/**
 * Read a txt file from hdd and return it to whomever needs it,
 * Save a txt file to hdd.
 * Return password for specific persNr
 * 
 * @author Henrik Johansson
 * @version 2013-02-13
 */


package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FileManagement {
	public FileManagement(){
		long longPrNr = 123456789101L;
					
		checkPassword("test.txt", longPrNr);
	}
	
	public String checkPassword(String fileName, long perNr){
		try {
			Scanner in = new Scanner(new FileReader(fileName) );
			
			while(in.hasNextLine() ){
				String fullText = in.nextLine();
				if(fullText.contains( Long.toString(perNr)) ){
					//Hittat raden med perssonnummret
					in.close();
					String[] persNrPassword = fullText.split(" ");
					System.out.println();
					return persNrPassword[1];		//Return password for perNr
				}
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void writeFile(){
		
	}
	
	

}
