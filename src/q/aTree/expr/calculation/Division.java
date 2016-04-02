package q.aTree.expr.calculation;

import q.aTree.expr.AExpression;
import q.aTree.expr.ABinary;
import q.aTree.expr.ExpressionVisitor;
import q.aTree.type.IntegerT;

public class Division extends ABinary {

	public Division(AExpression leftHandExpr, AExpression rightHandExpr) {
		super(leftHandExpr, rightHandExpr);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return this.getLeftHandExpr().toString() + " / "
				+ this.getRightHandExpr().toString();
	}

	@Override
	public IntegerT getType() {
		return new IntegerT();
	}
}
