package questionair.absTree.type;


public class IntegerT extends AbsType{
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
	public boolean isCompatibleToType(AbsType type) {
		return type.isCompatibleToInteger();
	}
}


