/**
 * Sets up a in/out streams for the given clientsocket.
 * Creates a communication which contains commands server can perform.
 * Reads objects from inputsttream forwards to Communication.
 * Writes objects to outputstream
 * 
 * @author Erik Hermansson
 * @version 2013-02-07
 * 
 * @param clientsocket	socket for the client.
 */

package model;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;


public class ClientHandler extends Thread {
	
	private Socket			clientSocket;
	private Communication	cmnds;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		cmnds = new Communication(this);
		start();
	}
	/**
	 * sets up the in and out streams towards the clientsocket.
	 */
	public void run()
	{
		System.out.println("Connection recieved");
		try
		{
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
			handleRequests();
	}
	/**
	 * reads the in stream and checks them 
	 * using the messageRecieved method from Communication
	 */
	private void handleRequests() {
		LinkedList<Object> request = null;
		while(true)
		{
			try
			{
				request = (LinkedList<Object>) in.readObject();
			}
			catch(Exception e)
			{
				try
				{
					in.close();
					clientSocket.close();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
				return;
			}
			cmnds.messageRecieved(request);
		}
	}
	/**
	 * sends listToSend to client
	 * 
	 * @param listToSend
	 */
	public void send(Object listToSend) {
		try{
			out.writeObject(listToSend);
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
	/**
	 * Closes the socket
	 */
	public void closeSocket() {
		try {
			clientSocket.close();
		} catch (IOException e1) {
			System.out.println("Could not close socket");
			e1.printStackTrace();
		}
	}
		
}

