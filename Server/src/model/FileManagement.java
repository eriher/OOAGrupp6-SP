/**
 * Read a txt file from hdd and return it to whomever needs it,
 * Save a txt file to hdd.
 * Return password for specific persNr
 * 
 * @author Henrik Johansson
 * @version 2013-02-13
 */


package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManagement {
	private final String USERSLIST_FILEPATH = "users.bin";		//Chose here filename or path.
	public FileManagement(){
		
	}
	
	/**
	 * 	Get password for "perNr" from file "PERSNR_PASSWORD_STATUS_FILEPATH"
	 * @param perNr
	 * @return	password if exist both perNr and Password, null otherwise.
	 */
	/*public String getPassword( String perNr){
		try {
			Scanner in = new Scanner(new FileReader(PERSNR_PASSWORD_STATUS_FILEPATH) );
			
			while(in.hasNextLine() ){
				String fullText = in.nextLine();
				if(fullText.contains( perNr)  ){
					//Found row with personalnumber
					in.close();
					String[] persNrPasswordStatus = fullText.split("\\s+"); //Split on blanks either space or tab
					return persNrPasswordStatus[1];		//Return password for perNr
				}
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("null returned from file manager");
		return null;
		
	}*/
	
	
		//TODO might want to add a private method that both getStatus and getPassword uses so that we wont have as much duplicated code
	/**
	 * Get status of personnumber admin or emplployee
	 * @param perNr
	 * @return	status of person with personalNr as parameter
	 */
	/*public String getStatus(String perNr){
		try{
			Scanner in = new Scanner(new FileReader(PERSNR_PASSWORD_STATUS_FILEPATH) );
			
			while(in.hasNextLine() ){
				String lineText = in.nextLine();
				if(lineText.contains(perNr) ){
					//Found row with personalnumber
					in.close();
					String[] persNrPasswordStatus = lineText.split("\\s+");		//Split on blanks either space or tab
					return persNrPasswordStatus[2]; //Returns status for persnr
				}
			}
			in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("null returned from file manager");
		return null;
		
	}*/
	
	
	/**
	 * @param fileName
	 */
	public void writeUsersFile(String fileName){				
		try{
			Users users = new Users();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName) );
			out.writeObject(users);
		}catch(IOException e){
			System.out.println("Could not write file");
		}
	}
	
	public void writeUsersFile(String fileName, Users users){				
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName) );
			out.writeObject(users);
		}catch(IOException e){
			System.out.println("Could not write file");
		}
	}
	
	public void writeUsersFile(Users users){				
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERSLIST_FILEPATH) );
			out.writeObject(users);
		}catch(IOException e){
			System.out.println("Could not write file");
		}
	}
	
	
	public Users getUsersList(){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(USERSLIST_FILEPATH));
			return (Users)in.readObject();
		}catch(Exception e){
			return null;
		}
	}
	
	
	
	
	
	

}
