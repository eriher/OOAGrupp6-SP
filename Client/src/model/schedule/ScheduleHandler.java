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

public class ScheduleHandler extends Observable implements Observer {

	// A reference to the current user

	private User currentUser;

	// A reference to the schedule object that contains all weeks
	private Schedule userSchedule;

	// A reference to the currently active week
	private Week currentWeek;

	// The current real week
	private int currentRealWeekNr = (new DateTime()).getWeekOfWeekyear();
	private int currentRealYearNr = (new DateTime()).getYear();

	private int currentWeekIndex = currentRealWeekNr - 1;
	private int currentYearIndex = currentRealYearNr - 2014;

	public ScheduleHandler() {
	}

	public void setScheduleHandler(User user) {
		this.currentUser = user;

		// Checks if the user has a schedule and if he does, loads the schedule.
		// Else it creates a new default schedule for the user.
		userSchedule = currentUser.getUserSchedule();
		if (userSchedule != null) {
			currentWeek = userSchedule.yearList.get(currentYearIndex).weekList
					.get(currentWeekIndex);
		} else {
			userSchedule = new Schedule();
			populateYear();
			currentWeek = userSchedule.yearList.get(currentYearIndex).weekList
					.get(currentWeekIndex);
			// populateDefaultSchedule();
			currentUser.setSchedule(userSchedule);
		}

	}

	/**
	 * Sets the currently active week
	 * 
	 * @param weekNumber
	 * @return
	 */
	public void setActiveWeek(int yearNumber, int weekNumber) {
		currentWeekIndex = weekNumber - 1;
		currentWeek = userSchedule.yearList.get(yearNumber - currentRealYearNr).weekList
				.get(currentWeekIndex);
	}

	/**
	 * Sets the currently active week to the next week
	 */
	public void getNextWeek() {
		currentWeekIndex++;
		currentWeek = userSchedule.yearList.get(currentYearIndex).weekList
				.get(currentWeekIndex);
		System.out.println("Currently active week: " + currentWeek.weekNr);
	}

	/**
	 * Sets the currently active week to the previous week
	 */
	public void getPrevWeek() {
		currentWeekIndex--;
		currentWeek = userSchedule.yearList.get(currentYearIndex).weekList
				.get(currentWeekIndex);
		System.out.println("Currently active week: " + currentWeek.weekNr);
	}

	/**
	 * 
	 */
	void modifySchedule() {

	}

	/**
	 * Populates a week with empty days, ranging from 0 to 6 For now all days
	 * are scheduled from 8-17
	 * 
	 */
	private void populateWeek(Week week) {

		for (int i = 0; i < 7; i++) {
			Day day = new Day();
			// day.dayNr = i;
			// day.scheduledInTime = new
			// DateTime(2000,1,1,8,00).getMinuteOfDay();
			// day.scheduledOutTime = new
			// DateTime(2000,1,1,17,00,00).getMinuteOfDay();
			// day.checkInTime.add(new
			// DateTime(2000,1,1,17,00,00).getMinuteOfDay());
			// day.checkOutTime.add(new
			// DateTime(2000,1,1,19,00,00).getMinuteOfDay());
			week.days.add(day);
		}
	}

	/**
	 * Populates the active userSchedule with a years worth of weeks.
	 */
	private void populateYear() {
		for (int x = 0; x < 5; x++) {
			userSchedule.yearList.add(new Year(currentRealYearNr + x));

			for (int i = 0; i < 52; i++) {
				Week newWeek = new Week();
				newWeek.weekNr = i;
				populateWeek(newWeek);
				userSchedule.yearList.get(currentYearIndex + x).weekList
						.add(newWeek);
			}
		}
	}

	/**
	 * Translates real time stamps to minutes for easy building of
	 * schemastaplarna. First field: a list of all checkIns of the day Second
	 * field: a list of all checkOuts of the day Third field: the scheduled in
	 * and out times of the day
	 * 
	 * @param dayofWeek
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> scheduleToDays(int dayofWeek) {

		ArrayList<ArrayList<Integer>> totalList = new ArrayList<ArrayList<Integer>>(
				3);
		ArrayList<Integer> checkInList = new ArrayList<Integer>(
				currentWeek.days.get(dayofWeek).checkInTime.size());
		ArrayList<Integer> checkOutList = new ArrayList<Integer>(
				currentWeek.days.get(dayofWeek).checkInTime.size());
		ArrayList<Integer> scheduledTimeList = new ArrayList<Integer>(2);

		for (DateTime stamp : currentWeek.days.get(dayofWeek).checkInTime)
			checkInList.add(stamp.getMinuteOfDay());

		for (DateTime stamp : currentWeek.days.get(dayofWeek).checkOutTime)
			checkOutList.add(stamp.getMinuteOfDay());

		try {
			scheduledTimeList.add(currentWeek.days.get(dayofWeek).scheduledInTime.getMinuteOfDay());
		} catch (Exception e) {
			//scheduledTimeList.add(null);
		}
		try {
			scheduledTimeList.add(currentWeek.days.get(dayofWeek).scheduledOutTime.getMinuteOfDay());
		} catch (Exception e) {
			//scheduledTimeList.add(null);
		}

		totalList.add(checkInList);
		totalList.add(checkOutList);
		totalList.add(scheduledTimeList);
		System.out.println(currentWeekIndex);
		return totalList;
	}

	public Schedule getSchedule() {
		return userSchedule;
	}

	public User getUser() {
		return currentUser;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Communication) {
			@SuppressWarnings("unchecked")
			LinkedList<Object> argsList = (LinkedList<Object>) arg;
			if (argsList.get(0).equals("GetUser")
					|| argsList.get(0).equals("CheckIn")
					|| argsList.get(0).equals("CheckOut")) {
				setScheduleHandler((User) argsList.get(1));
				setChanged();
				notifyObservers(this);
			} else if (argsList.get(0).equals("login")
					&& argsList.get(1).equals("Employee")) {
				setScheduleHandler((User) argsList.get(2));
				setChanged();
				notifyObservers(this);
			}
		}

	}

}
