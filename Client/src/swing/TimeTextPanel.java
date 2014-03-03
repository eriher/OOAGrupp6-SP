/**
 * Custom panel consisting of two integer fields.
 * 
 * @author David Stromner
 * @version 2014-03-03
 */

package swing;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class TimeTextPanel extends JPanel {
	private static final long serialVersionUID = -7774067380432105202L;
	private IntegerField hour, minute;

	public TimeTextPanel(String text) {
		super();
		
		String[] temp = text.split(":");
		
		hour = new IntegerField(temp[0]);
		minute = new IntegerField(temp[1]);
		
		add(hour);
		add(minute);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
	}

	public IntegerField getHour() {
		return hour;
	}

	public IntegerField getMinute(){
		return minute;
	}
	
	public String getTime(){
		return hour.getText()+":"+minute.getText();
	}
}
