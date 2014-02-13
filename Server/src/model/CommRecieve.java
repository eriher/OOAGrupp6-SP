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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class CommRecieve extends Observable implements Runnable {		
	private Socket soc;
	private ServerSocket server;
	private String message = null;
	public CommRecieve(ServerSocket server) {
		this.server = server;
		
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
	
		try {
			
			Boolean again = true;		//TODO remove after debug
			
			while (again) {
				
				soc = server.accept();						
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(soc.getInputStream()));

				InetAddress iaddr = soc.getInetAddress();
				setChanged(); 								//Update Observers of recieved message from iaddr ip
				notifyObservers(iaddr);
				
				message = buffReader.readLine();

				System.out.println(message);				// TODO Debug	
				setChanged(); 								//Update Observers
				notifyObservers(message);
				
				
				
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
