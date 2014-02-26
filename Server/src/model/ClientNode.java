/**
 * ClientNode keeps track of all current connections.
 * 
 * @author Henrik Johansson
 * @version 2013-02-07
 */

package model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class ClientNode implements Observer {
	private static ClientNode nod = null;

	private HashMap<InetAddress, Communication> clientConnected;
	private ServerSocket server;
	private CommRecieve recComm;
	
	/**
	 * @param port
	 *            Porten
	 * 
	 */
	private ClientNode(int port) {			//Private constructor use getInstance. (singleton)

		try {
			server = new ServerSocket(port);
			recieveInit();

		} catch (IOException e) {
			System.out.println("Another instance of server is already initialized, or some other program is using port: " + port +"\nServer shutdown.");
			//e.printStackTrace();		//TODO remove after debug
		}

	}

	public static synchronized ClientNode getInstance(int port) {
		if (nod == null) {
			nod = new ClientNode(port);
		}
		return nod;
	}

	public void update(Observable o, Object arg) {
		if (o instanceof CommRecieve && arg instanceof InetAddress) {
			// Check if inetaddress is already in list
			// if not add new, otherwise do nothing

			
			addConnection((InetAddress) arg, ((CommRecieve) o).getSocket());
		}
			
			/*if (!clientConnected.containsKey(arg)) {
				addConnection((InetAddress) arg, ((CommRecieve) o).getSocket());
			}
		} else*/ if (o instanceof CommRecieve && arg instanceof LinkedList) {
			// Recieved message from Inetaddress send to Communication part

			forwardMessage(((CommRecieve) o).getInetAddress(), (LinkedList<Object>) arg);
		}
		else if(o instanceof Communication){	//Remove Connection 
			System.out.println("Remove connection (in update)");
			clientConnected.remove(   ( (Communication) o).getInetAddress() );
		}
	}

	private void recieveInit() {
		clientConnected = new HashMap<InetAddress, Communication>();
		recComm = new CommRecieve(server);
		recComm.addObserver(this);
		Thread recieve = new Thread(recComm);
		recieve.start();

	}

	private void addConnection(InetAddress iaddr, Socket soc) {
		System.out.println("Add Connection");
		clientConnected.put(iaddr, new Communication(soc));
		clientConnected.get(iaddr).addObserver(this);				
		


	}

	private void forwardMessage(InetAddress iaddr, Object message) {
		if (clientConnected.containsKey(iaddr)) {
			clientConnected.get(iaddr).messageRecieved(iaddr, (LinkedList) message);
			new Thread(clientConnected.get(iaddr)){}
			.start();

		} else {
			System.out.println("HashMap did not contain InetAddress: " + iaddr);
		}
	}
	
	public void killClientNode(){		
		recComm.kill();
		clientConnected.clear();
		nod = null;
	}

}
