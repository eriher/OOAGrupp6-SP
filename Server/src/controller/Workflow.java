/**
 * This is the overall controller of the Server
 * 
 * @author  Henrik Johansson
 * @version 2013-02-12
 */

package controller;

import view.Window;

public class Workflow {
	private final int SERVER_PORT = 4444;

	public Workflow() {
		initWindowThread(SERVER_PORT);
	
	}

	private void initWindowThread(int SERVER_PORT) { //Initializes the Window it was supposed to be a initModelThread as well but it is instead located in actionhandler for consistancy
		Window window = new Window(SERVER_PORT);
	}

}
