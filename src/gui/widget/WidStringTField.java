package gui.widget;

import gui.eventHandler.EvalExpression;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import questionair.absTree.type.AbsType;
import questionair.evaluation.StringValue;
import questionair.evaluation.Value;
import questionair.evaluation.ValueSaver;

public class WidStringTField implements WidComponent {
	private final String id;
	private final AbsType t;
	private JTextField jtF;
	private final ValueSaver valueSaver;

	public WidStringTField(String id, AbsType t, ValueSaver valueSaver) {
		this.id = id;
		this.t = t;
		this.valueSaver = valueSaver;
		this.jtF = new JTextField("", 10);
	}

	@Override
	public JComponent getWidget() {
		// TODO Auto-generated method stub
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
		jtF.setVisible(isEnabled);

	}

	@Override
	public void addDocListener(EvalExpression eval) {
		jtF.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) {
				valueSaver.putValue(getIdWidget().toString(), getValue());
			}

			public void insertUpdate(DocumentEvent arg0) {
				valueSaver.putValue(getIdWidget().toString(), getValue());
			}

			public void removeUpdate(DocumentEvent arg0) {
				valueSaver.putValue(getIdWidget().toString(), getValue());
			}
		});

	}

	@Override
	public Value getValue() {
		return new StringValue(jtF.getText());

	}

	@Override
	public void setValue(Value value) {
		value = valueSaver.getValue(id);
		jtF.setText("" + value.toString());

	}

	@Override
	public void setVisible(boolean visible) {
		jtF.setVisible(visible);

	}
}
