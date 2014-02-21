/**
 * Represents one week with seven days
 * 
 * @author Simon Planhage
 * @version 2014-02-20
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

import org.joda.time.*;

public class Schedule implements Serializable {

	private static final long serialVersionUID = -4841663954805333902L;

	//Vecka som schemat representerar
	int week;
	
	//Lista med veckodagarna som hör till
	private ArrayList<Day> days = new ArrayList<Day>();
	
	
	/**
	 * Creates a new week with the given week number
	 * @param week
	 */
	public Schedule(int week) {
		this.week = week;
		this.populateWeek();
	}
	
	/**
	 * Populates the schedule with empty days, ranging from 0 to 6
	 * 
	 */
	private void populateWeek() {
		
		for(int i = 0; i < 7; i++) {
			Day day = new Day();
			day.setDayNr(i);
			days.add(day);
			
		}
	}
	
	/**
	 * CheckIn on the current day and time
	 * 
	 */
	public void checkIn() {
		DateTime currentTime = new DateTime();
		int currentDay = currentTime.getDayOfWeek();
		Day aktuellDag2 = days.get(currentDay);
		aktuellDag2.checkInTime = currentTime;
		System.out.println("CheckIn: " + currentTime);
	}

	
	/**
	 * CheckOut on the current day and time
	 * 
	 */
	public void checkOut() {
		DateTime currentTime = new DateTime();
		int currentDay = currentTime.getDayOfWeek();
		Day aktuellDag2 = days.get(currentDay);
		aktuellDag2.checkOutTime = currentTime;
		System.out.println("CheckOut: " + currentTime);
	}
}
