package questionair.absTree.expression.literal;

import questionair.absTree.expression.ExpressionVisitor;
import questionair.absTree.type.StringT;

public class Stringliteral extends AbsLiteral {
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