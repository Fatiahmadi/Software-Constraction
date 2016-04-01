package questionair.semanticAnalysis;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.expression.Binary;
import questionair.absTree.expression.ExpressionVisitor;
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
import questionair.absTree.expression.unary.AbsUnary;
import questionair.absTree.expression.unary.Minus;
import questionair.absTree.expression.unary.Not;
import questionair.absTree.expression.unary.Plus;
import questionair.absTree.form.Form;
import questionair.absTree.form.FormVisitor;
import questionair.absTree.question.AbsQuestion;
import questionair.absTree.question.CalculatedQ;
import questionair.absTree.question.IfElseQ;
import questionair.absTree.question.IfQ;
import questionair.absTree.question.NormalQ;
import questionair.absTree.question.QuestionVisitor;
import questionair.absTree.type.AbsType;
import questionair.absTree.type.IntegerT;
import questionair.semanticAnalysis.error.Error;
import questionair.semanticAnalysis.error.ErrorWarningList;
import questionair.semanticAnalysis.error.Warning;

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
		AbsType type = q.getQType();

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
		String QLabel = q.getQText();

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

	public Void checkExpression(Binary e) {
		e.getLeftExpression().accept(this);
		e.getRightExpression().accept(this);

		 checkCompatibalityType(e.getLeftExpression(), e.getType());
		 checkCompatibalityType(e.getRightExpression(), e.getType());
		return null;
	}

	public Void checkComparison(Binary e) {
		e.getLeftExpression().accept(this);
		e.getRightExpression().accept(this);

		 checkCompatibalityType(e.getLeftExpression(), new IntegerT());
		 checkCompatibalityType(e.getRightExpression(), new IntegerT());
		return null;
	}

	public Void checkUnary(AbsUnary e) {
		e.getUnaryExpression().accept(this);

		 checkCompatibalityType(e.getUnaryExpression(), e.getType());
		return null;
	}

	public Void checkEqual(Binary e) {
		e.getLeftExpression().accept(this);
		e.getRightExpression().accept(this);

		 checkCompatibalityType(e.getLeftExpression(), e
				.getRightExpression().getType());

		return null;
	}
	public void checkCompatibalityType(AbsExpression e, AbsType t) {
		if (e.getType() != null) {
			if (e.getType().isCompatibleToType(t)) {
				return;
			}
			this.errorCollector
					.addErrorToErrorList(" Error:Type of the AbsExpression *"
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

	// AbsType checking visit and check literal
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

	// AbsType checking visit and check calculation
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

	// AbsType checking visit and check comparison expressions
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

	// AbsType checking visit and check Equal end NotEqual expressions
	@Override
	public Void visit(Equal e) {
		return this.checkEqual(e);
	}

	@Override
	public Void visit(NotEqual e) {
		return this.checkEqual(e);
	}

	// AbsType checking visit and check AbsUnary expressions
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

	// AbsType checking visit and check expressions

	@Override
	public Void visit(Form form) {

		for (AbsQuestion q : form.getBody()) {
			q.accept(this);
		}
		return null;
	}

	@Override
	public Void visit(NormalQ nq) {
		this.checkQ(nq);
		typeSaver.saveType(nq.getQId().getID(), nq.getQType());
		typeSaver.saveQLabel(nq.getQId().getID(), nq.getQText());
		return null;
	}

	@Override
	public Void visit(CalculatedQ cq) {
		this.checkQ(cq);

		String identifier = cq.getQId().getID();
		typeSaver.saveType(identifier, cq.getQType());
		typeSaver.saveQLabel(identifier, cq.getQText());

		cq.getE().accept(this);

		 checkCompatibalityType(cq.getE(), cq.getQType());

		return null;
	}

	@Override
	public Void visit(IfQ ifQ) {
		for (AbsQuestion q : ifQ.getIfQ()) {
			q.accept(this);
		}

		ifQ.getE().accept(this);
		AbsExpression e = ifQ.getE();

		 checkCompatibalityType(e, e.getType());

		return null;

	}

	@Override
	public Void visit(IfElseQ ifelseQ) {

		for (AbsQuestion q : ifelseQ.getIfQ()) {
			q.accept(this);
		}

		for (AbsQuestion q : ifelseQ.getIfElseQ()) {
			q.accept(this);
		}

		ifelseQ.getE().accept(this);
		AbsExpression e = ifelseQ.getE();

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