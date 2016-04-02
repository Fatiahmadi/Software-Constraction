

package q.aTree.expr;

public abstract class ABinary extends AExpression {
	private final AExpression leftHandExpr, rightHandExpr;
	
	public ABinary (AExpression leftHandExpr, AExpression rightHandExpr) {
		this.leftHandExpr = leftHandExpr;
		this.rightHandExpr =rightHandExpr;
	}
			
	public AExpression  getLeftHandExpr() {
		return  leftHandExpr;
	}
			
	public AExpression getRightHandExpr() {
		return rightHandExpr;
	}
}