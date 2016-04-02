package q.aTree.expr.unary;

import q.aTree.expr.AExpression;

public abstract class AUnary extends AExpression {
	private final AExpression expression;
	
	public AUnary (AExpression expression) {
		this.expression = expression;
	}
	
	public AExpression getUnaryExpression() {
		return expression;
	}
}

