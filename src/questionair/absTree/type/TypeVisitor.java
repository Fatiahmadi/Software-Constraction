package questionair.absTree.type;


public interface TypeVisitor<T> {
	public T visit(IntegerT t);
	public T visit(StringT t);
	public T visit(BooleanT t);
	public T visit(NotSpecifiedT t);

}
