package questionair.absTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.expression.Par;
import questionair.absTree.expression.calculation.Add;
import questionair.absTree.expression.calculation.Division;
import questionair.absTree.expression.calculation.Sub;
import questionair.absTree.expression.calculation.Time;
import questionair.absTree.expression.comparison.Equal;
import questionair.absTree.expression.comparison.GreaterEqual;
import questionair.absTree.expression.comparison.GreaterThan;
import questionair.absTree.expression.comparison.LessEqual;
import questionair.absTree.expression.comparison.LessThan;
import questionair.absTree.expression.comparison.NotEqual;
import questionair.absTree.expression.literal.Booleanliteral;
import questionair.absTree.expression.literal.Identifier;
import questionair.absTree.expression.literal.Integerliteral;
import questionair.absTree.expression.literal.Stringliteral;
import questionair.absTree.expression.logical.And;
import questionair.absTree.expression.logical.Or;
import questionair.absTree.expression.unary.Minus;
import questionair.absTree.expression.unary.Not;
import questionair.absTree.expression.unary.Plus;
import questionair.absTree.form.Form;
import questionair.absTree.question.AbsQuestion;
import questionair.absTree.question.CalculatedQ;
import questionair.absTree.question.IfElseQ;
import questionair.absTree.question.IfQ;
import questionair.absTree.question.NormalQ;
import questionair.absTree.type.AbsType;
import questionair.absTree.type.BooleanT;
import questionair.absTree.type.IntegerT;
import questionair.absTree.type.StringT;
import antlrProducts.QLBaseVisitor;
import antlrProducts.QLParser;


/**
 * Goal of this class is Creating a Abstract syntax tree visitor witch is
 * inherited from QLBaseVisitor. <AbstractSyntaxTree> is visitor type. It means
 * AbstractSyntaxTree visits each node, each node has a Accept method. the
 * visitor calls the accept method and ask for its class. Accept() method calls
 * the visit() method of the visitor to response. means each method is inherited
 * from QLBaseVisitor and implemented base on the requirement of ASTNodeVisitor
 * class. the new entry.
 */
public class AstNodeVisitor extends QLBaseVisitor<AbstractSyntaxTree> {
	// public void AstNodeVisitor() {}

	/**
	 * Method "visitForm" uses a ArrayList to collect the questions. it return a
	 * new Form which is included the form's id and a list of the questions.
	 */
	// @Override
	public AbstractSyntaxTree visitForm(@NotNull QLParser.FormContext context) {
		ArrayList<AbsQuestion> questions = new ArrayList<AbsQuestion>();

		for (QLParser.QuestionContext q : context.question()) {
			questions.add((AbsQuestion) q.accept(this));
		}

		return new Form(context.IDENTIFIER().getText(), questions);
	}

	/**
	 * THis method uses "casting" to refer the exact type of question because
	 * the AbstractSyntaxTree can not convert to the "AbsType".
	 * 
	 */
	@Override public AbstractSyntaxTree visitNormalQ(@NotNull QLParser.NormalQContext context){
		AbsType type = (AbsType) context.type().accept(this);
		Identifier identifier = new Identifier(context.IDENTIFIER().getText(),
				type);

		return new NormalQ(identifier, context.STRINGLITERAL().getText()
				.replaceAll("^\"|\"$", ""), (AbsType) context.type().accept(
				this));
	}
	

	@Override
	public AbstractSyntaxTree visitCalculatedQ(
			@NotNull QLParser.CalculatedQContext context) {
		AbsType type = (AbsType) context.type().accept(this);
		Identifier identifier = new Identifier(context.IDENTIFIER().getText(),
				type);
		this.putIdType(identifier.getID(), type);
		return new CalculatedQ(identifier, context.STRINGLITERAL().getText()
				.replaceAll("^\"|\"$", ""), (AbsType) context.type().accept(
				this), (AbsExpression) context.expression().accept(this));
	}

	private final HashMap<String, AbsType> idType = new HashMap<>();

	private void putIdType(String id, AbsType type) {
		idType.put(id, type);
	}

	private AbsType getIdType(String id) {
		return idType.containsKey(id) ? idType.get(id) : null;
	}

	@Override
	public AbstractSyntaxTree visitIfQ(@NotNull QLParser.IfQContext context) {
		List<AbsQuestion> ifquestions = new ArrayList<AbsQuestion>();
		for (QLParser.QuestionContext q : context.question()) {
			ifquestions.add((AbsQuestion) q.accept(this));
		}
		return new IfQ((AbsExpression) context.expression().accept(this),
				ifquestions);

	}

	/**
	 * to create ifElseQ is a ArrayList is used to add the question for-loop is
	 * used to add the ifElseQ. The method return a new IfElseQ
	 */

	@Override
	public AbstractSyntaxTree visitIfElseQ(
			@NotNull QLParser.IfElseQContext context) {

		List<AbsQuestion> ifQuestions = new ArrayList<AbsQuestion>();
		for (QLParser.QuestionContext q : context.question()) {
			ifQuestions.add((AbsQuestion) q.accept(this));
		}
		List<AbsQuestion> elseQuestions = new ArrayList<AbsQuestion>();
		for (QLParser.QuestionContext q : context.question()) {
			elseQuestions.add((AbsQuestion) q.accept(this));
		}
		return new IfElseQ((AbsExpression) context.accept(this), ifQuestions,
				elseQuestions);
	}

	// AST visit the subNode of AbsExpression
	/**
	 * uses data structure Binary access the subNodes of AbsExpression. First
	 * left node then right node will be visited by Visitor. / This data
	 * Structure is easy to modifiable. The Next part of the code consist of:
	 * developing AbsExpression's calculation, comparison, logical base of BST.
	 * 
	 */

