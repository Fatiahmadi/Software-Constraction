package q.aTree.expr.logical;

import q.aTree.expr.AExpression;
import q.aTree.expr.ABinary;
import q.aTree.expr.ExpressionVisitor;
import q.aTree.type.BooleanT;



public class And extends ABinary {
							
	public And (AExpression leftHandExpr, AExpression rightHandExpr) {
		super(leftHandExpr, rightHandExpr);
	}
				
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		return this.getLeftHandExpr().toString() + " && " + this.getRightHandExpr().toString();
	}

	@Override
	public BooleanT getType() {
		return new BooleanT();
	}
}
