package q.aTree.expr;

import q.aTree.type.AType;

public class Par extends AExpression {
	private final AExpression e;

	public Par(AExpression e) {
		this.e = e;
	}

	public AExpression getParExpression() {
		return e;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return "(" + this.e.toString() + ")";
	}

	@Override
	public AType getType() {
		return this.e.getType();
	}

}
