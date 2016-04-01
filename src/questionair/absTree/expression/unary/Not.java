package questionair.absTree.expression.unary;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.expression.ExpressionVisitor;
import questionair.absTree.type.BooleanT;

public class Not extends AbsUnary {
									
	public Not (AbsExpression expression) {
		super(expression);
	}
					
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return " ! " + this.getUnaryExpression().toString();
	}

	@Override
	public BooleanT getType() {
		return new BooleanT();
	}
}
