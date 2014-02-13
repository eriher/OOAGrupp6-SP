/**
 * Implements the DocumentListner interface. setMethod must be called before usage.
 * 
 * @author David Stromner
 * @version 2013-02-12
 */

package controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentListenerHandler implements DocumentListener {
	private Object methodObject;
	private Method method;

	public DocumentListenerHandler() {

	}

	/**
	 * Set the method that DocumentListenerHandler should evoke when provoked.
	 * 
	 * @param o
	 *            The method's class name.
	 * @param methodName
	 *            name of the method.
	 */
	public void setMethod(Object o, String methodName) {
		try {
			methodObject = o;
			method = o.getClass().getMethod(methodName, DocumentEvent.class);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param e
	 *            To be passed onto the called method.
	 */
	private void callMethod(DocumentEvent e) {
		try {
			method.invoke(methodObject, e);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		callMethod(e);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		callMethod(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		callMethod(e);
	}
}
