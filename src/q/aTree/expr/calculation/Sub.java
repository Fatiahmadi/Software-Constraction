package q.aTree.expr.calculation;


	import q.aTree.expr.AExpression;
import q.aTree.expr.ABinary;
import q.aTree.expr.ExpressionVisitor;
import q.aTree.type.IntegerT;

	public class Sub extends ABinary{
		
		public Sub (AExpression leftHandExp, AExpression RightHandExp){
			super(leftHandExp, RightHandExp);
		}
			
			@Override
			public <T> T accept(ExpressionVisitor<T> visitor){
				return visitor.visit(this);
			}
			@Override
			public String toString(){
				return this.getLeftHandExpr().toString() +  " - " + this.getRightHandExpr().toString();
			}

			@Override
			public IntegerT getType() {
				return new IntegerT();
			}	
		}





