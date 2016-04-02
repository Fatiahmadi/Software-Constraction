package q.evaluation;

import q.evaluation.BooleanV;
import q.evaluation.IntegerV;
import q.evaluation.Value;
public class IntegerV extends Value<Integer>  {
	Integer x= getValue();
	public IntegerV(int integerValue) {
		super(integerValue);
	}
	
	public Value add(Value value) {
		return value.addInt(this); 
	}

	public Value substract(Value value) {
		return value.substractInt(this); 
	}

	public Value time(Value value) {
		return value.timeInt(this); 
	}

	public Value divide(Value value) {
		return value.divideInt(this); 
	}

	public Value equality(Value value) {
		return value.equalityInt(this);
	}

	public Value greaterEqual(Value value) {
		return value.greaterEqualInt(this);
	}

	public Value greater(Value value) {
		return value.greaterInt(this);
	}

	public Value lessEqual(Value value) {
		return value.lessEqualInt(this);
	}

	public Value less(Value value) {
		return value.lessInt(this);
	}

	public Value notEqual(Value value) {
		return value.notEqualInt(this);
	}

	public Value plus() {
		return new IntegerV(getValue());
	}

	public Value minus() {
		return new IntegerV(-getValue());
	}

	public Value addInt(IntegerV value) {
		return new IntegerV(value.getValue() + getValue());
	}

	public Value substractInt(IntegerV value) {
		return new IntegerV(value.getValue() - getValue());
	}

	public Value timeInt(IntegerV value) {
		return new IntegerV(value.getValue() * getValue());
	}

	public Value divideInt(IntegerV value) {
		if(getValue()==0){return new UndefinedV();}
		return new IntegerV(value.getValue() / this.getValue());
	}

	public Value equalityInt(IntegerV value) {
		return new BooleanV(value.getValue() == getValue());
	}

	public Value greaterEqualInt(IntegerV value) {
		return new BooleanV(value.getValue() >= getValue());
	}

	public Value greaterInt(IntegerV value) {
		return new BooleanV(value.getValue() > getValue());
	}

	public Value lessEqualInt(IntegerV value) {
		return new BooleanV(value.getValue() <= getValue());
	}

	public Value lessInt(IntegerV value) {
		return new BooleanV(value.getValue() < getValue());
	}

	public Value notEqualInt(IntegerV value) {
		return new BooleanV(value.getValue() != getValue());
	}	
}