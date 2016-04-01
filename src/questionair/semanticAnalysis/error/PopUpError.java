package questionair.semanticAnalysis.error;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopUpError{
	private List<Error> errorCollection;
	private List<Warning> warningCollection;
	private JLabel errorLabel, warningLabel;
	
	
	public PopUpError(List<Error> errorCollection, List<Warning> warningCollection) {
		this.errorCollection = errorCollection;
		this.warningCollection = warningCollection;
		addErrorWarningLabelToContainer();
	}
	
	public void addErrorWarningLabelToContainer() {	
		JDialog dialog = new JDialog();
		Container contentPane = dialog.getContentPane();
		contentPane.setLayout(new GridLayout(0, 1));
		errorLabel = new JLabel("Errors found", JLabel.CENTER);
		errorLabel.setForeground(Color.red);
		errorLabel.setVisible(false);
		contentPane.add(errorLabel);
		
		for(Error errorMessage : this.errorCollection) {
			if (errorMessage !=null) {
				errorLabel.setVisible(true);
			}
			System.out.println("Errors: " + errorMessage);
			contentPane.add(addErrorLabel(errorMessage));
		
		}
		
		warningLabel = new JLabel("Warnings found", JLabel.CENTER);
		warningLabel.setForeground(Color.red);
		warningLabel.setVisible(false);
		contentPane.add(warningLabel);
		
		for(Warning warningMessage : this.warningCollection) {
			if (warningMessage !=null) {
				warningLabel.setVisible(true);
			}
			contentPane.add(addWarningLabel(warningMessage));
		}
		
		contentPane.setBackground(Color.white);
		contentPane.setVisible(true);
		dialog.setTitle("Typechecker: errors found");
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialog.setMinimumSize(new Dimension(400,200));
		dialog.setVisible(true);
	}
	
	public JLabel addErrorLabel(Error error) {
		return new JLabel("  " + error.getErrorWarningText() + " ");
	}
	
	public JLabel addWarningLabel(Warning w) {
		return new JLabel("  "+ w.getErrorWarningText() + " ");
	}

}