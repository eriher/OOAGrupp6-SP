/**
 * Handles methods for modifying and getting schedule objects
 * 
 * @author Simon Planhage
 * @version 2014-02-20
 */


package model;

import java.util.ArrayList;


public class ScheduleHandler {
	
	//A list that stores all the schedules for the currently logged in user
	static ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
	
	//A reference to the currently active schedule
	private Schedule currentSchedule;
	
	//Instance variable for (maybe slightly) more effective nextWeek and previousWeek methods.
	private int currentIndex = 0;
	
	//Don't know if this is needed yet
	public int personNummer;
	
	
	/**
	 * Constructor who loads an existing schedule file. Some test code remains
	 * @param personNummer The personnummer of the currently logged in user
	 */
	public ScheduleHandler(int personNummer) {
		this.personNummer = personNummer;
	//	Schedule newTest = new Schedule(1);
	//	scheduleList.add(newTest);
	//	saveSchedules(0);
		FileManagement.loadSchedules(personNummer);
		Schedule test = getWeek(1);
		Schedule test2 = new Schedule(2);
		scheduleList.add(test2);
		System.out.println(test.week);
		System.out.println(getNextWeek().week);
	}

	
	/**
	 * Returns the schedule for the requested week
	 * @param weekNumber
	 * @return
	 */
	Schedule getWeek(int weekNumber) {
		
		for(Schedule schedule : scheduleList) {
			
			if (schedule.week != weekNumber) {
				currentIndex++;
			} else {
					currentSchedule = schedule;
					return currentSchedule;
				}
				
			}
		System.out.println("Requested week doesn't exist.");
		return null;
	}

	
	Schedule getNextWeek() {
		currentIndex++;
		Schedule currentSchedule = scheduleList.get(currentIndex);
		return currentSchedule;
	}

	Schedule getPrevWeek() {
		currentIndex--;
		currentSchedule = scheduleList.get(currentIndex);
		return currentSchedule;
	}

	public void checkIn(int personNummer) {
		
		currentSchedule.checkIn();
		
	}

	public void checkout() {
		
		currentSchedule.checkOut();
		
	}

	void modifySchedule() {
		
	}
}
