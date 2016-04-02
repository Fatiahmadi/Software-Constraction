package q.aTree.type;


public class NotSpecifiedT extends AType {
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
	public boolean isCompatibleToType(AType type) {
		return false;
	}
}


