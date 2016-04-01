package questionair.absTree.question;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.expression.literal.Identifier;
import questionair.absTree.type.AbsType;

public class CalculatedQ extends NormalQ {
	private final AbsExpression e;

	public CalculatedQ(Identifier ID, String questionText, AbsType TYPE,
			AbsExpression e) {
		super(ID, questionText, TYPE);
		this.e = e;
	}

	public AbsExpression getE() {
		return e;
	}

	@Override
	public <T> T accept(QuestionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return super.getQId().toString() + " \"" + super.getQText() + "\" "
				+ super.getQType().toString() + " ( " + this.e.toString()
				+ " )";
	}
}