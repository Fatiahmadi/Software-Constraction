package gui.widget;

import gui.eventHandler.EvalExpression;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import questionair.absTree.type.AbsType;
import questionair.evaluation.BooleanValue;
import questionair.evaluation.Value;
import questionair.evaluation.ValueSaver;

public class WidBooleanT implements WidComponent {
	private final String id;
	private final AbsType t;
	private JCheckBox jChBox;
	private final ValueSaver valueSaver;
	private BooleanValue value;

	public WidBooleanT(String id, AbsType t, ValueSaver valueSaver) {
		this.id = id;
		this.valueSaver = valueSaver;
		this.t = t;
		this.jChBox = new JCheckBox();
	}

	@Override
	public JComponent getWidget() {
		return this.jChBox;

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
		this.setEnabled(isEnabled);

	}

	@Override
	public void addDocListener(EvalExpression eval) {
		jChBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				valueSaver.putValue(getIdWidget().toString(), getValue());
				eval.setValueInGUI();
			}
		});
	}

	@Override
	public Value getValue() {
		this.value = new BooleanValue(jChBox.isSelected());
		return value;
	}

	@Override
	public void setValue(Value value) {
		this.value = (BooleanValue) value;
		boolean selected = (Boolean) value.getValue();
		this.jChBox.setSelected(selected);
	}

	@Override
	public void setVisible(boolean visible) {
		jChBox.setVisible(visible);

	}

}