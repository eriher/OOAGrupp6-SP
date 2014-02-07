/**
 * Write a description of class ClientNode here.
 * 
 * @author David Stromner
 * @author Henrik Johansson
 * @version 2013-02-07
 */


package model;

import java.io.IOException;
import java.net.ServerSocket;

public class ClientNode{
	private static ClientNode nod = null;
	ServerSocket server;
	
	private ClientNode(int port){		//Must be made Singleton method or else ServerSocket will already be occupied.
		
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Communication comm = new Communication(server);
		System.out.println("hejsan");
		
		
		
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
