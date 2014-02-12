
/**
 * Each connecton to the server should initiate a new Communication objec.
 *
 * @author Henrik Johansson
 * @version 2013-02-12
 * 
 * @param port	port number for Serversocket.
 */

package model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

public class Communication implements Observer {
	private ServerSocket server;
	//private Socket soc; TODO remove
	private Boolean recieveInited = false;
	private CommRecieve recComm ;

	public Communication(ServerSocket server) { 
		this.server = server;

		//recieveInit();
		System.out.println("Under recieve");
		
		
		
		InetAddress iaddr;
		try {
			iaddr = InetAddress.getByName("localhost");
			send(iaddr , 4445, "hajhajserver");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public void update(Observable o, Object arg){
		System.out.println("CommRecieve has uppdated something");
	}

	public void send(InetAddress ipAddress, int port, String message){	//Send message to ip ipAddress on port port lol :)
				
			try {
				Socket sendSoc = new Socket(ipAddress, port);
				DataOutputStream out = new DataOutputStream(sendSoc.getOutputStream() );
				out.writeBytes(message);
				sendSoc.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		

	}
	
	public String recieveMessage(){
		return recComm.getMessage();
	}

	public void recieveInit() {
		if(!recieveInited){
			recieveInited = true;
			CommRecieve recComm = new CommRecieve(server);
			recComm.addObserver(this);
			Thread recieve = new Thread( recComm );
			recieve.start();
		}
		
		
	}

}

