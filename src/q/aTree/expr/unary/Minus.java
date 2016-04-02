package q.aTree.expr.unary;

import q.aTree.expr.AExpression;
import q.aTree.expr.ExpressionVisitor;
import q.aTree.type.AType;
import q.aTree.type.IntegerT;

public class Minus extends AUnary {
	
	public Minus (AExpression expression) {
		super(expression);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return " - " + this.getUnaryExpression().toString();
	}

	@Override
	public AType getType() {
		return new IntegerT();
	}
}

