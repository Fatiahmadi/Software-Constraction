package questionair.absTree.expression.literal;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.expression.ExpressionVisitor;

public abstract class AbsLiteral extends AbsExpression {
	public abstract String toString();

	public abstract <T> T accept(ExpressionVisitor<T> visitor);
}