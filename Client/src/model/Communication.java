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
import java.util.LinkedList;
import java.util.Observable;

public class Communication extends Observable {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String ip;
	private int portNumber;

	/**
	 * Read port number and IP from config.txt
	 *  which the client needs to create a socket against.
	 */
	public Communication() {
		String s = FileManagement.getInstance().readLine("config.txt");
		String[] sArr = s.split(";");
		ip = sArr[0];
		portNumber = Integer.parseInt(sArr[1]);

	}

	/**
	 * Set up a new socket and connect to the given ip and port number.
	 */
	private void connect() {
		if (socket == null) {
			try {

				socket = new Socket(InetAddress.getByName(ip), portNumber);
				socket.setSoTimeout(10000);

				out = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Close down the socket.
	 */
	public void disconnect() {
		try {
			// Possible null pointers exception can happen if these are not
			// checked
			out.close();
			out = null;
			in.close();
			in = null;
			socket.close();
			socket = null;
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/**
	 * Open the socket's input stream if one of the following is met:
	 * 
	 * 1) Socket's input isn't connected.
	 * 
	 * 2) The object input stream is null.
	 */
	private void openInputStream() {
		if (in == null) {
			try {
				in = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 *            Additional arguments to be sent with the message.
	 */
	public void send(Object... args) {
		connect();

		LinkedList<Object> argsList = new LinkedList<Object>();
		for (Object o : args) {
			argsList.add(o);
		}

		try {
			out.writeObject(args);
			out.flush();
			openInputStream();
			setChanged();
			notifyObservers(in.readObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
