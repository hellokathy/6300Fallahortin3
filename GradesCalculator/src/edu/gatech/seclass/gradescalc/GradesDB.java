package edu.gatech.seclass.gradescalc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GradesDB {
	HashSet<Student> students = new HashSet<Student>();
	XSSFSheet sheetTeamGrades;
	XSSFSheet sheetSoloGrades;
	XSSFSheet sheetAttendance;
	XSSFSheet sheetProjects;
	XSSFSheet sheetContribs;
	InputStream inp = null;
	XSSFWorkbook wb = null;
	FileOutputStream fileOut;
	String dbName;
	
	
	public GradesDB(String dbName)  {
		this.dbName = dbName;
		try {
			inp = new FileInputStream(dbName);
			wb = new XSSFWorkbook(inp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.sheetSoloGrades = wb.getSheet("IndividualGrades"); //Student Assignments
		this.sheetTeamGrades = wb.getSheet("TeamGrades");
		this.sheetAttendance = wb.getSheet("Attendance");
				
		//loading student info
		XSSFSheet sheetInfo = wb.getSheet("StudentsInfo"); //Student Info
		for (int rowNum = 1; rowNum < sheetInfo.getLastRowNum()+1; rowNum++){
			Row rInfo = sheetInfo.getRow(rowNum);
			Row rAttendance = sheetAttendance.getRow(rowNum);
			Student s = new Student(
					rInfo.getCell(0).getStringCellValue(), //name
					(int) rInfo.getCell(1).getNumericCellValue(), //gtID
					(int) rAttendance.getCell(1).getNumericCellValue()//attendance
					);
			this.students.add(s);
		}
		
	}

	public int getNumStudents() {
		return this.students.size();
	}

	public int getNumAssignments() {
		Row header = this.sheetSoloGrades.getRow(0);
		return header.getLastCellNum()-1; //subtracts name
	}

	public int getNumProjects() {
		Row header = this.sheetSoloGrades.getRow(0);
		return header.getLastCellNum()-1; //subtracts name
	}

	public HashSet<Student> getStudents() {
		return this.students;
	}

	public Student getStudentByName(String string) {
		for (Student s : students) {
			if (s.getName().compareTo(string) == 0) {
				return s;
			}
		}
		return null;
	}

	public Student getStudentByID(String string) {
		for (Student s : students) {
			if (s.getGtid().compareTo(string) == 0) {
				return s;
			}
		}
		return null;
	}
	
	private void saveBook(){
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(this.dbName);
			this.wb.write(fileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addAssignment(String string) {
		Row header = this.sheetSoloGrades.getRow(0);
		int num_assignments = header.getLastCellNum(); //subtracts name
		Cell cell = header.createCell(num_assignments);
		cell.setCellValue(string);
		saveBook();
	}

	public void addGradesForAssignment(String assignmentName,
			HashMap<Student, Integer> grades) {
		// TODO Auto-generated method stub
		
	}
	
	private Row seekStudent(XSSFSheet sheet,String name){
		for (int rowNum = 1; rowNum < sheet.getLastRowNum()+1; rowNum++){
			Row row = sheet.getRow(rowNum);
			if (row.getCell(0).getStringCellValue().compareTo(name)==0){ //checking to see if the row equals the name
				return row;
			}
		}
		return null;
	}
	
	public double getAverageAssignmentsGrade(Student student) {
		Row row = this.seekStudent(this.sheetSoloGrades, student.getName());
		int totalGrade = 0;
		for (int colNum = 1; colNum <= this.getNumAssignments();colNum++){
			totalGrade += row.getCell(colNum).getNumericCellValue();
		}
		return totalGrade/this.getNumAssignments();
	}

	public double getAverageProjectsGrade(Student student) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addIndividualContributions(String projectName1,
			HashMap<Student, Integer> contributions1) {
		// TODO Auto-generated method stub
		
	}

}
