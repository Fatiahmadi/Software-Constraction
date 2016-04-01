package questionair.absTree.expression;

import questionair.absTree.expression.calculation.Add;
import questionair.absTree.expression.calculation.Division;
import questionair.absTree.expression.calculation.Sub;
import questionair.absTree.expression.calculation.Time;
import questionair.absTree.expression.comparison.Equal;
import questionair.absTree.expression.comparison.GreaterEqual;
import questionair.absTree.expression.comparison.GreaterThan;
import questionair.absTree.expression.comparison.LessEqual;
import questionair.absTree.expression.comparison.LessThan;
import questionair.absTree.expression.comparison.NotEqual;
import questionair.absTree.expression.literal.Booleanliteral;
import questionair.absTree.expression.literal.Identifier;
import questionair.absTree.expression.literal.Integerliteral;
import questionair.absTree.expression.literal.Stringliteral;
import questionair.absTree.expression.logical.And;
import questionair.absTree.expression.logical.Or;
import questionair.absTree.expression.unary.Minus;
import questionair.absTree.expression.unary.Not;
import questionair.absTree.expression.unary.Plus;

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
