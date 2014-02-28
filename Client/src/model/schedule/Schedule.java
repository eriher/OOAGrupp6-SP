/**
 * Write a description of class Schedule here.
 * 
 * @author Simon Planhage
 * @version 2014-02-25
 */

package model.schedule;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Serializable {

	private static final long serialVersionUID = -4841663954805333902L;
	
	//Variabler h�r har inga modifiers (private etc.) s� att de kan anropas fr�n samma paket utan att beh�va skicka
	//getters och setters-metoder i varje day objekt till servern.
	ArrayList<Week> weekList;
	int personNummer;

	public Schedule() {
		weekList = new ArrayList<Week>();
	}
}
