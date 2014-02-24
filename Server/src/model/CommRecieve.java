/**
 * Creates receiving part of Communication class, when starting the thread it will check for incomming messages.
 * 
 * @author Henrik Johansson
 * @version 2013-02-07
 * 
 * @param port	port number for Serversocket.
 */

package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observable;

public class CommRecieve extends Observable implements Runnable {		
	private Socket soc;
	private ServerSocket server;
	private String message = null;
	private ObjectInputStream objInputStream;
	private InetAddress iaddr;
	private Boolean again;
	
	public CommRecieve(ServerSocket server) {
		this.server = server;
		
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
	
		try {
			
			again = true;		
			while (again) {
				
				soc = server.accept();						
				objInputStream = new ObjectInputStream((soc.getInputStream()));

				iaddr = soc.getInetAddress();
				setChanged(); 								//Update Observers of recieved message from iaddr ip
				notifyObservers(iaddr);
				
				
				LinkedList<Object> listMessage = (LinkedList<Object>) objInputStream.readObject();
				
				message =   (String)  listMessage.get(0) ;		//TODO doesent work muppasdasjsajasjdjsdajdjas

				System.out.println("This came in: " + message);				// TODO remove after debug	
				setChanged(); 								//Update Observers
				notifyObservers(listMessage );
				
				
				//soc.close();
			
			}
			server.close();

		} catch (IOException e) {
			System.out.println("If this was recieved during Stop from GUI, all should be fine.");
			//e.printStackTrace();
			
		}catch(ClassNotFoundException e){
			
			System.out.println("message = (String) buffIn.readObject().toString(); ");
			e.printStackTrace();
		}
		
	}
	
	public String getMessage(){
		return message;
	}
	
	public Socket getSocket(){
		return soc;
	}
	
	public InetAddress getInetAddress(){
		return iaddr;
	}
	
	public void kill(){							// TODO untested if correct usage
		try {
			server.close();
		} catch (IOException e) {
			System.out.println("Closed serversocket");
			//e.printStackTrace();
		}
		again=false;
	}
}
