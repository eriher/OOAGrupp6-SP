package model.schedule;

import java.io.Serializable;
import java.util.ArrayList;

public class Year implements Serializable {

	private static final long serialVersionUID = 352635685812722718L;
	
	ArrayList<Week> weekList;
	
	public Year() {
		weekList = new ArrayList<Week>();
	}

}
