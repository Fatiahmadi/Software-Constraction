package questionair.absTree.type;

import questionair.absTree.AbstractSyntaxTree;


public abstract class AbsType extends AbstractSyntaxTree {
	public abstract <T> T accept(TypeVisitor<T> visitor);
	public abstract String toString();
	
	public abstract boolean isCompatibleToType(AbsType type);
			
	public boolean isCompatibleToBoolean() {
		return false;
	}
		
	public boolean isCompatibleToString() {
		return false;
	}
		
	public boolean isCompatibleToInteger() {
		return false;
	}

}
