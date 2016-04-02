package q.aTree.expr.literal;

import q.aTree.expr.AExpression;
import q.aTree.expr.ExpressionVisitor;

public abstract class ALiteral extends AExpression {
	public abstract String toString();

	public abstract <T> T accept(ExpressionVisitor<T> visitor);
}