package q.aTree.type;


public class BooleanT extends AType {

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
	public boolean isCompatibleToType(AType type) {
		return type.isCompatibleToBoolean();
	}
}
