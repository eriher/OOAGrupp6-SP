/**
 * Represents one week with seven days
 * 
 * @author Simon Planhage
 * @version 2014-02-27
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Week implements Serializable {


	private static final long serialVersionUID = -8613882887180242145L;

	//Variabler här har inga modifiers (private etc.) så att de kan anropas från samma paket utan att behöva skicka
	//getters och setters-metoder i varje week objekt till servern.

	//Vecka som schemat representerar
	int weekNr;
	
	//Lista med veckodagarna som hör till
	ArrayList<Day> days = new ArrayList<Day>();

}