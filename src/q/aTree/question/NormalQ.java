package q.aTree.question;

import q.aTree.expr.literal.Identifier;
import q.aTree.expr.literal.Stringliteral;
import q.aTree.type.AType;

public class NormalQ extends AQuestion {
	private final Identifier id;
	private final String label;
	private final AType type;

	public NormalQ(Identifier id, String label,
			AType type) {
		this.id = id;
		this.label = label;
		this.type = type;
	}

	public Identifier getQId() {
		return id;
	}

	public String getQLabel() {
		return label;
	}

	public AType getQType() {
		return type;
	}

	@Override
	public <T> T accept(QuestionVisitor<T> visitor) {
		return visitor.visit(this);
	}
// " \"" + this.label + "\" "
	@Override
	public String toString() {
		return this.id.toString() +  this.label.toString()   
				+ this.type.toString();
	}

}