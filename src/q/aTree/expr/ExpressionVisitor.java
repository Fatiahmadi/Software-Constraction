package q.aTree.expr;

import q.aTree.expr.calculation.Add;
import q.aTree.expr.calculation.Division;
import q.aTree.expr.calculation.Sub;
import q.aTree.expr.calculation.Time;
import q.aTree.expr.comparison.Equal;
import q.aTree.expr.comparison.GreaterEqual;
import q.aTree.expr.comparison.GreaterThan;
import q.aTree.expr.comparison.LessEqual;
import q.aTree.expr.comparison.LessThan;
import q.aTree.expr.comparison.NotEqual;
import q.aTree.expr.literal.Booleanliteral;
import q.aTree.expr.literal.Identifier;
import q.aTree.expr.literal.Integerliteral;
import q.aTree.expr.literal.Stringliteral;
import q.aTree.expr.logical.And;
import q.aTree.expr.logical.Or;
import q.aTree.expr.unary.Minus;
import q.aTree.expr.unary.Not;
import q.aTree.expr.unary.Plus;

public interface ExpressionVisitor<T> {

	// visit variable

	public T visit(Par e);

	public T visit(Booleanliteral bool);

	public T visit(Stringliteral string);

	public T visit(Integerliteral integer);

	// visit Logical

	public T visit(And e);

	public T visit(Or e);

	// visitor interface visit calculation operator

	public T visit(Add e);

	public T visit(Sub e);

	public T visit(Time e);

	public T visit(Division e);

	// visit comparison

	public T visit(Equal e);

	public T visit(GreaterThan e);

	public T visit(LessEqual e);

	public T visit(LessThan e);

	public T visit(GreaterEqual e);

	public T visit(NotEqual e);

	// / interface visit unary operator

	public T visit(Plus e);

	public T visit(Minus e);

	public T visit(Not e);

	public T visit(Identifier id);

}