	@Override
	public AbstractSyntaxTree visitAndExpr(
			@NotNull QLParser.AndExprContext context) {

		return new And((AbsExpression) context.expression(0).accept(this),
				(AbsExpression) context.expression(1).accept(this));
	}

	@Override
	public AbstractSyntaxTree visitORExpr(
			@NotNull QLParser.ORExprContext context) {
		return new Or((AbsExpression) context.expression(0).accept(this),
				(AbsExpression) context.expression(1).accept(this));
	}

	// ASTNode visit Calculation

	@Override
	public AbstractSyntaxTree visitAddSubExpr(
			@NotNull QLParser.AddSubExprContext context) {

		if (context.OP.getText().equals("+")) {
			return new Add((AbsExpression) context.expression(0).accept(this),
					(AbsExpression) context.expression(1).accept(this));
		}

		if (context.OP.getText().equals("-")) {
			return new Sub((AbsExpression) context.expression(0).accept(this),
					(AbsExpression) context.expression(1).accept(this));
		}

		return null;
	}

	@Override
	public AbstractSyntaxTree visitTimeDivExpr(
			@NotNull QLParser.TimeDivExprContext context) {

		if (context.OP.getText().equals("*")) {
			return new Time((AbsExpression) context.expression(0).accept(this),
					(AbsExpression) context.expression(1).accept(this));
		}

		if (context.OP.getText().equals("/")) {
			return new Division((AbsExpression) context.expression(0).accept(
					this), (AbsExpression) context.expression(1).accept(this));
		}

		return null;
	}

	// ASTNode visit Comparison
	@Override
	public AbstractSyntaxTree visitCOMPExpr(
			@NotNull QLParser.COMPExprContext context) {

		if (context.OP.getText().equals(">")) {
			return new GreaterThan((AbsExpression) context.expression(0)
					.accept(this), (AbsExpression) context.expression(1)
					.accept(this));
		}

		if (context.OP.getText().equals(">=")) {
			return new GreaterEqual((AbsExpression) context.expression(0)
					.accept(this), (AbsExpression) context.expression(1)
					.accept(this));
		}

		if (context.OP.getText().equals("<")) {
			return new LessThan((AbsExpression) context.expression(0).accept(
					this), (AbsExpression) context.expression(1).accept(this));
		}
		if (context.OP.getText().equals("<=")) {
			return new LessEqual((AbsExpression) context.expression(0).accept(
					this), (AbsExpression) context.expression(1).accept(this));
		}

		return null;
	}

	@Override
	public AbstractSyntaxTree visitEQUALExpr(
			@NotNull QLParser.EQUALExprContext context) {
		if (context.OP.getText().equals("==")) {
			return new Equal(
					(AbsExpression) context.expression(0).accept(this),
					(AbsExpression) context.expression(1).accept(this));
		}
		if (context.OP.getText().equals("!=")) {
			return new NotEqual((AbsExpression) context.expression(0).accept(
					this), (AbsExpression) context.expression(1).accept(this));
		}
		return null;
	}

	@Override
	public AbstractSyntaxTree visitUnaryExpr(
			@NotNull QLParser.UnaryExprContext context) {
		if (context.OP.getText().equals("!")) {
			return new Not((AbsExpression) context.expression().accept(this));
		}
		if (context.OP.getText().equals("+")) {
			return new Plus((AbsExpression) context.expression().accept(this));
		}
		if (context.OP.getText().equals("-")) {
			return new Minus((AbsExpression) context.expression().accept(this));
		}
		return null;

	}

	@Override
	public AbstractSyntaxTree visitIdentifier(
			@NotNull QLParser.IdentifierContext context)

	{
		String id = context.IDENTIFIER().getText();
		AbsType type = this.getIdType(id);
		return new Identifier(context.IDENTIFIER().getText(), type);
	}

	// /....ASTNode visit the subNode of ql type and return a new type of QL
	// each of them
	@Override public AbstractSyntaxTree visitStringQL(@NotNull QLParser.StringQLContext context){
		return new StringT();
		
	}
	@Override
	public AbstractSyntaxTree visitBooleanQL(
			@NotNull QLParser.BooleanQLContext context) {
		return new BooleanT();

	}

	@Override
	public AbstractSyntaxTree visitIntegerQL(
			@NotNull QLParser.IntegerQLContext context) {
		return new IntegerT();

	}
	@Override
	public Integerliteral visitNumber(@NotNull QLParser.NumberContext context) {

		return new Integerliteral(Integer.valueOf(context.NUMBER().getText()));
	}

	// AST Visit AbsExpression literal

	/**
	 * In the literal's subNodes Boolean literal and IntegerLiteral, The
	 * valueOf() method is used to return the object that hold the value of the
	 * represented object. ....................
	 */
	

	@Override
	public AbstractSyntaxTree visitBooleanLiteral(
			@NotNull QLParser.BooleanLiteralContext context)

	{
		return new Booleanliteral(Boolean.valueOf(context.BOOLEANLITERAL()
				.getText()));

	}

	@Override
	public AbstractSyntaxTree visitStringLiteral(
			@NotNull QLParser.StringLiteralContext context) {
		return new Stringliteral(context.STRINGLITERAL().getText().replaceAll("^\"|\"$", ""));
	}

	public AbstractSyntaxTree visitIntegerliteral(
			@NotNull QLParser.IntegerLiteralContext context) {
		return new Integerliteral(Integer.valueOf(context.INTEGERLITERAL()
				.getText()));
	}

	@Override
	public AbstractSyntaxTree visitPar(@NotNull QLParser.ParContext context) {
		return new Par((AbsExpression) context.expression().accept(this));
	}
}