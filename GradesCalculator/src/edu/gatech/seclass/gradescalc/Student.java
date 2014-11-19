package edu.gatech.seclass.gradescalc;

public class Student {
	String name;
	int gtID;
	//String email;
	int attendance;
	public Student(String name, String id, GradesDB db){ //added the new method for deliverable 2
		this.name = name;
		Student s = db.getStudentByID(id);
		//this.gtID = Integer.getInteger(s.getGtid());
		this.attendance = s.attendance;
	};
	
	public Student(String name, int id, int attendance) {
		this.name = name;
		this.gtID = id;
		this.attendance = Integer.valueOf(attendance);
	}

	public String getName() {
		return this.name;
	}

	public String getGtid() {
		return String.valueOf(this.gtID);
	}

	public Object getAttendance() {
		return this.attendance;
	}

}
