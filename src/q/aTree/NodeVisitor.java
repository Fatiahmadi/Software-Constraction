package q.aTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import q.aTree.expr.AExpression;
import q.aTree.expr.Par;
import q.aTree.expr.calculation.Add;
import q.aTree.expr.calculation.Division;
import q.aTree.expr.calculation.Sub;
import q.aTree.expr.calculation.Time;
import q.aTree.expr.comparison.Equal;
import q.aTree.expr.comparison.GreaterEqual;
import q.aTree.expr.comparison.GreaterThan;
import q.aTree.expr.comparison.LessEqual;
import q.aTree.expr.comparison.LessThan;
import q.aTree.expr.comparison.NotEqual;
import q.aTree.expr.literal.Booleanliteral;
import q.aTree.expr.literal.Identifier;
import q.aTree.expr.literal.Integerliteral;
import q.aTree.expr.literal.Stringliteral;
import q.aTree.expr.logical.And;
import q.aTree.expr.logical.Or;
import q.aTree.expr.unary.Minus;
import q.aTree.expr.unary.Not;
import q.aTree.expr.unary.Plus;
import q.aTree.form.Form;
import q.aTree.question.AQuestion;
import q.aTree.question.CalculatedQ;

import q.aTree.question.IfElseQ;
import q.aTree.question.IfQ;
import q.aTree.question.NormalQ;
import q.aTree.type.AType;
import q.aTree.type.BooleanT;
import q.aTree.type.IntegerT;
import q.aTree.type.StringT;
import antlrProducts.QLBaseVisitor;
import antlrProducts.QLParser;

/**
 * Goal of this class is Creating a Abstract syntax tree visitor witch is
 * inherited from QLBaseVisitor. <ASyntaxTree> is visitor type. It means
 * ASyntaxTree visits each node, each node has a Accept method. the
 * visitor calls the accept method and ask for its class. Accept() method calls
 * the visit() method of the visitor to response. means each method is inherited
 * from QLBaseVisitor and implemented base on the requirement of ASTNodeVisitor
 * class. the new entry.
 */
public class NodeVisitor extends QLBaseVisitor<ASyntaxTree> {
	// public void NodeVisitor() {}

	/**
	 * Method "visitForm" uses a ArrayList to collect the questions. it return a
	 * new Form which is included the form's id and a list of the questions.
	 */
	// @Override
	public ASyntaxTree visitForm(@NotNull QLParser.FormContext context) {
		ArrayList<AQuestion> questions = new ArrayList<AQuestion>();

		for (QLParser.QuestionContext q : context.question()) {
			questions.add((AQuestion) q.accept(this));
		}

		return new Form(context.IDENTIFIER().getText(), questions);
	}

	/**
	 * THis method uses "casting" to refer the exact type of question because
	 * the ASyntaxTree can not convert to the "AType".
	 * 
	 */
	@Override
	public ASyntaxTree visitNormalQ(
			@NotNull QLParser.NormalQContext context) {
		AType type = (AType) context.type().accept(this);
		Identifier identifier = new Identifier(context.IDENTIFIER().getText(),
				type);

		return new NormalQ(identifier, context.STRINGLITERAL().getText()
				.replaceAll("^\"|\"$", ""), (AType) context.type().accept(
				this));
	}

	@Override
	public ASyntaxTree visitCalculatedQ(
			@NotNull QLParser.CalculatedQContext context) {
		AType type = (AType) context.type().accept(this);
		Identifier identifier = new Identifier(context.IDENTIFIER().getText(),
				type);
		this.putIdType(identifier.getID(), type);
		return new CalculatedQ(identifier, context.STRINGLITERAL().getText()
				.replaceAll("^\"|\"$", ""), (AType) context.type().accept(
				this), (AExpression) context.expression().accept(this));
	}

	//
	private final HashMap<String, AType> idType = new HashMap<>();

	private void putIdType(String id, AType type) {
		idType.put(id, type);
	}

	private AType getIdType(String id) {
		return idType.containsKey(id) ? idType.get(id) : null;
	}

	@Override
	public ASyntaxTree visitIfQ(@NotNull QLParser.IfQContext context) {
		List<AQuestion> ifq = new ArrayList<AQuestion>();
		for (QLParser.QuestionContext q : context.question()) {
			ifq.add((AQuestion) q.accept(this));
		}
		return new IfQ((AExpression) context.expression().accept(this),
				ifq);

	}

	/**
	 * to create ifElseQ is a ArrayList is used to add the question for-loop is
	 * used to add the ifElseQ. The method return a new ElseQ
	 */

	@Override
	public ASyntaxTree visitIfElseQ(
			@NotNull QLParser.IfElseQContext context) {

		List<AQuestion> ifQ = new ArrayList<AQuestion>();
		for (QLParser.QuestionContext q : context.question()) {
			ifQ.add((AQuestion) q.accept(this));
		}
		List<AQuestion> elseQ = new ArrayList<AQuestion>();
		for (QLParser.QuestionContext q : context.question()) {
			elseQ.add((AQuestion) q.accept(this));
		}
		return new IfElseQ((AExpression) context.accept(this), ifQ,
				elseQ);
	}

