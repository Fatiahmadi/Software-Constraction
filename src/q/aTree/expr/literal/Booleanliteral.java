package q.aTree.expr.literal;

import q.aTree.expr.ExpressionVisitor;
import q.aTree.type.BooleanT;

public class Booleanliteral extends ALiteral {

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
