package q.aTree.expr.unary;

import q.aTree.expr.AExpression;
import q.aTree.expr.ExpressionVisitor;
import q.aTree.type.BooleanT;

public class Not extends AUnary {
									
	public Not (AExpression expression) {
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
