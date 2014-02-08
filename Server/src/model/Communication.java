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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Communication {
	private ServerSocket server;
	private Socket soc;
	private Boolean recieveInited = false;
	private CommRecieve recComm ;

	public Communication(ServerSocket server) { // Should be made observer of CommRecieve
		this.server = server;

		recieveInit();
		System.out.println("Under recieve");
		

	}

	public void send() {

	}
	
	public String recieveMessage(){
		return recComm.getMessage();
	}

	public void recieveInit() {
		if(!recieveInited){
			recieveInited = true;
			CommRecieve recComm = new CommRecieve(server);
			Thread recieve = new Thread( recComm );
			recieve.start();
		}
		
		
	}

}
