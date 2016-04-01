package questionair.absTree.question;

import java.util.List;

import questionair.absTree.expression.AbsExpression;

public class IfElseQ extends IfQ {

	private List<AbsQuestion> ifelseQ;

	public IfElseQ(AbsExpression ifExpression, List<AbsQuestion> ifQ,
			List<AbsQuestion> ifelseQ) {
		super(ifExpression, ifQ);
		this.ifelseQ = ifelseQ;
	}

	public List<AbsQuestion> getIfElseQ() {
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
		for (AbsQuestion qe : ifelseQ)
			output += qe.toString() + "\n";
		output += " }";
		return output;
	}
}