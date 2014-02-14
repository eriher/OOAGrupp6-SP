/**
 * Send string message to ipaddres on specific port
 * 
 * @author Henrik Johansson
 * @version 2013-02-12
 * 
 */


// TODO Debug probably all unneccesary remove whole class

package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class CommSend {
	InetAddress ipAdress;
	int port;
	
	public CommSend(InetAddress ipAddress, int port, String message){	//Send message to ip ipAddress on port port
		this.ipAdress = ipAddress;
		this.port = port;
		
		try {
			Socket soc = new Socket(ipAddress, port);
			PrintWriter out = new PrintWriter(soc.getOutputStream() );
			out.write(message); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	

}
