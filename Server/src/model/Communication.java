
/**
 * Each connecton to the server should initiate a new Communication object.
 *
 * @author Henrik Johansson
 * @version 2013-02-19
 * 
 * @param port	port number for Serversocket.
 */

package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;

public class Communication extends Observable implements Runnable {
	private Socket soc;
	private Boolean recieveInited = false;
	private CommRecieve recComm ;
	
	private InetAddress iaddr = null;
	private String message = null;
	private FileManagement fileMan;
	
	//private final int CLIENT_PORT = 4445; 		//TODO remove after debug

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
		if(iaddr != null && message != null){			//Only works for login atm.
			this.iaddr = iaddr;
			this.message = message;
			String[] messageType = message.split(" ");
			//You should now have (login/schedule) (PersonalNumber) (Password) in messageType
			
			if(messageType[0].contains("login")){			 

				loginRecieved(iaddr, message);
			}
			else if(messageType[0].contains("schedule")){
				//TODO Send back schedule
			}
			
			
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
			String recievedPassword= persNrPass[2];				//If you send empty password server will crash without Catch
			
			String realPassword = fileMan.getPassword(persNrPass[1]);
			
			if ( recievedPassword.equals(realPassword) ) { // Checks with the inlogg file to see if sent
										// password is correct
				
				// Now true is to be sent back
				String status = fileMan.getStatus(persNrPass[1]);
				System.out.println("True is sent back due to RIGHT password, Client is:" + status); //TODO fix so you send what typ client is instead of always employeee
				send(soc,  status);					//Sends back to port
			}
			else{
				System.out.println("False is sent back due to WRONG password");
				send(soc, "false");
				
				closeSocket();
				
			}
		}catch(Exception e){
			System.out.println("Did not recieve password");

			System.out.println("False is sent back due to INVALID messsage recieved");
			send(soc, "false");
			
			closeSocket();
			
			}
		
	}
	
	public void send(Socket soc, Object objStringMessage){	//Send message objBoolMessage to socket soc
		
		try {
			
			ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream() );
			out.writeObject(objStringMessage);
			out.flush();
			
			
		} catch (IOException e) {
			System.out.println("Client not active, did you close clients recieveing part?");	//TODO close connection to client
			
			try {
				soc.close();
			} catch (IOException e1) {
				System.out.println("Could not close socket");
				e1.printStackTrace();
			}
			setChanged();
			notifyObservers();
			
			e.printStackTrace();
		}		
		
	

	}
	
	public InetAddress getInetAddress(){
		return iaddr;
	}
	
	private void closeSocket(){
		try {
			soc.close();
		} catch (IOException e1) {
			System.out.println("Could not close socket");
			e1.printStackTrace();
		}
		setChanged();
		notifyObservers();
	}
	
	
	



}
