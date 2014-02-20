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
	
	public FileManagement(){
		// Create hashmap
	}
	
	/**
	 * @param file
	 */
	public void openFile(String file){
		// Add to hashmap
		// Open
	}
	
	/**
	 * 
	 */
	private void closeFile(){
		
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
