/**
 * Sets up the frame
 * 
 * @author olof spetz
 * @version 2013-02-20
 */
package view;
import controller.ActionHandler;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Olof
 *
 */

public class Window {
	
	private JFrame frame;
	private JButton start, stop;
	private JPanel panel1, panel2;
	private JTextField ip, port;
	private JLabel ipadr, portadr;
	private JMenuItem startItem, stopItem;
	
	public Window()
	{
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
				ActionHandler.getInstance().startButton();
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
		
		ip = new JTextField();
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
				ActionHandler.getInstance().startButton();
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
		String address = "192.168.1.5";
		return address;
	}
	
	public String getPort()
	{
		String port = "1234";
		return port;
		
	}
}



