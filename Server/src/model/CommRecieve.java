/**
 * Creates receiving part of Communication class, when starting the thread it will check for incomming messages.
 * 
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

public class CommRecieve implements Runnable {		//Should be made Observable and when a scanin.hasNextline comes in it should update Communication
	
	private Socket soc;
	private ServerSocket server;
	private String message = null;
	public CommRecieve(ServerSocket server) {
		this.server = server;
		
	}

	public void run() {
	
		try {
			
			Boolean again = true;
			
			while (again) {
				
				soc = server.accept();						//Why if I move this outside loop it will only catch first message?
				
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(soc.getInputStream()));

				message = buffReader.readLine();

				System.out.println(message);				//Uppdate Observers

				
			}
			soc.close();
			server.close();

		} catch (IOException e) {

		}
		
	}
	
	public String getMessage(){
		return message;
	}
}
