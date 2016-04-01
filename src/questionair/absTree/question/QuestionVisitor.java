package questionair.absTree.question;

public interface QuestionVisitor<T> {

	public T visit(NormalQ nq);

	public T visit(CalculatedQ cq);

	public T visit(IfQ ifQ);

	public T visit(IfElseQ ifelseQ);

}
