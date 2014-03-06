/**
 * Handles methods for modifying and getting schedule objects
 * 
 * @author Simon Planhage
 * @version 2014-03-04
 */

package model.schedule;

import model.User;

import org.joda.time.DateTime;

public class ScheduleHandler {

	// A reference to the current user
	@SuppressWarnings("unused")
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



	/**
	 * Sets the current active userSchedule to the schedule contained in the
	 * user object.
	 * 
	 * @param currentUser
	 *            the currently logged in user
	 */
	public ScheduleHandler(User currentUser) {
		this.currentUser = currentUser;

		// Checks if the user has a schedule and if he does, loads the schedule.
		// Else it creates a new default schedule for the user.
		userSchedule = currentUser.getUserSchedule();
		if (userSchedule != null) {
			currentWeek = userSchedule.yearList.get(currentYearIndex).weekList
					.get(currentWeekIndex);
			// System.out.println(currentWeek.weekNr);
		} else {
			userSchedule = new Schedule();
			populateYear();
			currentWeek = userSchedule.yearList.get(currentYearIndex).weekList
					.get(currentWeekIndex);
			// System.out.println(currentWeek.weekNr);
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
				.get(currentRealWeekNr);
		System.out.println("Currently active week: " + currentWeek.weekNr);
	}

	/**
	 * 
	 */
	void modifySchedule() {

	}

	/**
	 * CheckIn on the current day and time Creates a DateTime object for easy
	 * handling of current day and time. Uses the current day of the week to get
	 * the right day from the list of weekdays, then adds the checkIn time to
	 * the list of checkIn times.
	 * 
	 */
	public void checkIn() {

			DateTime currentTime = new DateTime();
			int currentRealDayofWeek = currentTime.getDayOfWeek() - 1;
			Day currentStampInDay = currentWeek.days.get(currentRealDayofWeek);
			currentStampInDay.checkInTime.add(currentTime);
			System.out.println("CheckIns: "
					+ currentWeek.days.get(currentRealDayofWeek).checkInTime);
			System.out
					.println("Scheduled time, in: "
							+ currentWeek.days.get(currentRealDayofWeek).scheduledInTime
							+ ", out: "
							+ currentWeek.days.get(currentRealDayofWeek).scheduledOutTime);


	}

	/**
	 * CheckOut on the current day and time Creates a DateTime object for easy
	 * handling of current day and time. Uses the current day of the week to get
	 * the right day from the list of weekdays, then adds the checkOut time to
	 * the list of checkOut times.
	 */
	public void checkOut() {

			DateTime currentTime = new DateTime();
			int currentRealDayofWeek = currentTime.getDayOfWeek() - 1;
			Day currentStampInDay = currentWeek.days.get(currentRealDayofWeek);
			currentStampInDay.checkOutTime.add(currentTime);
			System.out.println("CheckOuts: "
					+ currentWeek.days.get(currentRealDayofWeek).checkOutTime);
			System.out
					.println("Scheduled time, in: "
							+ currentWeek.days.get(currentRealDayofWeek).scheduledInTime
							+ ", out: "
							+ currentWeek.days.get(currentRealDayofWeek).scheduledOutTime);


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
			// day.scheduledInTime = new DateTime(2000,1,1,8,00);
			// day.scheduledOutTime = new DateTime(2000,1,1,17,00,00);
			week.days.add(day);
		}
	}

	/**
	 * Populates the active userSchedule with a years worth of weeks.
	 */
	private void populateYear() {
		for(int x = 0; x < 5; x++){
		userSchedule.yearList.add(new Year(currentRealYearNr+x));
		
		for (int i = 0; i < 52; i++) {
			Week newWeek = new Week();
			newWeek.weekNr = i;
			populateWeek(newWeek);
			userSchedule.yearList.get(currentYearIndex+x).weekList.add(newWeek);
		}
		}
	}

	public void populateDefaultSchedule() {
		for (Year y : userSchedule.yearList) {
			for (Week w : y.weekList) {
				populateDefaultWeek(w);
			}
		}
	}

	private void populateDefaultWeek(Week week) {
		for (Day d : week.days) {
			d.scheduledInTime = new DateTime(2000, 1, 1, 8, 00);
			d.scheduledOutTime = new DateTime(2000, 1, 1, 17, 00, 00);
		}
	}

	/**
	 * Sets the scheduled start and stop time for the day The times are strings
	 * in form "hh:mm"
	 * 
	 * @param week
	 * @param day
	 * @param startString
	 *            format: "hh:mm"
	 * @param stopString
	 *            format: "hh:mm"
	 */
	public void setScheduledTime(int year, int week, int day,
			String startString, String stopString) {
		Day d = getDay(year, week, day);

		String[] splitString = startString.split(":");
		int h = Integer.parseInt(splitString[0]);
		int m = Integer.parseInt(splitString[1]);

		d.scheduledInTime = new DateTime(2000, 1, 1, h, m);

		splitString = stopString.split(":");
		h = Integer.parseInt(splitString[0]);
		m = Integer.parseInt(splitString[1]);

		d.scheduledOutTime = new DateTime(2000, 1, 1, h, m);
	}

	private Day getDay(int year, int week, int day) {
		return userSchedule.yearList.get(year - currentRealYearNr).weekList
				.get(week).days.get(day);
	}


	public Schedule getSchedule() {
		return userSchedule;
	}

}
