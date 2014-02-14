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
import java.net.Socket;
import java.net.SocketException;

public class Communication {

	private Socket socket;
	private static ObjectInputStream inStream;
	private static ObjectOutputStream outStream;

	public Communication()
	{
		connect();
	}

	private void connect()
	{
		try {
			socket = new Socket("127.0.0.1",1234);
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

	public String requestLogin(UserHandler user)
	{
		String result = "0";
		try {
			outStream.writeObject(user);
			try {
				result = (String)inStream.readObject();
				}	catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				}
		}
		catch(SocketException e)
		{
			System.out.println(e.toString());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}

}
