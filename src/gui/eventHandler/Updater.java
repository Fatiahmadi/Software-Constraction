package gui.eventHandler;

import gui.QRendering;
import gui.question.UINormalQ;
import gui.question.UIQ;

import java.util.Set;

import questionair.absTree.expression.AbsExpression;
import questionair.evaluation.ValueSaver;

public class Updater {
	private final QRendering render;
	private final ValueSaver valueSaver;
	private final AbsExpression e;
	private EvalExpression evaluator = null;

	public Updater(AbsExpression e, QRendering render, ValueSaver valueSaver) {
		this.valueSaver = valueSaver;
		this.render = render;
		this.e = e;
	}

	public void updateGUI(UIQ questionValueSetter) {
		this.evaluator = prepareEvaluator(questionValueSetter);

		Set<String> idKeys = valueSaver.getIDkeys();
		for (String key : idKeys) {
			if (!this.render.containsNormalQUI(key)) {
				continue;
			}

			UINormalQ nq = this.render.getIDNormalQUI(key);
			nq.getWidCom().addDocListener(evaluator);
		}
	}

	public EvalExpression getEvaluator() {
		return this.evaluator;
	}

	public EvalExpression prepareEvaluator(UIQ questionValueSetter) {
		if (evaluator != null) {
			return this.evaluator;
		}
		return this.evaluator = new EvalExpression(valueSaver, e,
				questionValueSetter);
	}
}