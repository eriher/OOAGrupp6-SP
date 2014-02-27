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
	 * This is a "first time method" it should only be used if there are no admin users in users.bin. 
	 * This will make a complete new file and all old users will be removed!
	 * 
	 * @param fileName
	 */
	public void writeUsersFile(String fileName){				
		try{
			Users users = new Users();
			users.add("Admin", "Admin" , "Admin");
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName) );
			out.writeObject(users);
		}catch(IOException e){
			System.out.println("Could not write file");
		}
	}
	
	
	/**
	 * Saves users to binfile with a specific name as parameter.
	 * 
	 * @param fileName
	 * @param users
	 */
	public void writeUsersFile(String fileName, Users users){				
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName) );
			out.writeObject(users);
		}catch(IOException e){
			System.out.println("Could not write file");
		}
	}
	
	
	/**
	 * Writes users to default binfile choosen as constant.
	 * 
	 * @param users
	 */
	public void writeUsersFile(Users users){				
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERSLIST_FILEPATH) );
			out.writeObject(users);
		}catch(IOException e){
			System.out.println("Could not write file");
		}
	}
	
	
	
	/**
	 * Recieve the Users.
	 * 
	 * @return Users if there are som, null ow
	 */
	public Users getUsersList(){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(USERSLIST_FILEPATH));
			return (Users)in.readObject();
		}catch(Exception e){
			return null;
		}
	}
	
	
	
	
	
	

}
