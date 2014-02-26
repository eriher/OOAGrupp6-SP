/**
 * Sets up the frame
 * 
 * @author olof spetz
 * @version 2013-02-20
 */
package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ActionHandler;

/**
 * @author Olof
 *
 */

public class Window {
	
	private JFrame frame;
	private JButton start, stop;
	private JPanel panel1, panel2;
	private JTextField port;
	private JTextArea ip;
	private JLabel ipadr, portadr;
	private JMenuItem startItem, stopItem;
	private int SERVER_PORT;
	
	public Window(int SERVER_PORT)
	{
		this.SERVER_PORT = SERVER_PORT;
		buildWindow();
	}
	
	public void buildWindow(){
		
		frame = new JFrame("Stamp");
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		makeMenuBar();

		//Create Buttons
		start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().startButton(SERVER_PORT);
				enableStartStop();
				}
		});
		
		stop = new JButton("Stop");
		stop.setEnabled(false);
		stop.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) { 
				ActionHandler.getInstance().stopButton();
				enableStartStop();
			}
		});
		
		panel1.add(start);
		panel1.add(stop);
		
		//Create Labels
		
		ipadr = new JLabel();
		portadr= new JLabel();
		
		ipadr.setText("IP-address:");
		portadr.setText("Port:");
		
		// Create textfields
		
		ip = new JTextArea();
		port = new JTextField();

		ip.setEditable(false);
		ip.setText(getAddress());

		port.setEditable(false);
		port.setText(getPort());
		
		panel2.add(ipadr);
		panel2.add(ip);
		panel2.add(portadr);
		panel2.add(port);
		
		contentPane.add(panel1, BorderLayout.SOUTH);
		contentPane.add(panel2, BorderLayout.CENTER);
		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,200);
		frame.setVisible(true);
	}
	
	public void makeMenuBar()
	{
		// Create Menubar
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		// Create Menus
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		//Create MenuItems
		
		startItem = new JMenuItem("Start");
		startItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().startButton(SERVER_PORT);
				enableStartStop();
			}
		});
		fileMenu.add(startItem);
		
		stopItem = new JMenuItem("Stop");
		stopItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().stopButton();
				enableStartStop();
			}
		});
		stopItem.setEnabled(false);
		fileMenu.add(stopItem);
		
		JMenuItem aboutItem = new JMenuItem("About Stamp");
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().about();
			}
		});
		helpMenu.add(aboutItem);
		
	}
	
		public void enableStartStop()
	{
		if(start.isEnabled())
			{
				start.setEnabled(false);
				stop.setEnabled(true);
				
				startItem.setEnabled(false);
				stopItem.setEnabled(true);
			}
		else if(!start.isEnabled())
			{
				start.setEnabled(true);
				stop.setEnabled(false);
				
				startItem.setEnabled(true);
				stopItem.setEnabled(false);
			}		
	}
	
	public String getAddress()
	{	
		String addressLocal = "unknown", addressPublic = "unknown";
		try {
			addressLocal = InetAddress.getLocalHost().getHostAddress();				//Get local ip address
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try{																		//Get public ip address
			URL giefIPAddress = new URL("http://checkip.amazonaws.com");								//Only one line with your public ip address
			BufferedReader in = new BufferedReader(new InputStreamReader(giefIPAddress.openStream()));
			addressPublic = in.readLine();
			System.out.println("addressPublic");
		}catch(UnknownHostException e){
			System.out.println("No internet connection, thereforce no Public ip");
			//e.printStackTrace();
		}
		catch(MalformedURLException e){
			System.out.println("Faulty URL for public IP address, if you dont have an internet connection this is nothing to worry about");
			e.printStackTrace();
		}catch(IOException e){
			System.out.println("Could not read from URL provided");
			e.printStackTrace();
		}	*/																	
		String allAddress ="Local: " +  addressLocal + "\nPublic: " + addressPublic;
		
		
		
		return allAddress;
	}
	
	public String getPort()
	{
		String port = Integer.toString(SERVER_PORT) ;
		return port;
		
	}
}



