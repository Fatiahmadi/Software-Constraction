package questionair.absTree.question;

import questionair.absTree.AbstractSyntaxTree;

public abstract class AbsQuestion extends AbstractSyntaxTree {

	public abstract String toString();

	public abstract <T> T accept(QuestionVisitor<T> visitor);

}