/**
 * This is the overall controller of the Server
 * 
 * @author  Henrik Johansson
 * @version 2013-02-12
 */

package controller;
 
import model.ClientNode;
import model.FileManagement;
import view.Window;

public class Workflow {
	private final int SERVER_PORT = 4444;
	//private FileManagement fileMan;

	public Workflow() {
		initWindowThread(SERVER_PORT);
		//initModelThread();
	}

	private void initWindowThread(int SERVER_PORT) {	//Initializes the Window	//TODO fix threads 
		Window window = new Window(SERVER_PORT);
	}

	private void initModelThread() {		//This will initialize when click on startmodel
		//ClientNode clientNode = ClientNode.getInstance(SERVER_PORT);


	}



}
