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

import controller.Workflow;

public class ClientNode {
	private static ClientNode nod = null;
	ServerSocket server;

	/**
	 * @param port
	 *            Porten
	 * 
	 */
	private ClientNode(int port, Workflow flow) { // TODO change singleton method to
									// getInstance

		try {
			server = new ServerSocket(4444);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Communication comm = new Communication(server, flow);

	}

	public static synchronized ClientNode getInstance(int port, Workflow flow) {
		if (nod == null) {
			nod = new ClientNode(port, flow);
		}
		return nod;
	}

	/*
	 * public Communication newConnect(){
	 * 
	 * }
	 */

	public void removeConnect() {

	}

	public void addConnection() {

	}

}
