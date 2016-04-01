package questionair.absTree.expression.literal;

import questionair.absTree.expression.ExpressionVisitor;
import questionair.absTree.type.IntegerT;

public class Integerliteral extends AbsLiteral {
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