package q.aTree.expr.literal;

import q.aTree.expr.ExpressionVisitor;
import q.aTree.type.StringT;

public class Stringliteral extends ALiteral {
	private final String stringliteral;

	public Stringliteral(String stringliteral) {
		this.stringliteral = stringliteral;
	}

	public String getVariable() {
		return stringliteral;
	}

	@Override
	public String toString() {
		return stringliteral.toString();
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public StringT getType() {
		return new StringT();
	}
}