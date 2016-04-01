package questionair.absTree.expression.literal;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.expression.ExpressionVisitor;
import questionair.absTree.type.AbsType;

public class Identifier extends AbsExpression {
	private final String identifier;
	private final AbsType type;

	public Identifier(String identifier, AbsType type) {
		this.identifier = identifier;
		this.type = type;
	}

	public String getID() {
		return identifier;
	}

	@Override
	public String toString() {
		return identifier;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public AbsType getType() {
		return this.type;
	}

}