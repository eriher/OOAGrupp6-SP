/**
 * Write a description of class Communication here.
 * 
 * @author Erik Hermansson
 * @version 2013-02-14
 */

package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communication {

	private Socket socket;
	private BufferedReader in;
	private DataOutputStream out;
	private InetAddress ip;
	private int portNumber;

	/**
	 * 
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
			in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			out = new DataOutputStream(socket.getOutputStream());
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
	public void requestLogin(UserHandler user) throws IllegalArgumentException {
		if (user == null) {
			throw new IllegalArgumentException("UserHandler not found");
		}

		connect();

		Boolean result = false;
		try {
			out.writeBytes(user.getUser() + " " + user.getPassword());
			in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (result == true) {
			System.out.println("Logged in!");
		} else {
			System.out.println("Bad username or password");
		}
	}
}
