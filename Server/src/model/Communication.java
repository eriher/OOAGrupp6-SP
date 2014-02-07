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
	private ServerSocket server;
	private Socket soc;

	public Communication(ServerSocket server) { // Should be made observer of
												// CommRecieve
		this.server = server;

		Socket soc;
		try {
			soc = server.accept();
			Thread recCom = new Thread(new CommRecieve(soc)); // Creates the
																// recieve and
																// send threads

			
			
			
		//recCom.start(); // Starts the recieving and send threads

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void send() {

	}

	public void recieve() {

	}

}
