package questionair.absTree.expression.calculation;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.expression.Binary;
import questionair.absTree.expression.ExpressionVisitor;
import questionair.absTree.type.IntegerT;

public class Time extends Binary{
	
	public Time (AbsExpression LeftExp, AbsExpression RightExp){
		super(LeftExp, RightExp);
	}
		
		@Override
		public <T> T accept(ExpressionVisitor<T> visitor) {
			return visitor.visit(this);
		}
		
		@Override
		public String toString(){
			return this.getLeftExpression().toString() +  " * " + this.getRightExpression().toString();
		}

		@Override
		public IntegerT getType() {
			return new IntegerT();
		}	
	}


