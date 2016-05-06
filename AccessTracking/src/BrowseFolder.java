import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UIManager.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
// THIS CLASS ASKS THE USER TO SELECT A DIRECTORY
// AND THEN RETURNS A PATH STRING TO BE USED FOR SOMETHING ELSE

public class BrowseFolder { 
	
	public BrowseFolder() {
	}
	
	/*
	*Opens a file dialog and requests a folder and returns a string
	*@param title sets the title of the Window
	*/
	public static String getBrowseFolder(String title) {
		JFileChooser chooser = new JFileChooser();	
		chooser.setDialogTitle(title);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//Sets the default location for the explorer
		chooser.setCurrentDirectory(new File("C:/"));
		int returnVal = chooser.showOpenDialog(chooser);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		  String nx = chooser.getSelectedFile().getAbsolutePath() + "\\"; 
		  return nx;
		} else {
			String ex = "Action Canceled";
			return ex;
		}		
	}
	
	/*
	*Opens a file dialog and requests a file and returns a string
	*@param title sets the title of the Window
	*/
	public static String getBrowseFile(String title) {
		JFileChooser chooser = new JFileChooser();	
		chooser.setDialogTitle(title);
		FileFilter filter = new FileNameExtensionFilter("Databases", "mdb", "accdb");
		chooser.addChoosableFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		//Sets the default location for the explorer
		chooser.setCurrentDirectory(new File("C:/"));
		int returnVal = chooser.showOpenDialog(chooser);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		  String nx = chooser.getSelectedFile().getAbsolutePath(); 
		  return nx;
		} else {
			String ex = "Action Canceled";
			return ex;
		}		
	}
	
}


