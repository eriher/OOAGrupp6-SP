package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

//import controller.ActionHandler;
import model.Test;



public class ScheduleGUI extends JPanel {
	protected LinkedList<JPanel> plannedWorkList = new LinkedList<JPanel>();
	protected LinkedList<JPanel> plannedActualList = new LinkedList<JPanel>();
	
	public static void main(String[] args) {
		
		ScheduleGUI bob = new ScheduleGUI();
	}
		
	public ScheduleGUI(){  //Strukturerar om jag har tid. D.v.s. vääääldigt osannolikt
		init();
		fillPanels();
	}
	
	public void init(){ 
		JFrame frame = new JFrame(); //TEMPORÄR
		
		JPanel pane = new JPanel(new GridBagLayout());
		JPanel days[] = createPanels(1);
		JPanel dates[] = createPanels(1);
		JPanel planContainer[] = createPanels(2);
		
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel displayWeek = createJLabel("Vecka 9"); //STATISKT VÄRDE ATM ÄNDRA!
		
		JButton nextWeek = new JButton ("Next");
		JButton prevWeek = new JButton ("Previous");
	//	buttonSetup(nextWeek, prevWeek);
		
		
		//Grid-Setup start ----------------------
		nextWeek.setPreferredSize(new Dimension(85, 30));
		c.anchor = GridBagConstraints.LINE_START;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(nextWeek, c);
		
		//Visar currentWeek i top-mid OBS STATISKT VÄRDE ATM!!!!!!!!!!!!!!!!!!!!!!!!!!
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridy = 0;
		pane.add(displayWeek, c);
		
		//prevWeek.setPreferredSize(new Dimension(85, 30));
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 6;
		c.gridy = 0;
		pane.add(prevWeek, c);
		
		for(int i = 0; i<=6 ; i++){
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = i;
			c.gridy = 2;
			pane.add(days[i],c);
			days[i].add(dates[i],c);
			days[i].add(planContainer[i],c);
		}
		c.anchor = GridBagConstraints.CENTER;
		//Grid-Setup end
		
		
		frame.add(pane);
		frame.pack();
		frame.setVisible(true);
	}
	
    public void fillPanels(){ 
		Test testu = new Test();
		ArrayList<ArrayList<Integer>> totalList = testu.getSchedule();
		
		Iterator<JPanel> it = plannedActualList.iterator();
		while(it.hasNext()){
			
		}
		
	
	}
	
/*	public void buttonSetup(JButton next, JButton previous){
		
		next.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//getNextWeek
			}
		});
		
		previous.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				//getPreviousWeek
			}
		});
		
		
		
	}*/
	
	
	public JLabel createJLabel(String text){
		JLabel label = new JLabel();
		label.setOpaque(true);
		label.setText(text);
		label.setForeground(Color.BLACK);
		
		return label;
	}
	
	
	public JPanel[] createPanels(int x){
		JPanel [] panes = new JPanel[7];
		switch(x){
		
			case 1:
				for(int i=0; i<=6 ; i++){
					panes[i] = new JPanel();
					panes[i].setLayout(new BoxLayout(panes[i], BoxLayout.PAGE_AXIS));
					panes[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
					
				}
				break;
			
			case 2:
				for(int i=0; i<=6 ; i++){
					panes[i] = new JPanel();
					JPanel planned[] = new JPanel[2];
					for(int a=0; a<=1; a++){
						planned[a] = new JPanel();
						planned[a].setLayout(new BoxLayout(planned[a], BoxLayout.PAGE_AXIS));
						planned[a].setBorder(BorderFactory.createLineBorder(Color.BLACK));
						if(a == 0)
							plannedWorkList.add(planned[a]);
						else
							plannedActualList.add(planned[a]);
					}
					
				}
		
		}
		return panes;
	}
	
}
