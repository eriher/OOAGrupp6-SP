/**
 * Write a description of class Communication here.
 * 
 * @author David Stromner
 * @author Henrik Johansson
 * @version 2013-02-07
 * 
 * @param port	port number for Serversocket.
 */

package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Communication {
	public Communication(int port) {
		Thread recCom = new Thread(new CommRecieve(port) ); //Creates the recieve and send threads
		
		
		recCom.start();										//Starts the recieving and send threads

	}

	public void send() {

	}

	public void recieve() {

	}

}
