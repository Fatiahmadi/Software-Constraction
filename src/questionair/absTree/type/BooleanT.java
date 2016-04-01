package questionair.absTree.type;


public class BooleanT extends AbsType {

	public BooleanT() {}
	
	@Override
	public String toString() {
		return "boolean";
	}
		
	@Override
	public <T> T accept(TypeVisitor<T> visitor) {
		return visitor.visit(this);
	}
	@Override
	public boolean isCompatibleToBoolean() {
		return true;
	}
	
	@Override
	public boolean isCompatibleToType(AbsType type) {
		return type.isCompatibleToBoolean();
	}
}
