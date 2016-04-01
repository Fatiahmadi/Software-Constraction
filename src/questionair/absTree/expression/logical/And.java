package questionair.absTree.expression.logical;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.expression.Binary;
import questionair.absTree.expression.ExpressionVisitor;
import questionair.absTree.type.BooleanT;



public class And extends Binary {
							
	public And (AbsExpression leftExp, AbsExpression rightExp) {
		super(leftExp, rightExp);
	}
				
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return this.getLeftExpression().toString() + " && " + this.getRightExpression().toString();
	}

	@Override
	public BooleanT getType() {
		return new BooleanT();
	}
}
