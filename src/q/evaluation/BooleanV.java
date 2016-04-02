package q.evaluation;



public class BooleanV extends Value<Boolean> {

	public BooleanV(boolean booleanValue) {
		super(booleanValue);
	}

	public Value and(Value value) {
		return value.andBoolean(this);
	}

	public Value andBoolean(BooleanV value) {
		return new BooleanV(value.getValue() && getValue());
	}

	public Value or(Value value) {
		return value.orBoolean(this);
	}

	public Value orBoolean(BooleanV value) {
		return new BooleanV(value.getValue() || getValue());
	}

	public Value not() {
		return notBoolean();
	}

	public Value notBoolean() {
		return new BooleanV(!getValue());
	}

	public Value equality(Value value) {
		return value.equalBoolean(this);
	}
	
	public Value equalBoolean(BooleanV value) {
		return new BooleanV(value.getValue() == getValue());
	}

}