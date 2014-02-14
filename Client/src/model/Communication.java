/**
 * Write a description of class Communication here.
 * 
 * @author Erik Hermansson
 * @version 2013-02-10
 */

package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class Communication {

	private Socket socket;
	private static ObjectInputStream inStream;
	private static ObjectOutputStream outStream;

	public Communication() {
		connect();
	}

	private void connect() {
		try {
			InetAddress inet = InetAddress.getByName("127.0.0.1");
			socket = new Socket(inet, 4444);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			outStream = new ObjectOutputStream(socket.getOutputStream());
			inStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public void requestLogin(UserHandler user) throws IllegalArgumentException {
		if (user == null) {
			throw new IllegalArgumentException("UserHandler not found");
		}
		if (user.getUser() == null) {
			user.setUser("");
		}
		if (user.getPassword() == null) {
			user.setPassword("");
		}

		Boolean result = false;
		String logIn = user.getUser()+ " " + user.getPassword();
		
		try {
			outStream.writeObject(logIn);
			try {
				result = (Boolean) inStream.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (SocketException e) {
			System.out.println(e.toString());
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
