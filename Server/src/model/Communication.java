/**
 * Each connecton to the server should initiate a new Communication object.
 *
 * @author Henrik Johansson
 * @version 2013-02-25
 * 
 * @param port	port number for Serversocket.
 */

package model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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

	private Users users;
	private Boolean normalLogin = false;
	private Boolean adminLogin = false;

	public Communication(Socket soc) {
		this.soc = soc;
		fileMan = new FileManagement();

		

	}

	public void run() {
		recieve();
	}

	/**
	 * Check what type of message has been recieved.
	 */
	public void messageRecieved(InetAddress iaddr, LinkedList<Object> linkedMessage) {
		if (iaddr != null && linkedMessage != null) {
			this.iaddr = iaddr;
			this.linkedMessage = linkedMessage;
			System.out.println("Message recieved in communication Server" + linkedMessage.toString());
			String whatToDo = (String) linkedMessage.get(0);
			if (whatToDo.compareToIgnoreCase("Login") == 0) { // Login has been recieved  previous: linkedMessage.get(0).equals("login")
				loginRecieved(iaddr, linkedMessage);
			} else if (whatToDo.compareToIgnoreCase("Logout") == 0) {
				adminLogin = false;
				normalLogin = false;
			} else if (whatToDo.compareToIgnoreCase("GetAllUsers") == 0) {
				Users users = fileMan.getUsersList();

				LinkedList<String> linkedMessageReturn = new LinkedList<String>(); //Formats the linkedList that is to be returned to:
				linkedMessageReturn.add("GetAllUsers"); //LinkedList( "GetAllUsers", "PersNr 1", "PersNr 2", "PersNr 3", ..., "PersNr n" )
				String[] usersListStringArray = users.getAllPerNr().toString().split(", ");

				for (int i = 1; i < usersListStringArray.length; i++) {
					linkedMessageReturn.add(usersListStringArray[i]);
				}

				send(soc, linkedMessageReturn); // Client will recieve a linkedList with strings and the first is "GetAllUsers" followed by the personal numbers

			} else if (whatToDo.compareToIgnoreCase("GetUser") == 0) { //Sends LinkedList<(String)"GetUser", (User)user> back to client
				String persNr = (String) linkedMessage.get(1);
				User user = fileMan.getUsersList().getUser(persNr);

				LinkedList<Object> linkedMessageReturn = new LinkedList<Object>();
				linkedMessageReturn.add("GetUser");
				linkedMessageReturn.add(user);
				send(soc, linkedMessageReturn);

			} else if (whatToDo.compareToIgnoreCase("RemoveUser") == 0 || whatToDo.compareToIgnoreCase("DeleteUser") == 0) {
				String persNr = (String) linkedMessage.get(1);
				Users users = fileMan.getUsersList();
				users.remove(persNr);
				fileMan.writeUsersFile(users);

			} else if (whatToDo.compareToIgnoreCase("NewUser") == 0) { //Add new user 
				Users users = fileMan.getUsersList();
				users.add((String) linkedMessage.get(1), (String) linkedMessage.get(2), (String) linkedMessage.get(3));
				fileMan.writeUsersFile(users);

			} else if (whatToDo.compareToIgnoreCase("EditUser") == 0) { // 

				Users users = fileMan.getUsersList();
				User user = users.getUser((String) linkedMessage.get(1));
				user.setPassword((String) linkedMessage.get(2));
				user.setStatus((String) linkedMessage.get(3));

				fileMan.writeUsersFile(users);
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

			try { // If you send empty password server will crash without catch
				String recievedPassword = (String) message.get(2);

				User user = fileMan.getUsersList().getUser((String) message.get(1)); //Get all info from the user.

				if (recievedPassword.equals(user.getPassword())) { // Checks with the file to see if sent password is correct

					// Now true is to be sent back
					String status = user.getStatus(); //Get what status the user has
					LinkedList<String> loginStatus = new LinkedList<String>(); //Create the linkedlist that is to be returned
					loginStatus.add("login"); //Add login as first string in the list so the client knows what it is recieving
					loginStatus.add(status); //Adding the status as second string in the list so the client know what GUI to open.
					System.out.println("login" + status + ": is sent back due to RIGHT password:");

					LinkedList<String> listToSend = new LinkedList<String>();
					listToSend.add("login");
					listToSend.add(status);

					send(soc, listToSend); // Sends back to port	
					if (status.compareToIgnoreCase("Admin") == 0) { //TODO 
						adminLogin = true;
						normalLogin = false;
					} else {
						normalLogin = true;
						adminLogin = false;
					}

				} else {
					System.out.println("False is sent back due to WRONG password"); //TODO fix all send LinkedList<Object //String>

					LinkedList<String> listToSend = new LinkedList<String>();
					listToSend.add("login");
					listToSend.add("false");

					send(soc, listToSend);

					closeSocket();

				}
			} catch (Exception e) {
				System.out.println("Did not recieve password");

				LinkedList<String> listToSend = new LinkedList<String>();
				listToSend.add("login");
				listToSend.add("false");

				send(soc, listToSend);

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
			System.out.println("Client not active, did you close clients recieveing part?"); // TODO close connection to client.

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

	public void recieve() { 
		while (normalLogin || adminLogin) {
			try {

				BufferedInputStream checkIn = new BufferedInputStream(soc.getInputStream());
				while (checkIn.available() != 0) {
					ObjectInputStream in = new ObjectInputStream(checkIn);

					LinkedList<Object> linkedIn = (LinkedList<Object>) in.readObject();

					messageRecieved(soc.getInetAddress(), linkedIn);
				}
			} catch (IOException e) {
				normalLogin = false;
				adminLogin = false;
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}

}
