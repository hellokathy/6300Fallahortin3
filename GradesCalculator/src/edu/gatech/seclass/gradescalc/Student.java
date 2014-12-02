package edu.gatech.seclass.gradescalc;

public class Student {
	String name;
	String gtID;
	GradesDB record = null;
	String attendance;
	//String email;
	
	public Student(String name, String id, GradesDB db){ //added the new method for deliverable 2
		this.name = name;
		this.gtID = id;
		this.record = db;
	};
	
	public String getName() {
		return this.name;
	}

	public String getGtid() {
		return this.gtID;
	}
	
	public int getAttendance() {
		return record.getStudentAttendance(this);
	}
	
	public String getEmail() {
		// TODO Auto-generated method stub
		return record.getStudentEmail(this);
	}
	public double getAverageAssignmentsGrade() {
		return record.getAverageProjectsGrade(this);
	}

	public String getOverallGrade() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getAverageProjectsGrade() {
		return record.getAverageProjectsGrade(this);
	}

}
