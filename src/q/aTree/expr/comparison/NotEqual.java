package q.aTree.expr.comparison;

import q.aTree.expr.AExpression;
import q.aTree.expr.ABinary;
import q.aTree.expr.ExpressionVisitor;
import q.aTree.type.BooleanT;

public class NotEqual extends ABinary {

	public NotEqual(AExpression leftHandExpr, AExpression rightHandExpr) {
		super(leftHandExpr, rightHandExpr);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return super.getLeftHandExpr().toString() + " != "
				+ super.getRightHandExpr().toString();
	}

	@Override
	public BooleanT getType() {
		return new BooleanT();
	}
}