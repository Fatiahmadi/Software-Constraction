package questionair.absTree.expression.unary;

import questionair.absTree.expression.AbsExpression;

public abstract class AbsUnary extends AbsExpression {
	private final AbsExpression expression;
	
	public AbsUnary (AbsExpression expression) {
		this.expression = expression;
	}
	
	public AbsExpression getUnaryExpression() {
		return expression;
	}
}

