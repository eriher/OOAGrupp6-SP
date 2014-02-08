/**
 * Write a description of class Communication here.
 * 
 * @author David Stromner
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

public class Communication {
	private ServerSocket server;
	private Socket soc;

	public Communication(ServerSocket server) { // Should be made observer of
												// CommRecieve
		this.server = server;

		try {
			

			Boolean again = true;
			while (again) {
				soc = server.accept();
				BufferedReader buffReader = new BufferedReader(
						new InputStreamReader(soc.getInputStream()));

				String meddelande = buffReader.readLine();

				System.out.println(meddelande);

				soc.close();
			}

			server.close();

		} catch (IOException e) {

		}

	}

	public void send() {

	}

	public void recieve() {

	}

}
