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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManagement {
	public FileManagement(){
		
	}
	
	/**
	 * @param fileName
	 * @param perNr
	 * @return
	 */
	public String getPassword(String fileName, String perNr){
		try {
			Scanner in = new Scanner(new FileReader(fileName) );
			
			while(in.hasNextLine() ){
				String fullText = in.nextLine();
				if(fullText.contains( perNr)  ){
					//Hittat raden med perssonnummret
					in.close();
					String[] persNrPassword = fullText.split(" ");
					return persNrPassword[1];		//Return password for perNr
				}
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("null returned from file manager");
		return null;
		
	}
	
	public void writeFile(String fileName){
		try {
			fileName ="test.txt";
			PrintWriter out = new PrintWriter(new FileWriter(fileName));
			out.write("hejsan i texten test.txt");
			out.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
