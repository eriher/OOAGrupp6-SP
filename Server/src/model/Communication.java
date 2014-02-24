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
import java.util.LinkedList;
import java.util.Observable;

public class Communication extends Observable implements Runnable {
	private Socket soc;
	private Boolean recieveInited = false;
	private CommRecieve recComm;

	private InetAddress iaddr = null;
	private String message = null;
	private LinkedList linkedMessage = null;
	private FileManagement fileMan;

	// private final int CLIENT_PORT = 4445; //TODO remove after debug

	public Communication(Socket soc) {
		this.soc = soc;
		fileMan = new FileManagement();

	}

	public void run() {

	}

	// TODO remove when working
	/**
	 * Check what type of message has been recieved. For now it only check for login recieved.
	 */
	/*
	 * public void messageRecieved(InetAddress iaddr , String message){ if(iaddr != null && message != null){ //Only works for login atm.
	 * this.iaddr = iaddr; this.message = message; String[] messageType = message.split(" "); //You should now have (login/schedule)
	 * (PersonalNumber) (Password) in messageType
	 * 
	 * if(messageType[0].contains("login")){
	 * 
	 * loginRecieved(iaddr, message); } else if(messageType[0].contains("schedule")){ //TODO Send back schedule }
	 * 
	 * 
	 * iaddr= null; message = null; } }
	 */

	/**
	 * Check what type of message has been recieved. For now it only check for login recieved.
	 */
	public void messageRecieved(InetAddress iaddr, LinkedList<Object> linkedMessage) {
		// TODO fix so you can reieve linkedlist instead of string
		if (iaddr != null && linkedMessage != null) {
			this.iaddr = iaddr;
			this.linkedMessage = linkedMessage;
			
			String whatToDo = (String) linkedMessage.get(0);	//TODO Tidy this up son, its a mess.
			if ( whatToDo.compareToIgnoreCase("login") == 0 ) { // Login has been recieved  previous: linkedMessage.get(0).equals("login")
				loginRecieved(iaddr, linkedMessage);
				
			} else if (whatToDo.compareToIgnoreCase("schedule") == 0) { // A request for a schedule has been recieved previous: linkedMessage.get(0).equals("schedule")
				// TODO Send back schedule
			}

		}
	}

	/**
	 * @param iaddr
	 *            InetAddress
	 * @param message
	 *            The message to be sent
	 * @param comm
	 *            What communication object to use
	 */
	public void loginRecieved(InetAddress iaddr, LinkedList<Object> message) { // A login has been recieved

		if (message.get(0) instanceof String && message.get(1) instanceof String && message.get(2) instanceof String) {

			try {																	// If you send empty password server will crash without catch
				String recievedPassword = (String) message.get(2); 

				String realPassword = fileMan.getPassword((String) message.get(1)); // Check real password from textfile.

				if (recievedPassword.equals(realPassword)) { 						// Checks with the file to see if sent password is correct

					// Now true is to be sent back
					String status = fileMan.getStatus((String) message.get(1));		//Get what status the user has
					LinkedList<String> loginStatus = new LinkedList<String>();		//Create the linkedlist that is to be returned
					loginStatus.add("login");										//Add login as first string in the list so the client knows what it is recieving
					loginStatus.add(status);										//Adding the status as second string in the list so the client know what GUI to open.
					System.out.println("login" + status + ": is sent back due to RIGHT password:" ); 
					
					LinkedList<String> listToSend = new LinkedList<String>();
					listToSend.add("login");
					listToSend.add(status);
					
					
					send(soc, listToSend); // Sends back to port	
				} else {
					System.out.println("False is sent back due to WRONG password");				//TODO fix all send LinkedList<Object //String>
				
					LinkedList<String> listToSend = new LinkedList<String>();
					listToSend.add("login");
					listToSend.add("false");
					
					send(soc, listToSend);

					closeSocket();

				}
			} catch (Exception e) {
				System.out.println("Did not recieve password");

				System.out.println("False is sent back due to INVALID messsage recieved");
				send(soc, "false");

				closeSocket();

			}
		}
	}
	public void send(Socket soc, Object objStringMessage) { // Send message objBoolMessage to socket soc

		try {

			ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
			out.writeObject(objStringMessage);
			out.flush();

		} catch (IOException e) {
			System.out.println("Client not active, did you close clients recieveing part?"); // TODO
			// close
			// connection
			// to
			// client

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

	public InetAddress getInetAddress() {
		return iaddr;
	}

	private void closeSocket() {
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
