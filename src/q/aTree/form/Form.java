package q.aTree.form;

import java.util.List;

import q.aTree.ASyntaxTree;
import q.aTree.question.AQuestion;

public class Form extends ASyntaxTree {
	private final String formId;
	private List<AQuestion> formBody;

	public Form(String formId, List<AQuestion> formBody) {
		this.formId = formId;
		this.formBody = formBody;
	}

	public String getFormId() {
		return formId;
	}

	public List<AQuestion> getFormBody() {
		return formBody;
	}

	public <T> T accept(FormVisitor<T> visitor) {
		return visitor.visit(this);
	}

	public String toString() {
		String output = "Form " + this.formId + " { ";
		for (AQuestion q : formBody)
			output += q.toString() + "\n";
		output += " } ";

		return output;
	}
}