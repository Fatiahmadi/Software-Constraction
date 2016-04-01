package questionair.absTree.type;


public class NotSpecifiedT extends AbsType {
public NotSpecifiedT() { }
	
	@Override
	public String toString() {
		return "notSpecified"; 
	}
	
	@Override
	public <T> T accept(TypeVisitor<T> visitor) {
		return visitor.visit(this);
	}
		
	@Override
	public boolean isCompatibleToType(AbsType type) {
		return false;
	}
}


