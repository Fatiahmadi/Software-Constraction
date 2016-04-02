package user_Interface.eventHandler;

import java.util.Set;

import q.aTree.expr.AExpression;
import q.evaluation.ValueSaver;
import user_Interface.QRendering;
import user_Interface.q.UINormalQ;
import user_Interface.q.UIQ;

public class Updater {
	private final QRendering render;
	private final ValueSaver valueSaver;
	private final AExpression e;
	private EvalExpression evaluator=null ;

	public Updater(AExpression e, QRendering render, ValueSaver valueSaver) {
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