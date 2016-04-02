package q.aTree.expr.literal;

import q.aTree.expr.ExpressionVisitor;
import q.aTree.type.IntegerT;

public class Integerliteral extends ALiteral {
	private final Integer integerliteral;

	public Integerliteral(int integerliteral) {
		this.integerliteral = integerliteral;
	}

	public int getVariable() {
		return integerliteral;
	}

	@Override
	public String toString() {
		return integerliteral.toString();
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public IntegerT getType() {
		return new IntegerT();
	}
}