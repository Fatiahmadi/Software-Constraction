package gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import main.coordinator.CoordinateGUI;
import questionair.absTree.form.Form;

public class QFrame {

	private JPanel contentPane;
	private JPanel jP = new JPanel();
	private JFrame jF;
	private JMenuBar jMB;
	private JMenu upload, close;

	public QFrame() {
	}

	public void baseQFrame() {
		jF = new JFrame("Questionnaire");
		jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// for the close
		// button exit the
		// aaplication
		jF.setBounds(100, 100, 450, 300);
		jF.setPreferredSize(new Dimension(600, 400));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));

		contentPane.add(jP);

		jF.setContentPane(contentPane);

		jF.setJMenuBar(addMenutoMenuBar());

		jF.pack();
		jF.setVisible(true);
	}

	public JPanel createFormOnFrame(Form f) {
		jP = QRendering.make(f);

		return jP;
	}

	public JMenuBar addMenutoMenuBar() {
		jMB = new JMenuBar();

		upload = new JMenu("upload the form");
		close = new JMenu("Close the program");

		jMB.add(upload);
		jMB.add(close);

		addActionToMenuItems();

		return jMB;
	}

	public void addActionToMenuItems() {

		upload.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent arg0) {
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
			}

			@Override
			public void menuSelected(MenuEvent arg0) {
				final CoordinateGUI manager = new CoordinateGUI();
				manager.runGUI(jF);
			}
		});

		close.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent arg0) {
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
			}

			@Override
			public void menuSelected(MenuEvent arg0) {
				closeFrame();
			}
		});
	}

	// pup op option to close the frame
	public void closeFrame() {
		int selectedOption = JOptionPane.showConfirmDialog(null,
				"Do you want to close this programm?", "Close form",
				JOptionPane.YES_NO_OPTION);

		if (selectedOption == JOptionPane.YES_OPTION) {
			jF.dispatchEvent(new WindowEvent(jF, WindowEvent.WINDOW_CLOSING));
		}
	}

}
