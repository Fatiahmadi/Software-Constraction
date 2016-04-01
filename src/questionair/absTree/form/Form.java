package questionair.absTree.form;

import java.util.List;

import questionair.absTree.AbstractSyntaxTree;
import questionair.absTree.question.AbsQuestion;

public class Form extends AbstractSyntaxTree {
	private final String formIdentifier;
	private List<AbsQuestion> body;

	public Form(String formIdentifier, List<AbsQuestion> body) {
		this.formIdentifier = formIdentifier;
		this.body = body;
	}

	public String getFormIdentifier() {
		return formIdentifier;
	}

	public List<AbsQuestion> getBody() {
		return body;
	}

	public <T> T accept(FormVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public String toString() {
		String output = "Form " + this.formIdentifier + " { ";
		for (AbsQuestion q : body)
			output += q.toString() + "\n";
		output += " } ";

		return output;
	}
}