package questionair.absTree.expression.comparison;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.expression.Binary;
import questionair.absTree.expression.ExpressionVisitor;
import questionair.absTree.type.BooleanT;

public class GreaterEqual extends Binary {

	public GreaterEqual(AbsExpression leftExpression,
			AbsExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return this.getLeftExpression().toString() + " >= "
				+ this.getRightExpression().toString();
	}

	@Override
	public BooleanT getType() {
		return new BooleanT();
	}
}
