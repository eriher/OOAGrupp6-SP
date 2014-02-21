/**
 * Handles file management for reading and saving schedule objects to file
 * 
 * @author Simon Planhage
 * @version 2014-02-20
 */

package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileManagement {

	
	/**
	 * Saves the current list of schedules to a file named personNummer.ser
	 * Some debug information remains
	 * @param personNummer 
	 */
	public void saveSchedules(int personNummer) {
		
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		try {
		        fout = new FileOutputStream(personNummer + ".ser", true);
		        oos = new ObjectOutputStream(fout);
		        oos.writeObject(ScheduleHandler.scheduleList);
		        System.out.println("Schedule has been saved");
		} catch (Exception e) {
		        e.printStackTrace();
		} finally {
				try {
					oos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	
	/**
	 * Loads the file with saved Schedule objects
	 * Some debug information remains
	 * @param personNummer
	 */
	@SuppressWarnings("unchecked")
	public static void loadSchedules(int personNummer) {
		ObjectInputStream objInStream = null;
		FileInputStream streamIn = null;
		 try {
		        streamIn = new FileInputStream(0 + ".ser");
		        objInStream = new ObjectInputStream(streamIn);
				ArrayList<Schedule> readCase = (ArrayList<Schedule>) objInStream.readObject();
		        ScheduleHandler.scheduleList.addAll(readCase);
		        System.out.println("Schedule has been loaded");


		    } catch (Exception e) {

		        e.printStackTrace();
		 } finally {
		        if(objInStream != null){
		            try {
						objInStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		         } 
		 }
	}
}
