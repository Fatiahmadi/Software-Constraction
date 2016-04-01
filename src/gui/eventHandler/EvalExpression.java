package gui.eventHandler;

import gui.question.UIQ;
import questionair.absTree.expression.AbsExpression;
import questionair.evaluation.BooleanValue;
import questionair.evaluation.EvalVisitor;
import questionair.evaluation.Value;
import questionair.evaluation.ValueSaver;

public class EvalExpression {

	private ValueSaver valueSaver;
	private final EvalVisitor evalVisitor;
	private final AbsExpression e;
	private final UIQ setQ;

	public EvalExpression(ValueSaver valueSaver, AbsExpression e, UIQ setQ) {
		this.valueSaver = valueSaver;
		this.e = e;
		this.evalVisitor = new EvalVisitor(this.valueSaver);
		this.setQ = setQ;
		setValueInGUI();
	}

	public Value evaluate() {
		Value evaluatedValue = e.accept(this.evalVisitor);
		return evaluatedValue;
	}

	public void setValueInGUI() {
		String insertedValue = evaluate().toString();
		String regexDigits = "[-+]?\\d+(\\.\\d+)?";

		if (!insertedValue.isEmpty() && insertedValue.matches(regexDigits)) {
			this.setQ.setValue(evaluate());
		}

		String trueValue = new BooleanValue(true).toString();
		String falseValue = new BooleanValue(false).toString();

		if (trueValue.equals(insertedValue) || falseValue.equals(insertedValue)) {
			this.setQ.setVisibilityValue(evaluate());
		}
	}
}