/**
 * Write a description of class GUI here.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-07
 */

package view;

import java.awt.Container;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JPanel;

public abstract class GUI {
	protected JPanel canvas; // Every component MUST be connected to the
	// canvas.
	protected HashMap<String, Container> components;

	public GUI() {
		canvas = new JPanel();
		components = new HashMap<String, Container>();
		initButtons();
		buildGUI();
	}

	public JPanel getCanvas() {
		return canvas;
	}
	
	// Place all components
	protected void buildGUI() {
		// Add all
		Iterator it = components.keySet().iterator();
		while (it.hasNext()) {
			canvas.add(components.get(it.next()));
		}
	}

	protected abstract void initButtons();
}