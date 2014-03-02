/**
 * Custom generic JComboBox with built in regex.
 * 
 * @author David Stromner
 * @version 2013-03-02
 */
package swing;

import java.util.LinkedList;

import javax.swing.JComboBox;

public class RegexJComboBox<T> extends JComboBox<T>{
	private static final long serialVersionUID = -2675712450023192575L;
	private LinkedList<T> items;
	
	/**
	 * @param args items to be added to the combobox
	 */
	public RegexJComboBox(){
		super();
		
		items = new LinkedList<T>();
	}
	
	@Override
	public void addItem(T item){
		super.addItem(item);
		items.add(item);
	}
	
	/**
	 * Filter the combo box with the regex fed into the param.
	 * 
	 * @param filter to build a regex from.
	 */
	public void filter(String filter){
		int size = items.size();
		super.removeAllItems();
		
		String regex = ".*?" + filter + ".*?";
		
		for(int i=0;i<size;i++){
			// Filter
			if(items.get(i).toString().matches(regex)){
				// Re-add if regex matched.
				super.addItem(items.get(i));
			}
		}
	}
}
