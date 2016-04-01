package questionair.absTree.expression.unary;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.expression.ExpressionVisitor;
import questionair.absTree.type.AbsType;
import questionair.absTree.type.IntegerT;

public class Minus extends AbsUnary {
	
	public Minus (AbsExpression expression) {
		super(expression);
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public String toString() {
		return " - " + this.getUnaryExpression().toString();
	}

	@Override
	public AbsType getType() {
		return new IntegerT();
	}
}

