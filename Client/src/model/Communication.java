/**
 * Write a description of class Communication here.
 * 
 * @author Erik Hermansson
 * @version 2013-02-16
 */

package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class Communication extends Observable{

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private InetAddress ip;
	private int portNumber;

	/**
	 * Init port number and IP that the client needs to create a socket against.
	 */
	public Communication() {
		// TODO Read these two variables from a text file instead of hard coded
		// like this
		try {
			ip = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		portNumber = 4444;
	}

	/**
	 * Set up a new socket and connect to the given ip and port number.
	 */
	private void connect() {
		try {
			socket = new Socket(ip, portNumber);
			socket.setSoTimeout(10000);
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param username ID to be sent to server.
	 * @param password Password to be sent to server.
	 */
	public void requestLogin(String username, String password) {
		if (username == null) {
			username = "";
		}
		if (password == null) {
			password = "";
		}

		connect();

		Boolean result = false;
		try {
			out.writeBytes(username + " " + password);
			result = (Boolean) in.readObject();
			// IOException & ClassNotFoundException
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setChanged();
		// TODO Send a char/int/string that identifies the user level instead of Boolean.
		notifyObservers(result);
	}
}
