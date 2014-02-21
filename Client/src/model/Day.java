/**
 * Write a description of class Day here.
 * 
 * @author David Stromner
 * @version 2013-02-06
 */

/*
 Schema uppbyggnad

 Object för varje dag, håller reda på nödvändig information.

 Object lagras i nångon form av lista eller tree set.

 */


package model;


import java.io.Serializable;

import org.joda.time.*;

public class Day implements Serializable {
	
	private static final long serialVersionUID = -7092684252961129250L;

	//Dagens nummer i veckan, börjar på 0
	private int dayNr;
	
	public DateTime checkInTime;
	public DateTime checkOutTime;
	
	public DateTime scheduledInTime;
	public DateTime scheduledOutTime;

	public Day() {

	}
	
	public int getDayNr() {
		return dayNr;
	}

	public void setDayNr(int dayNr) {
		this.dayNr = dayNr;
	}
	

}
