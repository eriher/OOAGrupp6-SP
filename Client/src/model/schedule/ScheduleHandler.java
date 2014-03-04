/**
 * Handles methods for modifying and getting schedule objects
 * 
 * @author Simon Planhage
 * @version 2014-03-04
 */

package model.schedule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import model.Communication;
import model.User;

import org.joda.time.DateTime;

import controller.Workflow;


public class ScheduleHandler implements Observer {
	
	//A reference to the current user

	private User currentUser;
	
	//A reference to the schedule object that contains all weeks
	private Schedule userSchedule;
	
	//A reference to the currently active week
	private Week currentWeek;
	
	//The current real week
	private int currentRealWeekNr = (new DateTime()).getWeekOfWeekyear();
	private int currentRealYearNr = (new DateTime()).getYear();
	
	private int currentWeekIndex = currentRealWeekNr - 1;
	private int currentYearIndex = currentRealYearNr - 2014;
	
	
	private Boolean isCheckedIn = false;

	public ScheduleHandler() {
	}
	
	public void setScheduleHandler(User user) {
		this.currentUser = user;
		
		//Checks if the user has a schedule and if he does, loads the schedule.
		//Else it creates a new default schedule for the user.
		userSchedule = currentUser.getUserSchedule();
		if (userSchedule != null) {
			currentWeek = userSchedule.yearList.get(currentYearIndex).weekList.get(currentWeekIndex);
			System.out.println(currentWeek.weekNr);
		} else {
			userSchedule = new Schedule();
			populateYear();
			currentWeek = userSchedule.yearList.get(currentYearIndex).weekList.get(currentWeekIndex);
			System.out.println(currentWeek.weekNr);
		}
		
	}

	/**
	 * Sets the currently active week
	 * @param weekNumber
	 * @return
	 */
	public void setActiveWeek(int yearNumber, int weekNumber) {
		currentWeekIndex = weekNumber - 1;
		currentWeek = userSchedule.yearList.get(yearNumber-currentRealYearNr).weekList.get(currentWeekIndex);
	}
	

	/**
	 * Sets the currently active week to the next week
	 */
	public void getNextWeek() {
		currentWeekIndex++;
		currentWeek = userSchedule.yearList.get(currentYearIndex).weekList.get(currentWeekIndex);
		System.out.println("Currently active week: " + currentWeek.weekNr);
	}


	/**
	 * Sets the currently active week to the previous week
	 */
	public void getPrevWeek() {
		currentWeekIndex--;
		currentWeek = userSchedule.yearList.get(currentYearIndex).weekList.get(currentRealWeekNr);
		System.out.println("Currently active week: " + currentWeek.weekNr);
	}


	/**
	 * 
	 */
	void modifySchedule() {
		
		
		
	}
	
	
	/**
	 * CheckIn on the current day and time
	 * Creates a DateTime object for easy handling of current day and time.
	 * Uses the current day of the week to get the right day from the list of weekdays,
	 * then adds the checkIn time to the list of checkIn times.
	 * 
	 */
	public void checkIn() {
		if (isCheckedIn == false) {
			DateTime currentTime = new DateTime();
			int currentRealDayofWeek = currentTime.getDayOfWeek() - 1;
			Day currentStampInDay = currentWeek.days.get(currentRealDayofWeek);
			currentStampInDay.checkInTime.add(currentTime);
			System.out.println("CheckIns: " + currentWeek.days.get(currentRealDayofWeek).checkInTime);
			System.out.println("Scheduled time, in: " + currentWeek.days.get(currentRealDayofWeek).scheduledInTime
								+ ", out: " + currentWeek.days.get(currentRealDayofWeek).scheduledOutTime);
			isCheckedIn = true;
		} else {
			System.out.println("User is already checked in!");
		}
	}

	
	/**
	 * CheckOut on the current day and time
	 * Creates a DateTime object for easy handling of current day and time.
	 * Uses the current day of the week to get the right day from the list of weekdays,
	 * then adds the checkOut time to the list of checkOut times.
	 */
	public void checkOut() {
		if (isCheckedIn == true) {
			DateTime currentTime = new DateTime();
			int currentRealDayofWeek = currentTime.getDayOfWeek() - 1;
			Day currentStampInDay = currentWeek.days.get(currentRealDayofWeek);
			currentStampInDay.checkOutTime.add(currentTime);
			System.out.println("CheckOuts: " + currentWeek.days.get(currentRealDayofWeek).checkOutTime);
			System.out.println("Scheduled time, in: " + currentWeek.days.get(currentRealDayofWeek).scheduledInTime
					+ ", out: " + currentWeek.days.get(currentRealDayofWeek).scheduledOutTime);
			isCheckedIn = false;
		} else {
			System.out.println("User is already checked out!");
		}
	}
	

