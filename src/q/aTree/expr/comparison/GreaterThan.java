package q.aTree.expr.comparison;

import q.aTree.expr.AExpression;
import q.aTree.expr.ABinary;
import q.aTree.expr.ExpressionVisitor;
import q.aTree.type.BooleanT;

public class GreaterThan extends ABinary {

	public GreaterThan(AExpression LeftHandExpr, AExpression rightHandExpr) {
		super(LeftHandExpr, rightHandExpr);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return this.getLeftHandExpr().toString()+ "> "
				+ this.getRightHandExpr().toString();
	}

	@Override
	public BooleanT getType() {
		return new BooleanT();
	}
}
