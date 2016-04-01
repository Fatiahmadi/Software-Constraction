package questionair.absTree.expression.literal;

import questionair.absTree.expression.ExpressionVisitor;
import questionair.absTree.type.BooleanT;

public class Booleanliteral extends AbsLiteral {

	private final Boolean booleanLiteral;

	public Booleanliteral(Boolean booleanLiteral) {
		this.booleanLiteral = booleanLiteral;
	}

	public Boolean getVariable() {
		return booleanLiteral;
	}

	@Override
	public String toString() {
		return booleanLiteral.toString();
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public BooleanT getType() {
		return new BooleanT();
	}
}
