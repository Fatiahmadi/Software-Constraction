package main.coordinator;

import gui.QFrame;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import questionair.absTree.form.Form;
import questionair.semanticAnalysis.TypeChecking;
import questionair.semanticAnalysis.error.Error;
import questionair.semanticAnalysis.error.PopUpError;
import questionair.semanticAnalysis.error.Warning;

//import main.coordinator.CoordinationFiles;

public class CoordinateGUI {
	private final CoordinationFiles file;
	private final String filename;
	private final Form form;
	private final QFrame qF;
	private final TypeChecking typeChecker;

	public CoordinateGUI() {

		this.typeChecker = new TypeChecking();// check if it is goed

		this.file = new CoordinationFiles();
		this.filename = file.getFileString();
		this.qF = new QFrame();
		this.form = new AbsSynTreeBuilder().formBuilder(filename);
	}

	public void runGUI(JFrame frame) {
		if (!formEmpty()) {
			if (formIsCorrect()) {
				final JPanel panel = qF.createFormOnFrame(form);
				frame.getContentPane().removeAll();
				frame.getContentPane().add(panel);
				frame.setTitle(form.getFormIdentifier().toString());
				frame.pack();
				frame.setVisible(true);
			} else {
				frame.getContentPane().removeAll();
				frame.revalidate();
				frame.repaint();
				showErrors();
			}
		}
	}

	public boolean formIsCorrect() {
		return checkQuestionnaire(form);
	}

	public boolean checkQuestionnaire(Form form) {
		form.accept(typeChecker);
		return noErrors();
	}

	public boolean formEmpty() {
		return form == null;
	}

	public boolean noErrors() {
		return this.typeChecker.isCorrect();
	}

	public void showErrors() {
		new PopUpError(getError(), getWarning());
	}

	public List<Error> getError() {
		return this.typeChecker.getError();
	}

	public List<Warning> getWarning() {
		return this.typeChecker.getWarning();
	}
}