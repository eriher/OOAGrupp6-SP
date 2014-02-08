/**
 * Creates receiving part of Communication class
 * 
 * @author Henrik Johansson
 * @version 2013-02-07
 * 
 * @param port	port number for Serversocket.
 */

package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class CommRecieve implements Runnable {		//Should be made Observable and when a scanin.hasNextline comes in it should update Communication
	
	private Socket soc;
	public CommRecieve(Socket soc) {
		this.soc = soc;
	}

	public void run() {
	

		
	}
}
