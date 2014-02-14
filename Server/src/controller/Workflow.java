/**
 * This is the overall controller of the Server
 * 
 * @author  Henrik Johansson
 * @version 2013-02-12
 */

package controller;

import java.net.InetAddress;

import model.ClientNode;
import model.Communication;
import model.FileManagement;

public class Workflow {
	private FileManagement fileMan;

	public Workflow() {
		initModelThread();
	}

	private void initWindowThread() {	//Initializes the Window

	}

	private void initModelThread() {
		ClientNode clientNode = ClientNode.getInstance(4444, this);
		fileMan = new FileManagement();

	}

	/**
	 * @param iaddr		InetAddress
	 * @param message	The message to be sent
	 * @param comm		What communication object oto use
	 */
	public void loginRecieved(InetAddress iaddr, String message, Communication comm) { //A login has been recieved
		String[] persNrPass = message.split(" ");

		if (persNrPass[1].equals(fileMan.getPassword("inlogg.txt",
				persNrPass[0]))) { // Checks with the inlogg file to see if sent
									// password is correct
			
			// Now true is to be sent back
			System.out.println("True skickas tillbaka");
			comm.send(iaddr, 4445, true);					//Sends back to socket 6666
			
		}
		else{
			comm.send(iaddr, 4445, false);
			System.out.println("False skickas tillbaka");
		}

	}

}
