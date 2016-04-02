package q.semanticAnalysis;

import java.util.List;
import java.util.Map;

import q.aTree.expr.AExpression;
import q.aTree.expr.ABinary;
import q.aTree.expr.ExpressionVisitor;
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
import q.aTree.expr.unary.AUnary;
import q.aTree.expr.unary.Minus;
import q.aTree.expr.unary.Not;
import q.aTree.expr.unary.Plus;
import q.aTree.form.Form;
import q.aTree.form.FormVisitor;
import q.aTree.question.AQuestion;
import q.aTree.question.CalculatedQ;

import q.aTree.question.IfElseQ;
import q.aTree.question.IfQ;
import q.aTree.question.NormalQ;
import q.aTree.question.QuestionVisitor;
import q.aTree.type.AType;
import q.aTree.type.IntegerT;
import q.semanticAnalysis.error.Error;
import q.semanticAnalysis.error.ErrorWarningList;
import q.semanticAnalysis.error.Warning;

public class TypeChecking implements FormVisitor<Void>, QuestionVisitor<Void>,
		ExpressionVisitor<Void> {
	protected final ErrorWarningList errorCollector;
	protected final SymTabel typeSaver;
	private final QRelation qRelation;

	public TypeChecking() {
		this.errorCollector = new ErrorWarningList();
		this.typeSaver = new SymTabel();
		this.qRelation = new QRelation();
	}

	public List<Error> getError() {
		return this.errorCollector.getErrorList();
	}

	public List<Warning> getWarning() {
		return this.errorCollector.getWarningList();
	}

	public boolean isCorrect() {
		return !this.errorCollector.containsError();
	}

	// Check the question
	public void checkQ(NormalQ q) {
		CheckDupplicatedQId(q);
		CheckDupplicationQLabel(q);
		checkForLoopRelation();
	}

	// Check duplication question id (Error)
	public void CheckDupplicatedQId(NormalQ q) {
		String id = q.getQId().getID();
		AType type = q.getQType();

		if (!this.typeSaver.empty()) {
			if (!this.typeSaver.isDefined(id)
					|| this.typeSaver.getValue(id).equals(type)) {
				return;
			}

			this.errorCollector.addErrorToErrorList("Question *" + id
					+ " is defined for another type.");
		}
	}

	// duplicate QLabels (warning)

	public void CheckDupplicationQLabel(NormalQ q) {
		String id = q.getQId().getID(); // violate the law of demeter
		String QLabel = q.getQLabel();

		if (this.typeSaver != null) {
			for (Map.Entry<String, String> input : this.typeSaver
					.getQLabelServer().entrySet()) {
				String key = input.getKey();
				String QLabelValue = input.getValue();

				if (!QLabelValue.equals(QLabel) || key.equals(id)) {
					continue;// skip the current statement and jump to the next
								// one
				}
				this.errorCollector
						.addWarningToWarningList("Warning! there is another question with the same*"
								+ QLabelValue + " or*" + key + "*.");
			}
		}
	}

	// .............check expression

	public Void checkExpression(ABinary e) {
		e.getLeftHandExpr().accept(this);
		e.getRightHandExpr().accept(this);

		 checkCompatibalityType(e.getLeftHandExpr(), e.getType());
		 checkCompatibalityType(e.getRightHandExpr(), e.getType());
		return null;
	}

	public Void checkComparison(ABinary e) {
		e.getLeftHandExpr().accept(this);
		e.getRightHandExpr().accept(this);

		 checkCompatibalityType(e.getLeftHandExpr(), new IntegerT());
		 checkCompatibalityType(e.getRightHandExpr(), new IntegerT());
		return null;
	}

	public Void checkUnary(AUnary e) {
		e.getUnaryExpression().accept(this);

		 checkCompatibalityType(e.getUnaryExpression(), e.getType());
		return null;
	}

	public Void checkEqual(ABinary e) {
		e.getLeftHandExpr().accept(this);
		e.getRightHandExpr().accept(this);

		 checkCompatibalityType(e.getLeftHandExpr(), e
				.getRightHandExpr().getType());

		return null;
	}
	public void checkCompatibalityType(AExpression e, AType t) {
		if (e.getType() != null) {
			if (e.getType().isCompatibleToType(t)) {
				return;
			}
			this.errorCollector
					.addErrorToErrorList(" Error:Type of the AExpression *"
							+ e.toString() + "is not correct" + e.getType()
							+ "the type should be *" + t + "*.");
		}
	}
	private void checkForLoopRelation() {
		if (this.qRelation.hasloop()) {
			IdentifierList loopIds = this.qRelation.getLoopIds();
			this.errorCollector.addErrorToErrorList("Error: the next question"
					+ loopIds.toString() + "causes a loop dependentie");
		}
	}

	

	// .......visitor
	@Override
	public Void visit(Identifier id) {
		String identifier = id.getID();

		if (!this.typeSaver.isDefined(identifier)) {
			this.errorCollector
					.addErrorToErrorList("Error: reference to undefined question *"
							+ id + "*.");
		}
		return null;
	}

	// AType checking visit and check literal
	@Override
	public Void visit(Booleanliteral bool) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(Stringliteral string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(Integerliteral integer) {
		// TODO Auto-generated method stub
		return null;
	}

	// AType checking visit and check calculation
	@Override
	public Void visit(And e) {
		return this.checkExpression(e);

	}

	@Override
	public Void visit(Or e) {
		return this.checkExpression(e);

	}

	@Override
	public Void visit(Add e) {
		return this.checkExpression(e);

	}

	@Override
	public Void visit(Sub e) {
		return this.checkExpression(e);
	}

	@Override
	public Void visit(Time e) {
		return this.checkExpression(e);

	}

	@Override
	public Void visit(Division e) {
		return this.checkExpression(e);

	}

	// AType checking visit and check comparison expressions
	@Override
	public Void visit(GreaterThan e) {
		return this.checkComparison(e);
	}

	@Override
	public Void visit(LessEqual e) {
		return this.checkComparison(e);

	}

	@Override
	public Void visit(LessThan e) {
		return this.checkComparison(e);
	}

	@Override
	public Void visit(GreaterEqual e) {
		return this.checkComparison(e);
	}

	// AType checking visit and check Equal end NotEqual expressions
	@Override
	public Void visit(Equal e) {
		return this.checkEqual(e);
	}

	@Override
	public Void visit(NotEqual e) {
		return this.checkEqual(e);
	}

	// AType checking visit and check AUnary expressions
	@Override
	public Void visit(Plus e) {
		return this.checkUnary(e);
	}

	@Override
	public Void visit(Minus e) {
		return this.checkUnary(e);
	}

	@Override
	public Void visit(Not e) {
		return this.checkUnary(e);
	}

	// AType checking visit and check expressions

	@Override
	public Void visit(Form form) {

		for (AQuestion q : form.getFormBody()) {
			q.accept(this);
		}
		return null;
	}

	@Override
	public Void visit(NormalQ nq) {
		this.checkQ(nq);
		typeSaver.saveType(nq.getQId().getID(), nq.getQType());
		typeSaver.saveQLabel(nq.getQId().getID(), nq.getQLabel());
		return null;
	}

	@Override
	public Void visit(CalculatedQ cq) {
		this.checkQ(cq);

		String identifier = cq.getQId().getID();
		typeSaver.saveType(identifier, cq.getQType());
		typeSaver.saveQLabel(identifier, cq.getQLabel());

		cq.getExpr().accept(this);

		 checkCompatibalityType(cq.getExpr(), cq.getQType());

		return null;
	}

	@Override
	public Void visit(IfQ ifQ) {
		for (AQuestion q : ifQ.getIfBody()) {
			q.accept(this);
		}

		ifQ.getIfCondition().accept(this);
		AExpression e = ifQ.getIfCondition();

		 checkCompatibalityType(e, e.getType());

		return null;

	}

	@Override
	public Void visit(IfElseQ elseQ) {

		for (AQuestion q : elseQ.getIfBody()) {
			q.accept(this);
		}

		for (AQuestion q : elseQ.getIfElseQ()) {
			q.accept(this);
		}

		elseQ.getIfCondition().accept(this);
		AExpression e = elseQ.getIfCondition();

		 checkCompatibalityType(e, e.getType());

		return null;
	}

	@Override
	public Void visit(Par e) {

		e.getParExpression().accept(this);
		 checkCompatibalityType(e.getParExpression(), e.getType());
		return null;
	}

}