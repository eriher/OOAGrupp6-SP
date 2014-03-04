/**
 * Sets up a serversocket for the given port number
 * listens after new connections and creates a clienthandler for each
 * 
 * @author Erik Hermansson
 * @version 2013-02-07
 * 
 * @param port	port number for Serversocket.
 */

package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CommRecieve extends Thread{
	private ServerSocket serverSocket;
	
	public CommRecieve(int port)
	{
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}
	/**
	 * listens after connections until stopped.
	 */
	public void run()
	{
		while(true)
		{
			try
			{
				Socket clientSocket = serverSocket.accept();
				createClientHandler(clientSocket);
			} 
			catch (IOException e)
			{
				break;	//this will be invoked when socket is closed, killing the thread
			}
		}
	}
	
	/**
	 * Creates a ClientHandler when client has established connection
	 */
	private void createClientHandler(Socket clientSocket) {
		new ClientHandler(clientSocket);
		
	}
	/**
	 * Closes the server socket and stops the thread
	 */
	public void close()
	{
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
