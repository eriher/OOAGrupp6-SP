package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.joda.time.DateTime;

import controller.ActionHandler;

import model.Communication;
import model.User;
import model.schedule.ScheduleHandlerClient;
import controller.Workflow;

/**
 * Creates the GUI part of the schedule
 * 
 * @author Benjamin Wijk
 * @version 2014-03-03
 */
public class JSchedule extends JPanel implements Observer 
	{
	protected LinkedList<JPanel> plannedWorkList = new LinkedList<JPanel>();
	protected LinkedList<JPanel> plannedActualList = new LinkedList<JPanel>();
	protected LinkedList<JTextArea> scheduleList = new LinkedList<JTextArea>();
	private ScheduleHandlerClient handler;
	public JSchedule() {
		add(initBuild());
	}

	public void update(Observable o, Object arg) {
		//add(initBuild());
		handler = (ScheduleHandlerClient)arg;
		fillPanels();
		
	}

	/**
	 * @return JSchedule
	 * Initializes Swing objects and builds the schedule GUI, returning it as a JPanel-object
	 */
	public JPanel initBuild() {

		JPanel pane = new JPanel(new GridBagLayout());
		JPanel days[] = createPanels(1);
		JPanel dates[] = createPanels(1);
		JPanel planContainer[] = createPanels(2);
		
		LinkedList<JLabel> dateList = new LinkedList<JLabel>();	
		dateList.add(createJLabel("Monday", "space"));
		dateList.add(createJLabel("Tuesday", "space"));
		dateList.add(createJLabel("Wednesday", "space"));
		dateList.add(createJLabel("Thursday", "space"));
		dateList.add(createJLabel("Friday", "space"));
		dateList.add(createJLabel("Saturday", "space"));
		dateList.add(createJLabel("Sunday", "space"));
		
		
		for(int x = 0; x <= 6; x++){
			dates[x].add(dateList.get(x));
		}
		GridBagConstraints c = new GridBagConstraints();

		//JLabel displayWeek = createJLabel("Vecka 10"); // STATISKT VÄRDE ATM
		// ÄNDRA!

		JButton nextWeek = new JButton("Next");
		nextWeek.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Workflow.getInstance().getScheduleHandler().getNextWeek();
				fillPanels();
			}
		});
		
		JButton prevWeek = new JButton("Previous");
		prevWeek.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Workflow.getInstance().getScheduleHandler().getPrevWeek();
				fillPanels();
			}
		});
		
		
		// Grid-Setup start ----------------------
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(prevWeek, c);

	/*	
	 	// Visar currentWeek i top-mid OBS STATISKT VÄRDE
		// ATM!!!!!!!!!!!!!!!!!!!!!!!!!!
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridy = 0;
		pane.add(displayWeek, c);
	*/
		

		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 6;
		c.gridy = 0;
		pane.add(nextWeek, c);

		for (int i = 0; i <= 6; i++) {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = i;
			c.gridy = 3;
			pane.add(days[i], c);
			days[i].add(dates[i], c);
			days[i].add(planContainer[i], c);
		}
		c.anchor = GridBagConstraints.CENTER;
		
		return pane;
	}

	/**
	 * if a schedule exists, clear it and replace the data, filling the JSchedule with the new data.
	 */
	public void fillPanels() {
		for(JPanel temp: plannedWorkList)
			temp.removeAll();
		for(JPanel temp: plannedActualList)
			temp.removeAll();
		
		
		
		
		ArrayList<Integer> checkIn = null; 
		ArrayList<Integer> checkOut = null;
		ArrayList<Integer> plannedTime = null;
		ArrayList<ArrayList<Integer>> totalList;
		

		/*
		LinkedList<String> dayName = new LinkedList<String>();

		dayName.add("Monday");
		dayName.add("Tuesday");
		dayName.add("Wednesday");
		dayName.add("Thursay");
		dayName.add("Friday");
		dayName.add("Saturday");
		dayName.add("Sunday");
		 */
		
		  //TESTV�RDEN START ------------------------------------------
		 /* ArrayList<Integer> checkInList = new ArrayList<Integer>(); 
		  checkInList.add(480+60); 
		  checkInList.add(480+140);
		  checkInList.add(480+190);
		  checkInList.add(480+270);
		  checkInList.add(480+320);
		  
		  
		  ArrayList<Integer> checkOutList = new ArrayList<Integer>();
		  checkOutList.add(480+100); 
		  checkOutList.add(480+180);
		  checkOutList.add(480+250);
		  checkOutList.add(480+315);
		  checkOutList.add(480+390);
		  
		  ArrayList<Integer> scheduledTimeList = new ArrayList<Integer>();
		  scheduledTimeList.add(480+15); 
		  scheduledTimeList.add(480+120);
		  scheduledTimeList.add(480+135);
		  scheduledTimeList.add(480+240);
		  scheduledTimeList.add(480+315);
		  scheduledTimeList.add(480+420);
		  scheduledTimeList.add(480+435);
		  scheduledTimeList.add(480+540);
		   ArrayList<ArrayList<Integer>> totalList = new ArrayList<ArrayList<Integer>>(3);
		  
		  totalList.add(checkInList); 
		  totalList.add(checkOutList);
		  totalList.add(scheduledTimeList);*/
		  //TESTV�RDEN SLUT --------------------------------------------------------------------
		 



		for (int i = 0; i <= 6; i++) {

			totalList = handler.scheduleToDays(i);

			/**
			 * Retrieves the 3 lists in totalList and assigns them for reference purpose.
			 */
			ArrayList<Integer> tempFill;
			int listNumber = 0;
			Iterator<ArrayList<Integer>> it = totalList.iterator(); 
			while (it.hasNext()) {
				listNumber++;
				tempFill = it.next();
				if (listNumber == 1)
					checkIn = tempFill;
				if (listNumber == 2)
					checkOut = tempFill;
				if (listNumber == 3)
					plannedTime = tempFill;
			}
			
			/**
			 * Fills the panel containing check-ins and check-outs with corresponding JLabels, filling out space in between with blank JLabels.
			 */
			
			if(checkIn.size()>0 && checkOut.size()>0)
			{
            for(int x = 0; x<checkIn.size(); x++){ 
            	
            	JLabel space = createJLabel("", "space");
	            	space.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
	            	if(checkIn.get(0) != 480 && x == 0 )
	        			space.setPreferredSize(new Dimension(80, checkIn.get(0) - 480));
	        		else
	        			space.setPreferredSize(new Dimension(80, checkIn.get(x)-checkOut.get(x-1)));
        		if(checkIn.size() == checkOut.size()){
	            	JLabel actualFields = createJLabel("" + toHours(checkIn.get(x)) + "-" + toHours(checkOut.get(x)), "actual");    	 
	          	   		actualFields.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
	          	   		actualFields.setPreferredSize(new Dimension(80, checkOut.get(x) - checkIn.get(x)));          	   		
	          	   		plannedActualList.get(i).add(actualFields);
        		}
          	   	plannedActualList.get(i).add(space);
          	   	
            }
            JLabel checkEnd = createJLabel("", "space");
            	checkEnd.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
            	checkEnd.setPreferredSize(new Dimension(80, 1020 - checkOut.get(checkOut.size()-1)));
            plannedActualList.get(i).add(checkEnd);
			}
			else{
				JLabel spaceFill = createJLabel("","space");
				spaceFill.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
				spaceFill.setPreferredSize(new Dimension(80, 1020-480));
			}
            
			/**}
             *  Fills the panels containing planned time with corresponding JLabels, filling out space in between with blank JLabels.
             */
			if(plannedTime.size()>0)
			{
            for(int x = 0; x<=0; x+=2){ 
            	JLabel space = createJLabel("", "space");
	            	space.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
	            	//if(plannedTime.get(0) != 480 && x == 0 )
	        			space.setPreferredSize(new Dimension(80, plannedTime.get(0) - 480));
	        		//else
	        			//space.setPreferredSize(new Dimension(100, plannedTime.get(x) - plannedTime.get(x-1)));
           
           		JLabel workFields = createJLabel("" + toHours(plannedTime.get(x)) + "-" + toHours(plannedTime.get(x+1)), "work");
           			workFields.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
           			workFields.setPreferredSize(new Dimension(80, plannedTime.get(x+1) - plannedTime.get(x)));
           		
	    	   plannedWorkList.get(i).add(space);
	    	   plannedWorkList.get(i).add(workFields);
            }
            JLabel workEnd = createJLabel("", "space");
	        	workEnd.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
	        	workEnd.setPreferredSize(new Dimension(80, 1020 - plannedTime.get(plannedTime.size()-1)));
	        	plannedWorkList.get(i).add(workEnd);
			}
		
		} //END OF 7-DAY FOR-LOOP

		updateUI();

	}
	
	
	/**
	 * Converts a value of minutes to a string displaying it as HH:MM
	 * @param minutes
	 * @return String ("HH:MM")
	 */
	
	public String toHours(int minutes) {
		String[] s = { "" + minutes / 60, "" + minutes % 60 };

		if (minutes / 60 < 10)
			s[0] = "0" + minutes / 60;
		if (minutes % 60 < 10)
			s[1] = "0" + minutes % 60;

		return s[0] + ":" + s[1];
	}

	/**
	 * Creates a "standard" JLabel with a specified name. It also has a different color depending on arg.
	 * @param text
	 * @param arg
	 * @return the JLabel created
	 */
	
	public JLabel createJLabel(String text, String arg) {
		JLabel label = new JLabel(text);
		label.setOpaque(true);
		label.add(Box.createVerticalGlue());
		label.setForeground(Color.BLACK);
		if(arg == "actual")
			label.setBackground(new Color(160,200,100));
		if(arg == "space")
			label.setBackground(new Color(255,255,255));
		if(arg == "work")
			label.setBackground(new Color(180,50,50));

		return label;
	}

	/**
	 * Creates JPanels for either 
	 * @param x
	 * @return
	 */
	
	public JPanel[] createPanels(int x) {
		JPanel[] panes = new JPanel[7];
		switch (x) {

		case 1:
			for (int i = 0; i <= 6; i++) {
				panes[i] = new JPanel();
				panes[i].setLayout(new BoxLayout(panes[i], BoxLayout.PAGE_AXIS));
				panes[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));

			}
			break;

		case 2:
			for (int i = 0; i <= 6; i++) {
				panes[i] = new JPanel();
				JPanel planned[] = new JPanel[2];
				for (int a = 0; a <= 1; a++) {
					planned[a] = new JPanel();
					planned[a].setLayout(new BoxLayout(planned[a],
							BoxLayout.PAGE_AXIS));
					planned[a].setBorder(BorderFactory
							.createLineBorder(Color.BLACK));

					panes[i].add(planned[a]);

					if (a == 0)
						plannedWorkList.add(planned[a]);
					else
						plannedActualList.add(planned[a]);
				}

			}

		}
		return panes;
	}

}