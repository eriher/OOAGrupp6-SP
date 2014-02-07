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

public class CommRecieve implements Runnable {		//Should be made Observable and when a scanin.hasNextline comes in it should update Communication
	
	private static Socket soc;
	public CommRecieve(Socket soc) {
		this.soc = soc;
	}

	public void run() {
		System.out.println("hoho" + soc.toString() );
		/*
		try {
			
			
			while (true) {
				Scanner scanin = new Scanner(soc.getInputStream());

				while (scanin.hasNextLine()) {
					System.out.println(scanin.nextLine());

				}
				scanin.close();
			}
		} catch (IOException e) {
			e.printStackTrace();

		}*/
	}
}
