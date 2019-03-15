package com.gojek.model;

import java.util.Comparator;

public class SortBySlotNo implements Comparator<Registration>{

	@Override
	public int compare(Registration a, Registration b) {
		// TODO Auto-generated method stub
		return a.getSlotNo() - b.getSlotNo();
	}

}
