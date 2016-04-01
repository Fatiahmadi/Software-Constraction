package gui.widget;

import gui.eventHandler.EvalExpression;

import javax.swing.JComponent;

import questionair.absTree.type.AbsType;
import questionair.evaluation.Value;

public interface WidComponent {

	public JComponent getWidget();

	public String getIdWidget();

	public AbsType getWidgetType();

	public void setEnabled(boolean isEnabled);

	public void addDocListener(EvalExpression eval);

	public Value getValue();

	public void setValue(Value value);

	public void setVisible(boolean visible);

}
