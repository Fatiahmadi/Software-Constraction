package q.evaluation;

import q.evaluation.BooleanV;
import q.evaluation.IntegerV;
import q.evaluation.Value;

public abstract class Value<T> {
	private final T value;
	
	public Value(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return value;
	}
	
	public String toString() {
		return value.toString();
	}

	public Value add(Value value) { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in addition operations."); 
	}
	
	public Value substract(Value value) { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in substaction operations."); 
	}
	
	public Value time(Value value) { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in multiplication operations."); 
	}
	
	public Value divide(Value value) { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in division operations."); 
	}
	
	
	public Value equality(Value value) { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in equation operations."); 
	}
	
	public Value greaterEqual(Value value) { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in (greater or equal) equation operations."); 
	}
	
	public Value greater(Value value) { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in (greater than) equation operations."); 
	}
	
	public Value lessEqual(Value value) { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in (less or equal) equation operations."); 
	}
	
	public Value less(Value value) { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in (less than) equation operations."); 
	}
	
	public Value notEqual(Value value) { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in (not equal) equation operations."); 
	}
	
	public Value and(Value value) { 
		throw new UnsupportedOperationException(
				"Invalid value type. Only boolean can be supported in logical operations with *and* operator."); 
	}
	
	public Value or(Value value) { 
		throw new UnsupportedOperationException(
				"Invalid value type. Only boolean can be supported in logical operations with *or* operator."); 
	}
	
	public Value not() { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in logical operations with *not* operator."); 
	}
	
	public Value plus() { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in unary operations with *+* operator."); 
	}
	
	public Value minus() { 
		throw new UnsupportedOperationException(
				"Invalid value type; can't be supported in unary operations with *-* operator."); 
	}
	
	// *** double dispatch to the rescue ***
	
	public Value addInt(IntegerV value) { 
		throw new UnsupportedOperationException(
				"Value is not of type integer and it can't be supported in addition."); 
	}
	
	public Value substractInt(IntegerV value) { 
		throw new UnsupportedOperationException(
				"Value is not of type integer and it can't be supported in substraction."); 
	}
	
	public Value timeInt(IntegerV value) { 
		throw new UnsupportedOperationException(
				"Value is not of type integer and it can't be supported in multiplication."); 
	}
	
	public Value divideInt(IntegerV value) { 
		throw new UnsupportedOperationException(
				"Value is not of type integer and it can't be supported in division."); 
	}
	
	public Value equalityInt(IntegerV value) {
		throw new UnsupportedOperationException(
				"Value is not of type integer and it can't be supported in equality operation."); 
	}
	
	public Value greaterEqualInt(IntegerV value) { 
		throw new UnsupportedOperationException(
				"Value is not of type integer and it can't be supported in equality operation."); 
	}
		
	public Value greaterInt(IntegerV value) { 
		throw new UnsupportedOperationException(
				"Value is not of type integer and it can't be supported in equality operation."); 
	}
	
	public Value lessEqualInt(IntegerV value) { 
		throw new UnsupportedOperationException(
				"Value is not of type integer and it can't be supported in equality operation."); 
	}
	
	public Value lessInt(IntegerV value) { 
		throw new UnsupportedOperationException(
				"Value is not of type integer and it can't be supported in equality operation."); 
	}
	
	public Value notEqualInt(IntegerV value) { 
		throw new UnsupportedOperationException(
				"Value is not of type integer and it can't be supported in equality operation."); 
	}
	
	public Value andBoolean(BooleanV value) { 
		throw new UnsupportedOperationException(
				"Value is not of type boolean and it can't be supported in logical operation."); 
	}
	
	public Value orBoolean(BooleanV value) { 
		throw new UnsupportedOperationException(
				"Value is not of type boolean and it can't be supported in logical operation."); 
	}
	
	public Value equalBoolean(BooleanV value) { 
		throw new UnsupportedOperationException(
				"Value is not of type boolean and it can't be supported in equality operation."); 
	}	
}