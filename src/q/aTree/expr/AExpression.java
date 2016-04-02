/**This class represent a Abstract class of expression Node.
 * it class calls accept method to communication with the visitor pattern.
 * @param Visitor the ASyntaxTree visitor.
 * The class calls the ToString() method to return the string representation of the object.
 */

package q.aTree.expr;

import q.aTree.ASyntaxTree;
import q.aTree.type.AType;

public abstract class AExpression extends ASyntaxTree {

	public abstract <T> T accept(ExpressionVisitor<T> visitor);

	public abstract String toString();

	public abstract AType getType();
}
