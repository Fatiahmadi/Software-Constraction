package q.aTree.type;

import q.aTree.ASyntaxTree;


public abstract class AType extends ASyntaxTree {
	public abstract <T> T accept(TypeVisitor<T> visitor);
	public abstract String toString();
	
	public abstract boolean isCompatibleToType(AType type);
			
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
