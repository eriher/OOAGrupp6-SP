/**
 * Write a description of class Schedule here.
 * 
 * @author Simon Planhage
 * @version 2014-02-27
 */

package model.schedule;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Serializable {
	
	

	private static final long serialVersionUID = -4841663954805333902L;
	
	//Variabler här har inga modifiers (private etc.) så att de kan anropas från samma paket utan att behöva skicka
	//getters och setters-metoder i varje schedule objekt till servern.
	
	ArrayList<Week> weekList;
	int personNummer;

	public Schedule() {
		weekList = new ArrayList<Week>();
	}
}
