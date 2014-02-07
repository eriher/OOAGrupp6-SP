/**
 * Write a description of class GUI here.
 * 
 * @author David Stromner
 * @version 2013-02-06
 */

package view;

<<<<<<< HEAD
import java.awt.Container;
import java.util.HashMap;

public abstract class GUI /*implements JContainer*/ {
	protected HashMap<String, Container> components;
	
	public GUI(){
		components = new HashMap<String, Container>();
		initButtons();
		buildGUI();
	}
	
	protected abstract void initButtons();
	protected abstract void buildGUI();
}
=======
public abstract class GUI {

}
>>>>>>> c3d93de206165536386cf1ded63d4c0f90aa0733
