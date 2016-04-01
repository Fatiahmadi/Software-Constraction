package questionair.evaluation;

import questionair.evaluation.BooleanValue;
import questionair.evaluation.IntegerValue;
import questionair.evaluation.Value;
public class IntegerValue extends Value<Integer>  {

	Integer x=getValue();
	
	public IntegerValue(Integer integerValue) {
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
		return new IntegerValue(getValue());
	}

	public Value minus() {
		return new IntegerValue(-getValue());
	}

	public Value addInt(IntegerValue value) {
		return new IntegerValue(value.getValue() + getValue());
	}

	public Value substractInt(IntegerValue value) {
		return new IntegerValue(value.getValue() - getValue());
	}

	public Value timeInt(IntegerValue value) {
		return new IntegerValue(value.getValue() * getValue());
	}

	
	public Value divideInt(IntegerValue value) {
		  if(getValue() == 0) { return new UndefinedValue(); }
		    return new IntegerValue(value.getValue() / getValue());
		  }
		

	
	public Value equalityInt(IntegerValue value) {
		return new BooleanValue(value.getValue() == getValue());
	}

	public Value greaterEqualInt(IntegerValue value) {
		return new BooleanValue(value.getValue() >= getValue());
	}

	public Value greaterInt(IntegerValue value) {
		return new BooleanValue(value.getValue() > getValue());
	}

	public Value lessEqualInt(IntegerValue value) {
		return new BooleanValue(value.getValue() <= getValue());
	}

	public Value lessInt(IntegerValue value) {
		return new BooleanValue(value.getValue() < getValue());
	}

	public Value notEqualInt(IntegerValue value) {
		return new BooleanValue(value.getValue() != getValue());
	}	
}