	// AST visit the subNode of AExpression
	/**
	 * uses data structure ABinary access the subNodes of AExpression. First
	 * left node then right node will be visited by Visitor. / This data
	 * Structure is easy to modifiable. The Next part of the code consist of:
	 * developing AExpression's calculation, comparison, logical base of BST.
	 * 
	 */
	@Override
	public ASyntaxTree visitAndExpr(
			@NotNull QLParser.AndExprContext context) {

		return new And((AExpression) context.expression(0).accept(this),
				(AExpression) context.expression(1).accept(this));
	}

	@Override
	public ASyntaxTree visitORExpr(
			@NotNull QLParser.ORExprContext context) {
		return new Or((AExpression) context.expression(0).accept(this),
				(AExpression) context.expression(1).accept(this));
	}

	// ASTNode visit Calculation

	@Override
	public ASyntaxTree visitAddSubExpr(
			@NotNull QLParser.AddSubExprContext context) {

		if (context.OP.getText().equals("+")) {
			return new Add((AExpression) context.expression(0).accept(this),
					(AExpression) context.expression(1).accept(this));
		}

		if (context.OP.getText().equals("-")) {
			return new Sub((AExpression) context.expression(0).accept(this),
					(AExpression) context.expression(1).accept(this));
		}

		return null;
	}

	@Override
	public ASyntaxTree visitTimeDivExpr(
			@NotNull QLParser.TimeDivExprContext context) {

		if (context.OP.getText().equals("*")) {
			return new Time((AExpression) context.expression(0).accept(this),
					(AExpression) context.expression(1).accept(this));
		}

		if (context.OP.getText().equals("/")) {
			return new Division((AExpression) context.expression(0).accept(
					this), (AExpression) context.expression(1).accept(this));
		}

		return null;
	}

	// ASTNode visit Comparison
	@Override
	public ASyntaxTree visitCOMPExpr(
			@NotNull QLParser.COMPExprContext context) {

		if (context.OP.getText().equals(">")) {
			return new GreaterThan((AExpression) context.expression(0)
					.accept(this), (AExpression) context.expression(1)
					.accept(this));
		}

		if (context.OP.getText().equals(">=")) {
			return new GreaterEqual((AExpression) context.expression(0)
					.accept(this), (AExpression) context.expression(1)
					.accept(this));
		}

		if (context.OP.getText().equals("<")) {
			return new LessThan((AExpression) context.expression(0).accept(
					this), (AExpression) context.expression(1).accept(this));
		}
		if (context.OP.getText().equals("<=")) {
			return new LessEqual((AExpression) context.expression(0).accept(
					this), (AExpression) context.expression(1).accept(this));
		}

		return null;
	}

	@Override
	public ASyntaxTree visitEQUALExpr(
			@NotNull QLParser.EQUALExprContext context) {
		if (context.OP.getText().equals("==")) {
			return new Equal(
					(AExpression) context.expression(0).accept(this),
					(AExpression) context.expression(1).accept(this));
		}
		if (context.OP.getText().equals("!=")) {
			return new NotEqual((AExpression) context.expression(0).accept(
					this), (AExpression) context.expression(1).accept(this));
		}
		return null;
	}

	@Override
	public ASyntaxTree visitUnaryExpr(
			@NotNull QLParser.UnaryExprContext context) {
		if (context.OP.getText().equals("!")) {
			return new Not((AExpression) context.expression().accept(this));
		}
		if (context.OP.getText().equals("+")) {
			return new Plus((AExpression) context.expression().accept(this));
		}
		if (context.OP.getText().equals("-")) {
			return new Minus((AExpression) context.expression().accept(this));
		}
		return null;

	}

	public ASyntaxTree visitIdentifier(
			@NotNull QLParser.IdentifierContext context) {
		String id = context.IDENTIFIER().getText();
		AType type = this.getIdType(id);
		return new Identifier(context.IDENTIFIER().getText(), type);
	}

	// /....ASTNode visit the subNode of ql type and return a new type of QL
	// each of them
	@Override
	public ASyntaxTree visitBooleanQL(
			@NotNull QLParser.BooleanQLContext context) {
		return new BooleanT();

	}

	@Override
	public ASyntaxTree visitIntegerQL(
			@NotNull QLParser.IntegerQLContext context) {
		return new IntegerT();

	}

	public ASyntaxTree visitStringQL(
			@NotNull QLParser.StringQLContext context) {
		return new StringT();

	}

	// AST Visit AExpression literal

	/**
	 * In the literal's subNodes Boolean literal and IntegerLiteral, The
	 * valueOf() method is used to return the object that hold the value of the
	 * represented object. ....................
	 */
	@Override
	
	public ASyntaxTree visitBooleanLiteral(@NotNull QLParser.BooleanLiteralContext context)
	 {
		return new Booleanliteral(Boolean.valueOf(context.BOOLEANLITERAL()
				.getText()));

	}
	@Override
	public ASyntaxTree visitStringLiteral(@NotNull QLParser.StringLiteralContext context)
	 {
		return new Stringliteral(context.STRINGLITERAL().getText()
				.replaceAll("^\"|\"$", ""));

	}
	@Override 
	public ASyntaxTree visitIntegerLiteral(@NotNull QLParser.IntegerLiteralContext context)
	 {
		return new Integerliteral(Integer.valueOf(context.INTEGERLITERAL().getText()));
	}

	@Override
	public ASyntaxTree visitPar(@NotNull QLParser.ParContext context) {
		return new Par((AExpression) context.expression().accept(this));
	}
}