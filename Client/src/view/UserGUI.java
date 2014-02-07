/**
 * Write a description of class UserGUI here.
 * 
 * @author David Stromner
 * @version 2013-02-06
 */

package view;

<<<<<<< HEAD
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class UserGUI extends GUI {	
	public UserGUI(){
		super();
	}
	
	// Init logout
	protected void initButtons(){
		components.put("logOut", new JButton());
	}
	
	// Init top,menu panel
	protected void initPanels(){
		components.put("topPanel", new JPanel());
		components.put("menuPanel", new JPanel());
	}
	
	// Modify & add logout, top, menu
	protected void buildGUI(){
		
	}
=======
public abstract class UserGUI extends GUI{

>>>>>>> c3d93de206165536386cf1ded63d4c0f90aa0733
}
