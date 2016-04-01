/**This class represent how to use data structure  "Binary search tree" to access the subNodes of a expression in Ql
 *
 * left expression, operation, right expression.  
 * 
 */


package questionair.absTree.expression;

public abstract class Binary extends AbsExpression {
	private final AbsExpression leftExpression, rightExpression;
	
	public Binary (AbsExpression leftExpression, AbsExpression rightExpression) {
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
			
	public AbsExpression getLeftExpression() {
		return leftExpression;
	}
			
	public AbsExpression getRightExpression() {
		return rightExpression;
	}
}