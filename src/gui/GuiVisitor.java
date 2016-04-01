package gui;

import gui.eventHandler.Updater;
import gui.question.IfElseQUI;
import gui.question.UICalQ;
import gui.question.UINormalQ;
import gui.question.UIQ;
import gui.widget.WidComponent;
import gui.widget.WidVisitor;

import javax.swing.JLabel;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.question.AbsQuestion;
import questionair.absTree.question.CalculatedQ;
import questionair.absTree.question.IfElseQ;
import questionair.absTree.question.IfQ;
import questionair.absTree.question.NormalQ;
import questionair.absTree.question.QuestionVisitor;
import questionair.evaluation.ValueSaver;

public class GuiVisitor implements QuestionVisitor<UIQ> {
	private final QRendering gui;
	private final ValueSaver valueSaver;

	public GuiVisitor(QRendering gui, ValueSaver valueSaver) {
		this.gui = gui;
		this.valueSaver = valueSaver;
	}

	public WidComponent widget(NormalQ nq) {
		return nq.getQType().accept(
				new WidVisitor(nq.getQId().getID(), nq.getQType(),
						this.valueSaver));

	}

	public Updater sendToUpdater(AbsExpression e) {
		return new Updater(e, gui, valueSaver);
	}

	@Override
	public UIQ visit(NormalQ nq) {

		UINormalQ nQUI = new UINormalQ(nq.getQId().getID(), new JLabel(
				nq.getQText()), this.widget(nq));

		gui.putWidgetRepository(nq.getQId().getID(), nQUI);
		return nQUI;
	}

	@Override
	public UIQ visit(CalculatedQ cq) {
		UICalQ cQUI = new UICalQ(cq.getQId().getID(),
				new JLabel(cq.getQText()), this.widget(cq),
				this.sendToUpdater(cq.getE()));

		gui.putWidgetRepository(cq.getQId().getID(), cQUI);
		cQUI.updateGUI();
		return cQUI;
	}

	@Override
	public UIQ visit(IfElseQ ifElseQ) {
		IfElseQUI ifElseqUI = new IfElseQUI(this.sendToUpdater(ifElseQ.getE()));

		for (AbsQuestion q : ifElseQ.getIfElseQ()) {
			ifElseqUI.showIfBody(q.accept(this));
		}

		for (AbsQuestion q : ifElseQ.getIfElseQ()) {
			ifElseqUI.showElseBody(q.accept(this));
		}

		ifElseqUI.updateGUI();
		return ifElseqUI;
	}

	@Override
	public UIQ visit(IfQ ifQ) {
		IfElseQUI ifqUI = new IfElseQUI(this.sendToUpdater(ifQ.getE()));

		for (AbsQuestion q : ifQ.getIfQ()) {
			ifqUI.showIfBody(q.accept(this));
		}

		ifqUI.updateGUI();
		return ifqUI;
	}

}
