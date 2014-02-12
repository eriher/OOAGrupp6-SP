
/**
 * ClientNode keeps track of all current connections.
 * 
 * @author Henrik Johansson
 * @version 2013-02-07
 */


package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientNode{
	private static ClientNode nod = null;
	ServerSocket server;
	
	private ClientNode(int port){		//TODO change singleton method to getInstance
		
		try {
			server = new ServerSocket(4444);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Communication comm = new Communication(server);
		
	
		
		
		
		
	}
	
	public static synchronized ClientNode singleton(int port){
		if(nod == null){
			nod = new ClientNode(port);
		}
		return nod;
	}
	
	/*public Communication newConnect(){
		
	}*/
	
	public void removeConnect(){
		
	}
	
	public void addConnection(){
		
	}
	
	
	
	

}
