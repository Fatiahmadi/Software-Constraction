package questionair.evaluation;

import questionair.evaluation.BooleanValue;
import questionair.evaluation.IntegerValue;
import questionair.evaluation.StringValue;
import questionair.evaluation.Value;
import questionair.absTree.expression.ExpressionVisitor;
import questionair.absTree.expression.Par;
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

public class EvalVisitor implements ExpressionVisitor<Value> {

	private final ValueSaver valueSaver;

	public EvalVisitor(ValueSaver  valueSaver) {
		this. valueSaver =  valueSaver;
	}

	@Override
	public Value visit(Par e) {
		return e.getParExpression().accept(this);
	}

	@Override
	public Value visit(Booleanliteral bool) {
		return new BooleanValue(bool.getVariable());
	}

	@Override
	public Value visit(Stringliteral string) {
		return new StringValue(string.getVariable());
	}

	@Override
	public Value visit(Integerliteral integer) {
		return new IntegerValue(integer.getVariable());
	}

	@Override
	public Value visit(And e) {
		Value left = e.getLeftExpression().accept(this);
		Value right = e.getRightExpression().accept(this);
		
		return left.and(right);
	}

	@Override
	public Value visit(Or e) {
		Value left = e.getLeftExpression().accept(this);
		Value right = e.getRightExpression().accept(this);
		
		return left.or(right);
	}

	@Override
	public Value visit(Add e) {
		Value left = e.getLeftExpression().accept(this);
		Value right = e.getRightExpression().accept(this);
		
		return left.add(right);
	}

	@Override
	public Value visit(Sub e) {
		Value left = e.getLeftExpression().accept(this);
		Value right = e.getRightExpression().accept(this);
		
		return left.substract(right);
	}

	@Override
	public Value visit(Time e) {
		Value left = e.getLeftExpression().accept(this);
		Value right = e.getRightExpression().accept(this);
		
		return left.time(right);
	}

	@Override
	public Value visit(Division e) {
		Value left = e.getLeftExpression().accept(this);
		Value right = e.getRightExpression().accept(this);
		
		return left.divide(right);
	}

	@Override
	public Value visit(Equal e) {
		Value left = e.getLeftExpression().accept(this);
		Value right = e.getRightExpression().accept(this);
		return left.equality(right);
	}

	@Override
	public Value visit(GreaterThan e) {
		Value left = e.getLeftExpression().accept(this);
		Value right = e.getRightExpression().accept(this);
		return left.greater(right);
	}

	@Override
	public Value visit(LessEqual e) {
		Value left = e.getLeftExpression().accept(this);
		Value right = e.getRightExpression().accept(this);
		return left.lessEqual(right);
	}

	@Override
	public Value visit(LessThan e) {
		Value left = e.getLeftExpression().accept(this);
		Value right = e.getRightExpression().accept(this);
		return left.less(right);
	}

	@Override
	public Value visit(GreaterEqual e) {
		Value left = e.getLeftExpression().accept(this);
		Value right = e.getRightExpression().accept(this);
		return left.greaterEqual(right);
	}

	@Override
	public Value visit(NotEqual e) {
		Value left = e.getLeftExpression().accept(this);
		Value right = e.getRightExpression().accept(this);
		
		return left.notEqual(right);
	}

	@Override
	public Value visit(Plus e) {
Value value = e.getUnaryExpression().accept(this);
		
		return value.plus();
	}

	@Override
	public Value visit(Minus e) {
Value value = e.getUnaryExpression().accept(this);
		
		return value.minus();
	}

	@Override
	public Value visit(Not e) {
Value value = e.getUnaryExpression().accept(this);
		
		return value.not();
	}

	@Override
	public Value visit(Identifier id) {
		return  valueSaver.getValue(id.getID());
	}
}
	
