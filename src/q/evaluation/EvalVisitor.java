package q.evaluation;

import q.aTree.expr.ExpressionVisitor;
import q.aTree.expr.Par;
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
import q.evaluation.BooleanV;
import q.evaluation.IntegerV;
import q.evaluation.StringV;
import q.evaluation.Value;

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
		return new BooleanV(bool.getVariable());
	}

	@Override
	public Value visit(Stringliteral string) {
		return new StringV(string.getVariable());
	}

	@Override
	public Value visit(Integerliteral integer) {
		return new IntegerV(integer.getVariable());
	}

	@Override
	public Value visit(And e) {
		Value left = e.getLeftHandExpr().accept(this);
		Value right = e.getRightHandExpr().accept(this);
		
		return left.and(right);
	}

	@Override
	public Value visit(Or e) {
		Value left = e.getLeftHandExpr().accept(this);
		Value right = e.getRightHandExpr().accept(this);
		
		return left.or(right);
	}

	@Override
	public Value visit(Add e) {
		Value left = e.getLeftHandExpr().accept(this);
		Value right = e.getRightHandExpr().accept(this);
		
		return left.add(right);
	}

	@Override
	public Value visit(Sub e) {
		Value left = e.getLeftHandExpr().accept(this);
		Value right = e.getRightHandExpr().accept(this);
		
		return left.substract(right);
	}

	@Override
	public Value visit(Time e) {
		Value left = e.getLeftHandExpr().accept(this);
		Value right = e.getRightHandExpr().accept(this);
		
		return left.time(right);
	}

	@Override
	public Value visit(Division e) {
		Value left = e.getLeftHandExpr().accept(this);
		Value right = e.getRightHandExpr().accept(this);
		
		return left.divide(right);
	}

	@Override
	public Value visit(Equal e) {
		Value left = e.getLeftHandExpr().accept(this);
		Value right = e.getRightHandExpr().accept(this);
		return left.equality(right);
	}

	@Override
	public Value visit(GreaterThan e) {
		Value left = e.getLeftHandExpr().accept(this);
		Value right = e.getRightHandExpr().accept(this);
		return left.greater(right);
	}

	@Override
	public Value visit(LessEqual e) {
		Value left = e.getLeftHandExpr().accept(this);
		Value right = e.getRightHandExpr().accept(this);
		return left.lessEqual(right);
	}

	@Override
	public Value visit(LessThan e) {
		Value left = e.getLeftHandExpr().accept(this);
		Value right = e.getRightHandExpr().accept(this);
		return left.less(right);
	}

	@Override
	public Value visit(GreaterEqual e) {
		Value left = e.getLeftHandExpr().accept(this);
		Value right = e.getRightHandExpr().accept(this);
		return left.greaterEqual(right);
	}

	@Override
	public Value visit(NotEqual e) {
		Value left = e.getLeftHandExpr().accept(this);
		Value right = e.getRightHandExpr().accept(this);
		
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
	
