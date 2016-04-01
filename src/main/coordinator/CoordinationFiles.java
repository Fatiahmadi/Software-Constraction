package main.coordinator;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class CoordinationFiles {
	final JFileChooser fileSelector = new JFileChooser();
	
	public String getFileString() {
		try {
			return fileToString(fileName());
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog (null, "You didn't choose (existing) file.", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		return "";
	}
	
	public String fileToString(String filename) throws FileNotFoundException  {
		File file = new File(filename);
		StringBuilder fileContents = new StringBuilder((int)file.length());
		Scanner scan = new Scanner(file);
		String lineSeparator = System.getProperty("line.separator");
		
		try {
		    while(scan.hasNextLine()) {        
		        fileContents.append(scan.nextLine() + lineSeparator);
		    }
		return fileContents.toString();
		} 
		finally {
		    scan.close();
		}
	}
	
	public String fileName() {
		customazefileSelector(fileSelector, "Choose a tax form");
		fileFilter("Quistionnaire (.ql)", ".ql");
		int result = fileSelector.showOpenDialog(new JOptionPane());	
		
		if (!(result == JFileChooser.APPROVE_OPTION)) {
			return "";
		}
		   
		File selectedFile = fileSelector.getSelectedFile();
		String fileName = selectedFile.getAbsolutePath();
		return fileName;
        
	}
	
	public JFileChooser customazefileSelector(JFileChooser fileSelector, String title) {
		fileSelector.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileSelector.setCurrentDirectory(new File("C:\\")); 
		fileSelector.setDialogTitle(title);
		fileSelector.setApproveButtonText("Load form");
		
		return fileSelector;
	}
	
	public JFileChooser fileFilter(final String description, final String extention) {
		fileSelector.setFileFilter(new FileFilter() {        
			 
			@Override
		       public String getDescription() {
		            return description;
		        }
		 
		   @Override
		       public boolean accept(File f) {
		            if (f.isDirectory()) {
		                return true;
		            } else {
		                return f.getName().toLowerCase().endsWith(extention);
		            }
		        }
		    });
		return fileSelector;
		
	}
}