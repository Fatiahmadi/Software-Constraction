package user_Interface.widget;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import q.aTree.type.AType;
import q.evaluation.BooleanV;
import q.evaluation.Value;
import q.evaluation.ValueSaver;
import user_Interface.eventHandler.EvalExpression;

public class WidBooleanT implements WidComponent {
	private final String id;
	private final AType t;
	private JCheckBox jChBox;
	private final ValueSaver valueSaver;
	private BooleanV value;

	public WidBooleanT(String id, AType t, ValueSaver valueSaver) {
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
	public AType getWidgetType() {

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
		this.value = new BooleanV(jChBox.isSelected());
		return value;
	}

	@Override
	public void setValue(Value value) {
		this.value = (BooleanV) value;
		boolean selected = (Boolean) value.getValue();
		this.jChBox.setSelected(selected);
	}

	@Override
	public void setVisible(boolean visible) {
		jChBox.setVisible(visible);

	}

}