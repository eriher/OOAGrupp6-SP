GLÖM INTE ATT ÄNDRA DESC.
/**
 * Write a description of class FileManagement here.
 * 
 * @author 
 * @version 2013-02-20
 */

package model;

import java.util.HashMap;

public class FileManagement {
	private HashMap<String, FILE> fileList; // Contains all opened files
	private static FileManagement fileManagement;
	
	private FileManagement(){
		// Create hashmap
	}
	
	public static FileManagement getInstance(){
		if(fileManagement == null){
			fileManagement = new FileManagement();
		}
		
		return fileManagement;
	}
	
	/**
	 * @param file
	 */
	public void openReadFile(String file){
		// Add to hashmap
		// Open read
	}
	
	/**
	 * @param file
	 */
	public void openWriteFile(String file){
		// Add to hashmap
		// Open write
	}
	
	/**
	 * 
	 */
	private void closeReadFile(){
		
	}
	
	/**
	 * 
	 */
	private void closeWriteFile(){
		
	}
	
	/**
	 * @return
	 */
	public String readFile(){
		// Check if file is already open for writing.
		// Read
		// Return read data
	}
	
	/**
	 * 
	 */
	public void writeFile(){
		// Check if file is already open for reading.
		// Write
	}
}
