package model;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 * @author Erik
 * @version 2014-02-27
 */
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

	public void send(Object listToSend) {
		try{
			out.writeObject(listToSend);
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void closeSocket() {
		try {
			clientSocket.close();
		} catch (IOException e1) {
			System.out.println("Could not close socket");
			e1.printStackTrace();
		}
	}
		
}

