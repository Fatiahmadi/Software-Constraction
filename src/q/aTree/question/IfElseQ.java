package q.aTree.question;

import java.util.List;

import q.aTree.expr.AExpression;


public class IfElseQ extends IfQ {

	private List<AQuestion> ifelseQ;

	public IfElseQ(AExpression ifExpression, List<AQuestion> ifQ,
			List<AQuestion> ifelseQ) {
		super(ifExpression, ifQ);
		this.ifelseQ = ifelseQ;
	}

	public List<AQuestion> getIfElseQ() {
		return ifelseQ;
	}

	@Override
	public <T> T accept(QuestionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		String output = super.toString();
		output += "\n else { \n";
		for (AQuestion qe : ifelseQ)
			output += qe.toString() + "\n";
		output += " }";
		return output;
	}
}