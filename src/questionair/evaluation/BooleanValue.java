package questionair.evaluation;



public class BooleanValue extends Value<Boolean> {

	public BooleanValue(boolean booleanValue) {
		super(booleanValue);
	}

	public Value and(Value value) {
		return value.andBoolean(this);
	}

	public Value andBoolean(BooleanValue value) {
		return new BooleanValue(value.getValue() && getValue());
	}

	public Value or(Value value) {
		return value.orBoolean(this);
	}

	public Value orBoolean(BooleanValue value) {
		return new BooleanValue(value.getValue() || getValue());
	}

	public Value not() {
		return notBoolean();
	}

	public Value notBoolean() {
		return new BooleanValue(!getValue());
	}

	public Value equality(Value value) {
		return value.equalBoolean(this);
	}
	
	public Value equalBoolean(BooleanValue value) {
		return new BooleanValue(value.getValue() == getValue());
	}

}