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



}
