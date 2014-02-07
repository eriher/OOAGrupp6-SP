/**
 * Creates receiving part of Communication class
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
import java.util.Scanner;

public class CommRecieve implements Runnable {
	private ServerSocket server;
	private int port;
	
	public CommRecieve(int port) {
		this.port = port;
		

	}

	public void run() {

		try {
			server = new ServerSocket(port);
			
			
			while (true) {
				Socket soc = server.accept();
				Scanner scanin = new Scanner(soc.getInputStream());

				while (scanin.hasNextLine()) {
					System.out.println(scanin.nextLine());

				}
				scanin.close();
			}
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}
