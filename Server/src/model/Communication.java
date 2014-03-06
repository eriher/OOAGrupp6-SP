/**
 * Each connecton to the server should initiate a new Communication object.
 *
 * @author Henrik Johansson
 * @version 2013-02-25
 * 
 * @param port	port number for Serversocket.
 */

package model;

import java.util.LinkedList;

import model.schedule.ScheduleHandler;

public class Communication {

	private FileManagement fileMan;

	private User user;
	private Users users;
	private ClientHandler clientHandler;

	private ScheduleHandler scheduleHandler;

	public Communication(ClientHandler clientHandler) {
		this.clientHandler = clientHandler;
		fileMan = new FileManagement();
	}

	/**
	 * Check what type of message that has been recieved. TODO fix case instead
	 * of if statements
	 */
	public void messageRecieved(LinkedList<Object> linkedMessage) {
		if (linkedMessage != null) {
			users = fileMan.getUsersList();
			System.out.println("Message recieved in communication Server"
					+ linkedMessage.toString());
			String whatToDo = (String) linkedMessage.get(0);

			if (whatToDo.compareToIgnoreCase("Login") == 0) { // Login has been
																// recieved
																// previous:
																// linkedMessage.get(0).equals("login")
				loginRecieved(linkedMessage);
				fileMan.addToLog(whatToDo, clientHandler.getAddress()); // Add
																		// event
																		// to
																		// log
			} else if (whatToDo.compareToIgnoreCase("Logout") == 0) {
				fileMan.addToLog(whatToDo, clientHandler.getAddress()); // Add
																		// event
																		// to
																		// log

			} else if (whatToDo.compareToIgnoreCase("GetAllUsers") == 0) {

				LinkedList<String> linkedMessageReturn = new LinkedList<String>(); // Formats
																					// the
																					// linkedList
																					// that
																					// is
																					// to
																					// be
																					// returned
																					// to:
				linkedMessageReturn.add("GetAllUsers"); // LinkedList(
														// "GetAllUsers",
														// "PersNr 1",
														// "PersNr 2",
														// "PersNr 3", ...,
														// "PersNr n" )
				String[] usersListStringArray = users.getAllPerNr().toString()
						.split(", ");

				for (int i = 0; i < usersListStringArray.length; i++) {
					usersListStringArray[i] = usersListStringArray[i].replace(
							"[", ""); // Removes [] that was recieved from
										// LinkedList.toString
					usersListStringArray[i] = usersListStringArray[i].replace(
							"]", "");

					linkedMessageReturn.add(usersListStringArray[i]);
				}

				clientHandler.send(linkedMessageReturn); // Client will recieve
															// a linkedList with
															// strings and the
															// first is
															// "GetAllUsers"
															// followed by the
															// personal numbers

			} else if (whatToDo.compareToIgnoreCase("GetUser") == 0) { // Sends
																		// LinkedList<(String)"GetUser",
																		// (User)user>
																		// back
																		// to
																		// client
				String persNr = (String) linkedMessage.get(1);
				user = users.getUser(persNr);
				scheduleHandler = new ScheduleHandler(user);

				LinkedList<Object> linkedMessageReturn = new LinkedList<Object>();
				linkedMessageReturn.add("GetUser");
				linkedMessageReturn.add(user);
				clientHandler.send(linkedMessageReturn);

			} else if (whatToDo.compareToIgnoreCase("RemoveUser") == 0
					|| whatToDo.compareToIgnoreCase("DeleteUser") == 0) {
				String persNr = (String) linkedMessage.get(1);
				users.remove(persNr);
				fileMan.writeUsersFile(users);

			} else if (whatToDo.compareToIgnoreCase("NewUser") == 0) { // Add
																		// new
																		// user
				// users.add((String) linkedMessage.get(1), (String)
				// linkedMessage.get(2), (String) linkedMessage.get(3));
				users.add((User) linkedMessage.get(1));

				fileMan.writeUsersFile(users);

			} else if (whatToDo.compareToIgnoreCase("EditUser") == 0) { //
				User user = users.getUser((String) linkedMessage.get(1));
				user.setPassword((String) linkedMessage.get(2));
				user.setStatus((String) linkedMessage.get(3));
				fileMan.writeUsersFile(users);

			} else if (whatToDo.compareToIgnoreCase("ChangePassword") == 0) {

				users.changePass(user.getPerNr(), (String) linkedMessage.get(1));
				fileMan.writeUsersFile(users);

			} else if (whatToDo.compareToIgnoreCase("CheckIn") == 0) {
				scheduleHandler.checkIn();
				users.replace(user);
				fileMan.writeUsersFile(users);

				/*LinkedList<Object> linkedMessageReturn = new LinkedList<Object>();
				linkedMessageReturn.add("CheckIn");
				linkedMessageReturn.add(users.getUser(user.getPerNr()));
				clientHandler.send(linkedMessageReturn);*/

			} else if (whatToDo.compareToIgnoreCase("CheckOut") == 0) {
				scheduleHandler.checkOut();
				users.replace(user);
				fileMan.writeUsersFile(users);

			/*	LinkedList<Object> linkedMessageReturn = new LinkedList<Object>();
				linkedMessageReturn.add("CheckOut");
				linkedMessageReturn.add(user);
				clientHandler.send(linkedMessageReturn);*/

			} else if (whatToDo.compareToIgnoreCase("NewTimeSlot") == 0) {
				scheduleHandler.setScheduledTime((int) linkedMessage.get(1),
						(int) linkedMessage.get(2), (int) linkedMessage.get(3),
						(String) linkedMessage.get(4),
						(String) linkedMessage.get(5));
				users.replace(user);
				fileMan.writeUsersFile(users);

			} else if (whatToDo.compareToIgnoreCase("CreateDefaultSchedule") == 0) {

				scheduleHandler.populateDefaultSchedule();
				users.replace(user);
				fileMan.writeUsersFile(users);

			}

		}
	}

