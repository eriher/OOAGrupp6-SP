/**
 * Write a description of class ActionHandler here.
 * 
 * @author David Stromner
 * @version 2013-02-06
 */

package controller;

public class ActionHandler {
	private static ActionHandler actionHandler = null;
	
	private ActionHandler(){
		
	}
	
	
	/**
	 * 
	 */
	public void logIn(){
		
	}
	
	/**
	 * @return The instance of the object
	 */
	public static ActionHandler getInstance(){
		if(actionHandler == null){
			actionHandler = new ActionHandler();
		}
		
		return actionHandler;
	}
}
