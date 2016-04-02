package q.aTree.type;


public class IntegerT extends AType{
	@Override
	public String toString() {
		return "integer";
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
		return type.isCompatibleToInteger();
	}
}


