//This class lists files and or folders and outputs the results
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.PrintWriter;
public class ListFilesUtil {   
	/**
     * List all the files and folders from a directory
     * @param directoryName to be listed
     */
    public void listFilesAndFolders(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            System.out.println(file.getName());
        }
    }
    /**
     * List all the files under a directory
     * @param directoryName to be listed
     */
    public void listFiles(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile()){
                System.out.println(file.getName());
            }
        }
    }
    /**
     * List all the folder under a directory
     * @param directoryName to be listed
     */
    public void listFolders(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isDirectory()){
                System.out.println(file.getName());
            }
        }
    }
    /**
     * List all files from a directory and its subdirectories
     * @param directoryName to be listed
     */
    public void listFilesAndFilesSubDirectories(String directoryName){
        File directory = new File(directoryName);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile()){
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()){
                listFilesAndFilesSubDirectories(file.getAbsolutePath());
            }
        }
    }    
    /**
     * List all folders and sub-folders filtered to .jpg
     * @param directoryName to be listed
     */ 
    public void listFilesInDirectory(String pathString) {
    	  class MyFilter implements FileFilter {
    	    public boolean accept(File file) {
    	      return !file.isHidden() && file.getName().endsWith(".JPG");
    	    }
    	  }
    	  File directory = new File(pathString);
    	  File[] files = directory.listFiles(new MyFilter());

    	  for (File fileLoop : files) {
    	    //ADDED
              if (fileLoop.isFile()){
                 System.out.println(fileLoop.getAbsolutePath());
              } else if (fileLoop.isDirectory()){
            	  listFilesAndFilesSubDirectories(fileLoop.getAbsolutePath());
              }
    		  System.out.println(fileLoop.getName());
    	  }
    	}    
    /**
     * List all folders and sub-folders
     * @param directoryName to be listed
     */ 
    public void FileList(String path)  {    	
    	File dir = new File(path);
    	File[] list = dir.listFiles();
    	for (File fileloop : list) {
    		if (fileloop.isFile()) {
    			System.out.println(fileloop.getName());
    		} else if (fileloop.isDirectory()){
    			FileList(fileloop.getAbsolutePath());
    		}
    	}
    }
    //Main
    public static void main (String[] args) {
    	final String directoryWindows = BrowseFolder.getBrowseFolder("Select a directory");
    	//Calls filter function
    	ListFilesUtil listFilesUtil = new ListFilesUtil();
    	listFilesUtil.FileList(directoryWindows);
    	//ListFilesUtil lfu = new ListFilesUtil();
    	//lfu.FileList(directoryWindows);
    }
}
