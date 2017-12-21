package org.eclipse.wtp.sample.classschedule;

import java.util.ArrayList;
import java.util.List;

public class SchoolSchedule {

	private List classes = new ArrayList();

	public List getClasses() {
		return classes;
	}
	
	public void addClass(SchoolClass schoolClass) {
		classes.add(schoolClass);
	}
}
