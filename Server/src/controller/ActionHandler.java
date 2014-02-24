
/**
 * Controls all ActionEvents (All 2 of them)
 * 
 * @author Henrik Johansson
 * @version 2013-02-12
 */


package controller;

import javax.swing.JOptionPane;

import model.ClientNode;

/**
 * @author Henrik
 * @version 2014-02-21
 */
public class ActionHandler { //Singleton
	//private final int SERVER_PORT = 4444;
	private static ActionHandler instance = null;
	
	Boolean logicThreadEnd;
	Boolean haltFlag;
	
	private ActionHandler(){
		
	}
	
	/**
	 * @return instance of ActionHandler
	 */
	public static ActionHandler getInstance(){ //Singleton method
		if(instance == null){
			instance = new ActionHandler();
		}
		return instance;
	}
	
	public void startButton(final int SERVER_PORT){	//Executes when click on start button
		/*clientNode = ClientNode.getInstance(SERVER_PORT);
		System.out.println("Server start");*/
		
		logicThreadEnd = false;
		
		new Thread(){
			public void run(){
				ClientNode clientNode = ClientNode.getInstance(SERVER_PORT);

				haltFlag = false;
				while(!haltFlag){											//TODO Dont know if all (=null) are neccesary, might only need kill from CommRecieve
				if(logicThreadEnd){
					interrupt(); 
					clientNode.killClientNode();
					clientNode = null;
					haltFlag = true;
					
				}
				}
			}
		}.start();
		
		System.out.println("Server start");
	}
	
	public void stopButton(){	//Executes when click on stop button
		logicThreadEnd = true;
		System.out.println("Server stop");
	}
	
	public void about(){		//Executes when click on menu item about
		String aboutText = "MarximumWorker 9001 (Server)"
				+ "\n\n"
				+ "Version 1.0\n"
				+ "Source: https://github.com/Stromner/OOAGrupp6-SP\n";
		
		JOptionPane.showMessageDialog(null, aboutText, "about", JOptionPane.INFORMATION_MESSAGE);
	}

}
