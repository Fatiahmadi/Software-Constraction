package questionair.absTree.expression;

import questionair.absTree.type.AbsType;

public class Par extends AbsExpression {
	private final AbsExpression e;

	public Par(AbsExpression e) {
		this.e = e;
	}

	public AbsExpression getParExpression() {
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
	public AbsType getType() {
		return this.e.getType();
	}

}
