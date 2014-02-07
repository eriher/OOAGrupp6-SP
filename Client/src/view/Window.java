/**
 * Write a description of class Window here.
 * 
 * @author David Stromner
 * @version 2013-02-06
 */

package view;

import java.util.HashMap;

import javax.swing.JFrame;

public class Window {
<<<<<<< HEAD
	private HashMap<String, GUI> interfaceList;
	private JFrame frame;

	public Window() {
		interfaceList = new HashMap<String, GUI>();
=======
	private HashMap<String,GUI> interfaceList;
	private JFrame frame;
	
	public Window(){
		interfaceList = new HashMap<String,GUI>();
>>>>>>> c3d93de206165536386cf1ded63d4c0f90aa0733
		frame = new JFrame();
		// Init all the views
		initViews();
	}
<<<<<<< HEAD

	private void initViews() {
		interfaceList.put("Login", new LoginGUI());
		interfaceList.put("Admin", new AdminGUI());
		interfaceList.put("Employee", new EmployeeGUI());
	}

	public void SetView(String key) {
=======
	
	private void initViews(){
		interfaceList.put("Login", new LoginGUI());
		interfaceList.put("Admin", new AdminGUI());
		interfaceList.put("Employee", new EmployeeGUI());	
	}
	
	public void SetView(String key){
>>>>>>> c3d93de206165536386cf1ded63d4c0f90aa0733
		frame.add(interfaceList.get(key));
	}
}
