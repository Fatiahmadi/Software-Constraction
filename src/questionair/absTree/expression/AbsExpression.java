/**This class represent a Abstract class of expression Node.
 * it class calls accept method to communication with the visitor pattern.
 * @param Visitor the AbstractSyntaxTree visitor.
 * The class calls the ToString() method to return the string representation of the object.
 */

package questionair.absTree.expression;

import questionair.absTree.AbstractSyntaxTree;
import questionair.absTree.type.AbsType;

public abstract class AbsExpression extends AbstractSyntaxTree {

	public abstract <T> T accept(ExpressionVisitor<T> visitor);

	public abstract String toString();

	public abstract AbsType getType();
}