	/**
	 * Handles what to do when a login has been recieved.
	 * 
	 * @param iaddr
	 *            InetAddress
	 * @param message
	 *            The message to be sent
	 * @param comm
	 *            What communication object to use
	 */
	public void loginRecieved(LinkedList<Object> message) { // A login has been
															// recieved

		if (message.get(0) instanceof String
				&& message.get(1) instanceof String
				&& message.get(2) instanceof String) {

			try { // If you send empty password server will crash without catch
				String recievedPassword = (String) message.get(2);

				user = users.getUser((String) message.get(1)); // Get all info
																// from the
																// user.
				scheduleHandler = new ScheduleHandler(user);

				if (recievedPassword.equals(user.getPassword())) { // Checks
																	// with the
																	// file to
																	// see if
																	// sent
																	// password
																	// is
																	// correct

					// Now true is to be sent back
					String status = user.getStatus(); // Get what status the
														// user has
					LinkedList<String> loginStatus = new LinkedList<String>(); // Create
																				// the
																				// linkedlist
																				// that
																				// is
																				// to
																				// be
																				// returned
					loginStatus.add("login"); // Add login as first string in
												// the list so the client knows
												// what it is recieving
					loginStatus.add(status); // Adding the status as second
												// string in the list so the
												// client know what GUI to open.
					System.out.println("login" + status
							+ ": is sent back due to RIGHT password:");

					LinkedList<Object> listToSend = new LinkedList<Object>();
					listToSend.add("login");
					listToSend.add(status);
					if (status.equals("Employee")) {
						listToSend.add(user);
					}

					clientHandler.send(listToSend);

				} else {
					System.out
							.println("False is sent back due to WRONG password");

					LinkedList<String> listToSend = new LinkedList<String>();
					listToSend.add("login");
					listToSend.add("false");

					clientHandler.send(listToSend);

					clientHandler.closeSocket();

				}
			} catch (Exception e) {
				System.out
						.println("Did not recieve password, \nor if you are experimenting with the User class user.bin file might be outdated or corrupt.\nTry the reset input param for main.");

				LinkedList<String> listToSend = new LinkedList<String>();
				listToSend.add("login");
				listToSend.add("false");

				clientHandler.send(listToSend);

				clientHandler.closeSocket();

			}
		}
	}

}
