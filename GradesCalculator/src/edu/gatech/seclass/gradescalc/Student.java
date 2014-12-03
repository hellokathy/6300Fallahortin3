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
	public int getAverageAssignmentsGrade() {
		return (int)(record.getAverageAssignmentsGrade(this)+.5);
	}

	public int getOverallGrade() {
		// TODO Auto-generated method stub
		return record.getOverallGrade(this);
	}

	public int getAverageProjectsGrade() {
		return (int) (record.getAverageProjectsGrade(this)+.5);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gtID == null) ? 0 : gtID.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (gtID == null) {
			if (other.gtID != null)
				return false;
		} else if (!gtID.equals(other.gtID))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
