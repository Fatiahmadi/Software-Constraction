package questionair.absTree.question;

import java.util.List;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.type.BooleanT;

public class IfQ extends AbsQuestion {

	private final AbsExpression ifE;
	private List<AbsQuestion> ifQ;

	public IfQ(AbsExpression ifE, List<AbsQuestion> ifQ) {
		this.ifQ = ifQ;
		this.ifE = ifE;
	}

	public AbsExpression getE() {
		return ifE;
	}

	public List<AbsQuestion> getIfQ() {
		return ifQ;
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
		String output = "if " + " ( " + this.ifE.toString() + " ) { \n";
		for (AbsQuestion q : ifQ)
			output += q.toString() + "\n";
		output += " } ";

		return output;
	}
}