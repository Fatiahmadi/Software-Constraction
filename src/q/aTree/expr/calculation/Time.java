package q.aTree.expr.calculation;

import q.aTree.expr.AExpression;
import q.aTree.expr.ABinary;
import q.aTree.expr.ExpressionVisitor;
import q.aTree.type.IntegerT;

public class Time extends ABinary{
	
	public Time (AExpression leftHandExpr, AExpression RightHandExpr){
		super(leftHandExpr, RightHandExpr);
	}
		
		@Override
		public <T> T accept(ExpressionVisitor<T> visitor) {
			return visitor.visit(this);
		}
		
		@Override
		public String toString(){
			return this.getLeftHandExpr().toString() +  " * " + this.getRightHandExpr().toString();
		}

		@Override
		public IntegerT getType() {
			return new IntegerT();
		}	
	}


