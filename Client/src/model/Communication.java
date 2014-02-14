/**
 * Write a description of class Communication here.
 * 
 * @author Erik Hermansson
 * @version 2013-02-14
 */

package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communication {

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
	 * @param user
	 *            Object containing information about the current user.
	 * @throws IllegalArgumentException
	 *             If 'user' is null.
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

		// TODO Remove this debug.
		if (result == true) {
			System.out.println("Logged in!");
		} else {
			System.out.println("Bad username or password");
		}
	}
}
