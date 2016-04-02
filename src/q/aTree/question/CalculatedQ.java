package q.aTree.question;

import q.aTree.expr.AExpression;
import q.aTree.expr.literal.Identifier;
import q.aTree.expr.literal.Stringliteral;
import q.aTree.type.AType;

public class CalculatedQ extends NormalQ {
	private final AExpression expr;

	public CalculatedQ(Identifier id, String label, AType type,
			AExpression e) {
		super(id, label,type);
		this.expr = e;
	}

	public AExpression getExpr() {
		return expr;
	}

	@Override
	public <T> T accept(QuestionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return super.getQId().toString() + " \"" + super.getQLabel() + "\" "
				+ super.getQType().toString() + " ( " + this.expr.toString()
				+ " )";
	}
}