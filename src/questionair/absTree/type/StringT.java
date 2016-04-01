package questionair.absTree.type;


public class StringT extends AbsType {
	@Override
	public String toString() {
		return "string";
	}
		
	@Override
	public <T> T accept(TypeVisitor<T> visitor) {
		 return visitor.visit(this);
	}
	
	@Override
	public boolean isCompatibleToInteger() {
		return true;
	}
		
	@Override
	public boolean isCompatibleToType(AbsType type) {
		return type.isCompatibleToString();
	}
}


