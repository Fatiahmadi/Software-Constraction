package q.aTree.question;

import q.aTree.ASyntaxTree;

public abstract class AQuestion extends ASyntaxTree {

	public abstract String toString();

	public abstract <T> T accept(QuestionVisitor<T> visitor);

}