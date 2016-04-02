package user_Interface.widget;

import javax.swing.JComponent;

import q.aTree.type.AType;
import q.evaluation.Value;
import user_Interface.eventHandler.EvalExpression;

public interface WidComponent {

	public JComponent getWidget();

	public String getIdWidget();

	public AType getWidgetType();

	public void setEnabled(boolean isEnabled);

	public void addDocListener(EvalExpression eval);

	public Value getValue();

	public void setValue(Value value);

	public void setVisible(boolean visible);

}
