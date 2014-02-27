/**
 * Creates receiving part of Communication class, when starting the thread it will check for new incomming messages.
 * 
 * @author Henrik Johansson
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
	private Boolean haltflag;
	
	public CommRecieve(int port)
	{
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}
	
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
				break;
			}
		}
	}

	private void createClientHandler(Socket clientSocket) {
		new ClientHandler(clientSocket);
		
	}
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
