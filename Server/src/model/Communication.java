
/**
 * Each connecton to the server should initiate a new Communication object.
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

import controller.Workflow;

public class Communication implements Observer {
	private ServerSocket server;
	private Boolean recieveInited = false;
	private CommRecieve recComm ;
	
	private InetAddress iaddr = null;
	private String message = null;
	private Workflow flow;

	public Communication(ServerSocket server, Workflow flow) { 
		this.server = server;
		this.flow = flow;

		recieveInit();
		System.out.println("Under recieve");	//TODO remove this debug

	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg){
		if(o instanceof CommRecieve ){
			
			if(arg instanceof InetAddress){
				System.out.println("You have recieved a message from " + (InetAddress) arg );
				iaddr = (InetAddress) arg;
			}else if(arg instanceof String){
				System.out.println("You recieved this message " + (String) arg);
				message = (String) arg;
				messageRecieved();
			}
			
		}
		
		
	}
	
	/**
	 * Check what type of message has been recieved. For now it only check for login recieved.
	 */
	private void messageRecieved(){
		if(iaddr != null && message != null){
			
			
			flow.loginRecieved(iaddr, message, this);
			
			iaddr= null;
			message = null;
		}
	}

	/**
	 * Send message to specific ip address and port with the message "message".
	 * @param ipAddress	ipaddress
	 * @param port		port nr
	 * @param message	the message to be sent
	 */
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
	
	

	/**
	 * Initialise recieve part
	 */
	private void recieveInit() {
		if(!recieveInited){
			recieveInited = true;
			CommRecieve recComm = new CommRecieve(server);
			recComm.addObserver(this);
			Thread recieve = new Thread( recComm );
			recieve.start();
		}
		
		
	}

}

