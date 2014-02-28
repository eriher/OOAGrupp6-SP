/**
 * Visual representation of the data that makes up the Schedule.
 * 
 * @author David Stromner
 * @version 2013-02-07
 */

package view;

import java.util.Observable;
import java.util.Observer;

import model.schedule;

public class JSchedule implements Observer {
	Schedule schedule;	
	
	public JSchedule() {
		initPanels();
	}
	/**
	 * @param o
	 * @param arg
	 */
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof Schedule)
			schedule = (Schedule)arg;
		
	}
	public void initPanels(){
		
		
	}
}