	/**
	 * Populates a week with empty days, ranging from 0 to 6
	 * For now all days are scheduled from 8-17
	 * 
	 */
	private void populateWeek(Week week) {
		
		for(int i = 0; i < 7; i++) {
			Day day = new Day();
	//		day.dayNr = i;
	//		day.scheduledInTime = new DateTime(2000,1,1,8,00);
	//		day.scheduledOutTime = new DateTime(2000,1,1,17,00,00);
			week.days.add(day);
		}
	}
	
	/**
	 * Populates the active userSchedule with a years worth of weeks.
	 */
	private void populateYear() {
		
		for(int i = 0; i < 52; i++) {
			Year newYear = new Year();
			userSchedule.yearList.add(newYear);
			Week newWeek = new Week();
			newWeek.weekNr = i;
			populateWeek(newWeek);
			userSchedule.yearList.get(currentYearIndex).weekList.add(newWeek);
		}
	}
	
	/**
	 * Sets the scheduled start and stop time for the day
	 * The times are strings in form "hh:mm"
	 * 
	 * @param week
	 * @param day
	 * @param startString format: "hh:mm"
	 * @param stopString format: "hh:mm"
	 */
	public void setScheduledTime(int year, int week, int day, String startString, String stopString) {
		Day d = getDay(year, week, day);
		
		String[] splitString = startString.split(":");
		int h = Integer.parseInt(splitString[0]);
		int m = Integer.parseInt(splitString[1]);
		
		d.scheduledInTime = new DateTime(2000,1,1,h,m);
		
		splitString = stopString.split(":");
		h = Integer.parseInt(splitString[0]);
		m = Integer.parseInt(splitString[1]);
		
		d.scheduledOutTime = new DateTime(2000,1,1,h,m);		
	}
	
	private Day getDay(int year, int week, int day) {
		return userSchedule.yearList.get(year-currentRealYearNr).weekList.get(week).days.get(day);
	}
	
	
	/**
	 * Translates real time stamps to minutes for easy building of schemastaplarna.
	 * First field: a list of all checkIns of the day
	 * Second field: a list of all checkOuts of the day
	 * Third field: the scheduled in and out times of the day
	 * @param dayofWeek
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> scheduleToDays (int dayofWeek) {
		
		ArrayList<ArrayList<Integer>> totalList = new ArrayList<ArrayList<Integer>>(3);
		ArrayList<Integer> checkInList = new ArrayList<Integer>(currentWeek.days.get(dayofWeek).checkInTime.size());
		ArrayList<Integer> checkOutList = new ArrayList<Integer>(currentWeek.days.get(dayofWeek).checkInTime.size());
		ArrayList<Integer> scheduledTimeList = new ArrayList<Integer>(2);
		
		for (DateTime stamp : currentWeek.days.get(dayofWeek).checkInTime) 
			checkInList.add(stamp.getMinuteOfDay());
		
		for (DateTime stamp : currentWeek.days.get(dayofWeek).checkOutTime) 
			checkOutList.add(stamp.getMinuteOfDay());
		
		scheduledTimeList.add(currentWeek.days.get(dayofWeek).scheduledInTime.getMinuteOfDay());
		scheduledTimeList.add(currentWeek.days.get(dayofWeek).scheduledOutTime.getMinuteOfDay());
		
		totalList.add(checkInList);
		totalList.add(checkOutList);
		totalList.add(scheduledTimeList);
		System.out.println(totalList);
		return totalList;
	}
	
	public Schedule getSchedule() {
		return userSchedule;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Communication) {
			@SuppressWarnings("unchecked")
			LinkedList<Object> argsList = (LinkedList<Object>) arg;
			if(argsList.get(0).equals("GetUser") || argsList.get(0).equals("CheckIn") 
					|| argsList.get(0).equals("CheckOut"))
				setScheduleHandler((User)argsList.get(1));
			else if(argsList.get(0).equals("login") && argsList.get(1).equals("Employee"))
				setScheduleHandler((User)argsList.get(2));
		}
	}


	public void addObserver(Observable o) {
		o.addObserver(this);
	}
	
}
