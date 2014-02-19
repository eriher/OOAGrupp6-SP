/**
 * Set up a connection to the server with IP and port number from a config file. 
 * Responsible for sending log in request from a user and receiving if it worked.
 * Also takes care of receiving the user's schedule and sending modified user schedules back to the server.
 * 
 * @author Erik Hermansson
 * @version 2013-02-18
 */

package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;

public class Communication extends Observable {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String ip;
	private int portNumber;

	/**
	 * Init port number and IP that the client needs to create a socket against.
	 */
	public Communication() {
		// TODO Read these two variables from a text file instead of hard coded
		// like this
		ip = "localhost";
		portNumber = 4444;
	}

	/**
	 * Set up a new socket and connect to the given ip and port number.
	 */
	private void connect() {
		try {
			socket = new Socket(InetAddress.getByName(ip), portNumber);
			socket.setSoTimeout(10000);
			
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close down the socket.
	 */
	public void disconnect(){
		try {
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/**
	 * @param username
	 *            ID to be sent to server.
	 * @param password
	 *            Password to be sent to server.
	 */
	public void requestLogin(String username, String password) {
		if (username == null) {
			username = "";
		}
		if (password == null) {
			password = "";
		}
		connect();

		String result = "false";
		String s = "login " + username + " " + password;
		try {
			out.writeObject(s);
			out.flush();
			// getInputStream is blocking, initiate just before first receive
			in = new ObjectInputStream(socket.getInputStream());
			result = (String) in.readObject();
			// IOException & ClassNotFoundException
		} catch (Exception e) {
			e.printStackTrace();
			disconnect(); // TODO Possible move/remove this.
		}

		setChanged();
		notifyObservers(result);
	}
}
