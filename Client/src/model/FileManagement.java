/**
 * Handles reading of files
 * 
 * @author	Erik Hermansson
 * @version 2014-02-22
 */

package model;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileManagement {
	private static FileManagement fm;

	private FileManagement() {

	}

	/**
	 * Singleton method.
	 * 
	 * @return The single instance of the object.
	 */
	public static FileManagement getInstance() {
		if (fm == null)
			fm = new FileManagement();
		return fm;
	}

	/**
	 * Reads one line and returns it as String
	 * 
	 * @param file
	 *            Name of the file to be read.
	 * @return First line of file as String
	 */
	public String readLine(String file)
	{
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			line = br.readLine();
			return line;
			// IOException
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
