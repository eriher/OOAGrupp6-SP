
/**
 * Each connecton to the server should initiate a new Communication object.
 *
 * @author Henrik Johansson
 * @version 2013-02-12
 * 
 * @param port	port number for Serversocket.
 */

package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication  implements Runnable {
	private Socket soc;
	private Boolean recieveInited = false;
	private CommRecieve recComm ;
	
	private InetAddress iaddr = null;
	private String message = null;
	private FileManagement fileMan;
	
	private final int CLIENT_PORT = 4445;

	public Communication(Socket soc) { 
		this.soc = soc;
		fileMan = new FileManagement();

	}
	
	public void run(){
		
	}
	
	/**
	 * Check what type of message has been recieved. For now it only check for login recieved.
	 */
	public void messageRecieved(InetAddress iaddr , String message){
		if(iaddr != null && message != null){			//Fungerar endast f�r login nu
			this.iaddr = iaddr;
			this.message = message;
			
			loginRecieved(iaddr, message);
			
			iaddr= null;
			message = null;
		}
	}
	
	/**
	 * @param iaddr		InetAddress
	 * @param message	The message to be sent
	 * @param comm		What communication object to use
	 */
	public void loginRecieved(InetAddress iaddr, String message) { //A login has been recieved
		String[] persNrPass = message.split(" ");
		try{
			String recievedPassword= persNrPass[1];				//If you send empty password server will crash without Catch
			
			String realPassword = fileMan.getPassword("inlogg.txt", persNrPass[0]);
			
			if ( recievedPassword.equals(realPassword) ) { // Checks with the inlogg file to see if sent
										// password is correct
				
				// Now true is to be sent back
				System.out.println("True is sent back due to RIGHT password");
				send(soc, true);					//Sends back to port
			}
			else{
				System.out.println("False is sent back due to WRONG password");
				send(soc, false);
			}
		}catch(Exception e){
			System.out.println("Did not recieve password");

			System.out.println("False is sent back due to INVALID messsage recieved");
			send(soc, false);
		}
		
		

	}
	
	public void send(Socket soc, Object objBoolMessage){	//Send message objBoolMessage to socket soc
		
		try {
			
			ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream() );
			out.writeObject(objBoolMessage);
			out.flush();
			
			
		} catch (IOException e) {
			System.out.println("Client not active, did you close clients recieveing part?");	
			e.printStackTrace();
		}		
		
	

	}
	
	
	



}
