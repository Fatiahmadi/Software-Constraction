package gui.question;

import gui.eventHandler.Updater;

import java.util.LinkedHashSet;

import questionair.evaluation.BooleanValue;
import questionair.evaluation.Value;

public class IfElseQUI implements UIQ, QUpdaterInterface {
	private final LinkedHashSet<UIQ> ifBody, elseBody;
	private final Updater updater;
	private final String trueValue;
	private final BooleanValue setTrue, setFalse;

	public IfElseQUI(Updater updater) {
		this.ifBody = new LinkedHashSet<UIQ>();
		this.elseBody = new LinkedHashSet<UIQ>();
		this.updater = updater;
		this.trueValue = new BooleanValue(true).toString();
		this.setTrue = new BooleanValue(true);
		this.setFalse = new BooleanValue(false);
	}

	public void showIfBody(UIQ qSet) {
		ifBody.add(qSet);
	}

	public void showElseBody(UIQ accept) {
		elseBody.add(accept);
	}

	@Override
	public void setValue(Value value) {
		assert false : "GUI Error. setValue() can't be used it if-body questions.";
	}

	@Override
	public void setVisibilityValue(Value value) {
		for (UIQ question : ifBody) {
			question.setVisibilityValue(value);
		}

		for (UIQ question : elseBody) {
			if (trueValue.equals(value.toString())) {
				question.setVisibilityValue(setFalse);
			} else {
				question.setVisibilityValue(setTrue);
			}
		}
	}

	@Override
	public Updater getUpdater() {
		return this.updater;
	}

	@Override
	public void updateGUI() {
		this.updater.updateGUI(this);
	}
}
