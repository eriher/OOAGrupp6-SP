
/**
 * Controls all ActionEvents (All 2 of them)
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
	 * @return instance of ActionHandler
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
