/**
 * Write a description of class Schedule here.
 * 
 * @author Simon Planhage
 * @version 2014-02-25
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Serializable {

	private static final long serialVersionUID = -4841663954805333902L;
	private ArrayList<Week> weekList;
	private int personNummer;

	public Schedule() {
		weekList = new ArrayList<Week>();
	}
}
