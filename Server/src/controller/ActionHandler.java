
/**
 * Controls all ActionEvents
 * 
 * @author Henrik Johansson
 * @version 2013-02-12
 */


package controller;

/**
 * @author Henrik
 *
 */
public class ActionHandler { //Singleton
	private static ActionHandler instance = null;
	
	
	private ActionHandler(){
		
	}
	
	/**
	 * @return
	 */
	public ActionHandler getInstance(){ //Singleton method
		if(instance == null){
			instance = new ActionHandler();
		}
		return instance;
	}
	
	public void startButton(){	//Executes when click on start button
		
	}
	
	public void stopButton(){	//Executes when click on stop button
		
	}
	

}
