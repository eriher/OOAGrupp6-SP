/**
 * All unique swing components for the GUI.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-13
 */

package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.HashMap;

import javax.swing.JPanel;

public abstract class GUI {
	private JPanel canvas; // Every component MUST be connected to the
	// canvas.
	protected HashMap<String, Container> components;

	public GUI() {
		canvas = new JPanel();
		canvas.setLayout(new BorderLayout());
		components = new HashMap<String, Container>();
		initLabels();
		initButtons();
		initPanels();
		initTextFields();
		buildGUI();
	}

	/**
	 * @return canvas Contains all objects to be displayed in a single panel.
	 */
	public JPanel getCanvas() {
		return canvas;
	}

	/**
	 * Create all labels
	 */
	protected void initLabels() {}

	/**
	 * Create all buttons
	 */
	protected void initButtons() {}

	/**
	 * Create all panels
	 */
	protected void initPanels() {}

	/**
	 * Create all text fields.
	 */
	protected void initTextFields() {}

	/**
	 * Place all components
	 */
	protected void buildGUI() {
		canvas.setLayout(new BorderLayout());
	}
}