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
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;

public class Communication extends Observable {
	
	private Socket socket;
	private static ObjectInputStream in;
	private static ObjectOutputStream out;
	
	public Communication()
	{
		
		connect();
	}

	/**
	 * initiates connection
	 */
	private void connect()
	{
		String ip;
		Integer port;
		FileManagement.getInstance().openReadFile("config.txt");
		String s = FileManagement.getInstance().readFile();
		String[] sArr = s.split("\\n");
		ip = sArr[0];
		port = Integer.parseInt(sArr[1]);
		InetAddress toAddr = null;
		try 
		{
			toAddr = InetAddress.getByName(ip);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}
		try {
			socket = new Socket(toAddr,port);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	/**
	 *		closes connections
	 */
	public void disconnect()
	{
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @param Object[] 
	 *            Includes command and information.
	 */
	public void communicate(Object[] cmnd)
	{
		Object result = null;
		try {
			out.writeObject(cmnd);
			try {
				result = in.readObject();
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
		setChanged();
		notifyObservers(result);
	}
}