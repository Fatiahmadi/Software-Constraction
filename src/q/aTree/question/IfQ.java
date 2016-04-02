package q.aTree.question;

import java.util.List;

import q.aTree.expr.AExpression;
import q.aTree.type.BooleanT;





public class IfQ extends AQuestion {

	private final AExpression ifCondition;
	private List<AQuestion> ifBody;

	public IfQ(AExpression ifCondition, List<AQuestion> ifBody) {
		this.ifCondition = ifCondition;
		this.ifBody = ifBody;
	}

	public AExpression getIfCondition() {
		return ifCondition;
	}

	public List<AQuestion> getIfBody() {
		return ifBody;
	}

	@Override
	public <T> T accept(QuestionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public BooleanT getType() {
		return new BooleanT();
	}

	@Override
	public String toString() {
		String output = "if " + " ( " + this.ifCondition.toString() + " ) { \n";
		for (AQuestion q : ifBody)
			output += q.toString() + "\n";
		output += " } ";

		return output;
	}
}