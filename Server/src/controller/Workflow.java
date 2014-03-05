/**
 * This is the overall controller of the Server
 * 
 * @author  Henrik Johansson
 * @version 2013-02-12
 */

package controller;

import java.io.File;
import java.io.IOException;

import view.Window;

public class Workflow {
	private final int SERVER_PORT = 4444;
	private boolean file;

	public Workflow() {
		createLog();
		initWindowThread(SERVER_PORT);
	
	}

	private void initWindowThread(int SERVER_PORT) { //Initializes the Window it was supposed to be a initModelThread as well but it is instead located in actionhandler for consistancy
		Window window = new Window(SERVER_PORT);
	}
	
	/*  @Author Olof Spetz
	 *  Checks if log.txt exists, if not create new file log.txt
	 * 
	 */
	private void createLog()
	{
		File log = new File("log.txt");
		
		if(!log.exists())
		{
			try 
			{
				file = new File("log.txt").createNewFile();
			}
			catch(IOException e)
			{
				System.out.println("Could not create log.txt");
			}
		}
	}

}
