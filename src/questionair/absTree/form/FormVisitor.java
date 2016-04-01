package questionair.absTree.form;

public interface FormVisitor<T> {
	public T visit(Form form);
}