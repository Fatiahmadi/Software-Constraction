package user_Interface.eventHandler;

import q.aTree.expr.AExpression;
import q.evaluation.BooleanV;
import q.evaluation.EvalVisitor;
import q.evaluation.Value;
import q.evaluation.ValueSaver;
import user_Interface.q.UIQ;

public class EvalExpression {

	private ValueSaver valueSaver;
	private final EvalVisitor evalVisitor;
	private final AExpression e;
	private final UIQ setQ;

	public EvalExpression(ValueSaver valueSaver, AExpression e, UIQ setQ) {
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
		String regularExprresionDigits = "[-+]?\\d+(\\.\\d+)?";
		
// 
		if (!insertedValue.isEmpty() && insertedValue.matches(regularExprresionDigits)) {
			this.setQ.setValue(evaluate());
		}

		String trueValue = new BooleanV(true).toString();
		String falseValue = new BooleanV(false).toString();

		if (trueValue.equals(insertedValue) || falseValue.equals(insertedValue)) {
			this.setQ.setVisibilityValue(evaluate());
		}
	}
}