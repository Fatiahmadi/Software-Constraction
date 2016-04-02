package user_Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import q.aTree.form.Form;
import q.aTree.form.FormVisitor;
import q.aTree.question.AQuestion;
import q.evaluation.ValueSaver;
import user_Interface.eventHandler.SaveButtonAction;
import user_Interface.q.UINormalQ;

public class QRendering implements FormVisitor<JPanel> {
	private final JPanel panel;
	private final ValueSaver valueSaver;
	private final LinkedHashMap<String, UINormalQ> widSaver;
	private final JButton saveData;

	private QRendering() {
		this.panel = new JPanel();
		this.panel.setLayout(new MigLayout("wrap 2, hidemode 3"));
		this.saveData = new JButton("Save questionnaire");
		this.valueSaver = new ValueSaver();
		this.widSaver = new LinkedHashMap<String, UINormalQ>();
	}

	public static JPanel make(Form form) {
		QRendering visitor = new QRendering();
		form.accept(visitor);
		return visitor.getPanel();
	}

	public JPanel getPanel() {
		return panel;
	}

	public void addToPanel() {
		Set<String> keys = widSaver.keySet();
		for (String k : keys) {
			this.panel.add(widSaver.get(k).getJL());
			this.panel.add(widSaver.get(k).getWidCom().getWidget(), "wrap");
		}
		addSaveButton();
	}

	public void addSaveButton() {
		saveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int selectedOption = JOptionPane.showConfirmDialog(null,
						"Do you want to save changes made to the form?",
						"Save form", JOptionPane.YES_NO_OPTION);

				if (selectedOption == JOptionPane.YES_OPTION) {
					new SaveButtonAction(valueSaver);
				}
			}
		});
		this.panel.add(saveData, "span 2, align center");
	}

	public Set<String> getIDkeys() {
		Set<String> keys = widSaver.keySet();
		return keys;
	}

	public void putWidgetRepository(String id, UINormalQ widgets) {
		this.widSaver.put(id, widgets);
	}

	public UINormalQ getIDNormalQUI(String id) {
		return this.widSaver.get(id);
	}

	public boolean containsNormalQUI(String id) {
		return this.widSaver.containsKey(id);
	}

	@Override
	public JPanel visit(Form form) {
		for (AQuestion q : form.getFormBody()) {
			q.accept(new UI_Visitor(this, valueSaver));
		}
		addToPanel();
		return panel;
	}
}
