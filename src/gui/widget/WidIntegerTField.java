package gui.widget;

import gui.eventHandler.EvalExpression;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import questionair.absTree.type.AbsType;
import questionair.evaluation.IntegerValue;
import questionair.evaluation.Value;
import questionair.evaluation.ValueSaver;

public class WidIntegerTField implements WidComponent {

	private final String id;
	private final AbsType t;
	private JTextField jtF;
	private final ValueSaver valueSaver;
	private IntegerValue value;

	public WidIntegerTField(String id, AbsType t, ValueSaver valSaver) {
		this.id = id;
		this.t = t;
		this.valueSaver = valSaver;
		this.jtF = new JTextField(10);
	}

	@Override
	public JComponent getWidget() {

		return this.jtF;
	}

	@Override
	public String getIdWidget() {

		return this.id;
	}

	@Override
	public AbsType getWidgetType() {

		return this.t;
	}

	@Override
	public void setEnabled(boolean isEnabled) {
		this.jtF.setEditable(isEnabled);

	}

	@Override
	public void addDocListener(EvalExpression eval) {
		jtF.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) {
				valueSaver.putValue(getIdWidget().toString(), getValue());
				eval.setValueInGUI();
			}

			public void insertUpdate(DocumentEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						valueSaver.putValue(getIdWidget().toString(),
								getValue());
						eval.setValueInGUI();
					}
				});
			}

			public void removeUpdate(DocumentEvent arg0) {
				valueSaver.putValue(getIdWidget().toString(), getValue());
			}
		});

	}

	@Override
	public void setValue(Value value) {
		this.value = (IntegerValue) value;
		int computation = (Integer) value.getValue();

		jtF.setText(String.valueOf(computation));
		setDefaultBorder();

	}

	@Override
	public void setVisible(boolean visible) {
		jtF.setVisible(visible);

	}

	private void setWarningBorder() {
		Border warningBorder = BorderFactory.createLineBorder(Color.RED, 1);
		jtF.setBorder(warningBorder);
	}

	private void setDefaultBorder() {
		Border defaultBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
		jtF.setBorder(defaultBorder);
	}

	@Override
	public Value getValue() {

		String insertedValue = jtF.getText().trim();
		String regexDigits = "[-+]?\\d+(\\.\\d+)?";

		if (!insertedValue.isEmpty() && insertedValue.matches(regexDigits)) {
			setDefaultBorder();
			return new IntegerValue(Integer.valueOf(insertedValue));
		}
		setWarningBorder();
		return new IntegerValue(0);
	}
}
