package user_Interface;

import javax.swing.JLabel;

import q.aTree.expr.AExpression;
import q.aTree.question.AQuestion;
import q.aTree.question.CalculatedQ;

import q.aTree.question.IfElseQ;
import q.aTree.question.IfQ;
import q.aTree.question.NormalQ;
import q.aTree.question.QuestionVisitor;
import q.evaluation.ValueSaver;
import user_Interface.eventHandler.Updater;
import user_Interface.q.IfElseQUI;
import user_Interface.q.UICalQ;
import user_Interface.q.UINormalQ;
import user_Interface.q.UIQ;
import user_Interface.widget.WidComponent;
import user_Interface.widget.WidVisitor;

public class UI_Visitor implements QuestionVisitor<UIQ> {
	private final QRendering user_interface;
	private final ValueSaver valueSaver;

	public UI_Visitor(QRendering user_interface, ValueSaver valueSaver) {
		this.user_interface = user_interface;
		this.valueSaver = valueSaver;
	}

	public WidComponent widget(NormalQ nq) {
		return nq.getQType().accept(
				new WidVisitor(nq.getQId().getID(), nq.getQType(),
						this.valueSaver));

	}

	public Updater sendToUpdater(AExpression e) {
		return new Updater(e, user_interface, valueSaver);
	}

	@Override
	public UIQ visit(NormalQ nq) {

		UINormalQ nQUI = new UINormalQ(nq.getQId().getID(), new JLabel(
				nq.getQLabel()), this.widget(nq));

		user_interface.putWidgetRepository(nq.getQId().getID(), nQUI);
		return nQUI;
	}

	@Override
	public UIQ visit(CalculatedQ cq) {
		UICalQ cQUI = new UICalQ(cq.getQId().getID(),
				new JLabel(cq.getQLabel()), this.widget(cq),
				this.sendToUpdater(cq.getExpr()));

		user_interface.putWidgetRepository(cq.getQId().getID(), cQUI);
		cQUI.updateGUI();
		return cQUI;
	}
	@Override
	public UIQ visit(IfQ ifQ) {
		IfElseQUI ifqUI = new IfElseQUI(this.sendToUpdater(ifQ.getIfCondition()));

		for (AQuestion q : ifQ.getIfBody()) {
			ifqUI.showIfBody(q.accept(this));
		}

		ifqUI.updateGUI();
		return ifqUI;
	}
	@Override
	public UIQ visit(IfElseQ elseQ) {
		IfElseQUI ifElseqUI = new IfElseQUI(this.sendToUpdater(elseQ.getIfCondition()));

		for (AQuestion q : elseQ.getIfBody()) {
			ifElseqUI.showIfBody(q.accept(this));
		}

		for (AQuestion q : elseQ.getIfElseQ()) {
			ifElseqUI.showElseBody(q.accept(this));
		}

		ifElseqUI.updateGUI();
		return ifElseqUI;
	}

	

}
