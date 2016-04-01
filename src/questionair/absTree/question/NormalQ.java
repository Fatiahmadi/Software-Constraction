package questionair.absTree.question;

import questionair.absTree.expression.literal.Identifier;
import questionair.absTree.type.AbsType;

public class NormalQ extends AbsQuestion {
	private final Identifier identifier;
	private final String Stringliteral;
	private final AbsType type;

	public NormalQ(Identifier questionID, String questionText,
			AbsType questionType) {
		this.identifier = questionID;
		this.Stringliteral = questionText;
		this.type = questionType;
	}

	public Identifier getQId() {
		return identifier;
	}

	public String getQText() {
		return Stringliteral;
	}

	public AbsType getQType() {
		return type;
	}

	@Override
	public <T> T accept(QuestionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return this.identifier.toString() + " \"" + this.Stringliteral + "\" "
				+ this.type.toString();
	}

}