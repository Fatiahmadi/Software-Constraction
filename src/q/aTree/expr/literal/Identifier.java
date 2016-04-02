package q.aTree.expr.literal;

import q.aTree.expr.AExpression;
import q.aTree.expr.ExpressionVisitor;
import q.aTree.type.AType;

public class Identifier extends AExpression {
	private final String identifier;
	private final AType type;

	public Identifier(String identifier, AType type) {
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
	public AType getType() {
		return this.type;
	}

}