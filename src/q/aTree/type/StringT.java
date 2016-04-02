package q.aTree.type;


public class StringT extends AType {
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
	public boolean isCompatibleToType(AType type) {
		return type.isCompatibleToString();
	}
}


