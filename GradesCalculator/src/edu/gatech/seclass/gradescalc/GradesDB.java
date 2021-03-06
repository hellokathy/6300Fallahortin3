package edu.gatech.seclass.gradescalc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

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
	XSSFSheet sheetInfo;
	InputStream inp = null;
	XSSFWorkbook wb = null;
	FileOutputStream fileOut;
	String dbName;
	XSSFSheet sheetTeams;
	String formula;


//	public GradesDB(String dbName)  { //left for compatibility 
//		this.loadSpreadsheet(dbName);
//		this.formula = "AT * 0.2 + AS * .4 + PR * .4";
//	}

	public GradesDB() {
		// TODO Auto-generated constructor stub
		this.formula = "AT * 0.2 + AS * .4 + PR * .4";
	}
	public void loadSpreadsheet(String gradesDb) {
		this.dbName = gradesDb;
		try {
			inp = new FileInputStream(gradesDb);
			wb = new XSSFWorkbook(inp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.sheetSoloGrades = wb.getSheet("IndividualGrades"); //Student Assignments
		this.sheetContribs = wb.getSheet("IndividualContribs");
		this.sheetTeamGrades = wb.getSheet("TeamGrades");
		this.sheetAttendance = wb.getSheet("Attendance");
		this.sheetTeams = wb.getSheet("Teams");
		this.sheetInfo = wb.getSheet("StudentsInfo"); //Student Info

		//loading student info
		XSSFSheet sheetInfo = wb.getSheet("StudentsInfo"); //Student Info
		for (int rowNum = 1; rowNum < sheetInfo.getLastRowNum()+1; rowNum++){
			Row rInfo = sheetInfo.getRow(rowNum);
			Row rAttendance = sheetAttendance.getRow(rowNum);
			Student s = new Student(
					rInfo.getCell(0).getStringCellValue(), //name
					String.valueOf((int) rInfo.getCell(1).getNumericCellValue()), //gtID
					this
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

	private void addGradeForAssignment(String assignmentName, String studentName, int grade, XSSFSheet sheet ){
		Row header = sheet.getRow(0);
		int i = 0;
		while (!header.getCell(i).getStringCellValue().equals(assignmentName)){
			i++;
			//TODO 
			//needs to try catch here for references
		}		
		Row student = this.seekRowByString(sheet, studentName);
		Cell cell = student.createCell(i);
		cell.setCellValue(grade);
		saveBook();
	}

	public void addGradesForAssignment(String assignmentName, HashMap<Student, Integer> grades) {
		for(HashMap.Entry<Student, Integer> s : grades.entrySet()){
			String name = s.getKey().getName();	
			int grade = s.getValue();
			this.addGradeForAssignment(assignmentName, name, grade, this.sheetSoloGrades);
		}

	}

	public Row seekRowByString(XSSFSheet sheet,String name){
		for (int rowNum = 1; rowNum < sheet.getLastRowNum()+1; rowNum++){
			Row row = sheet.getRow(rowNum);
			if (row.getCell(0).getStringCellValue().compareTo(name)==0){ //checking to see if the row equals the name
				return row;
			}
		}
		return null;
	}

	public double getAverageAssignmentsGrade(Student student) {
		Row row = this.seekRowByString(this.sheetSoloGrades, student.getName());
		int totalGrade = 0;
		for (int colNum = 1; colNum <= this.getNumAssignments();colNum++){
			totalGrade += row.getCell(colNum).getNumericCellValue();
		}
		return totalGrade/this.getNumAssignments();
	}

	private String seekTeam(String studentName){
		for (int rowNum = 1; rowNum < this.sheetTeams.getLastRowNum(); rowNum++){
			Row row = this.sheetTeams.getRow(rowNum);
			int i = 0;
			while (i < row.getLastCellNum()){
				if (row.getCell(i).getStringCellValue().equals(studentName)){
					return row.getCell(0).getStringCellValue();//this is the team name 
				}
				i++;
			}
		}
		return null;
	}

	public double getAverageProjectsGrade(Student student) {
		Row row = this.seekRowByString(this.sheetContribs, student.getName());
		String teamName = this.seekTeam(student.getName()); //seeks the team the student is one
		Row rowTeam = this.seekRowByString(this.sheetTeamGrades, teamName);
		double totalGrade = 0;
		int totalProjects = 0;
		for (int colNum = 1; colNum <= this.getNumProjects();colNum++){
			try {
				totalGrade += (((row.getCell(colNum).getNumericCellValue() * rowTeam.getCell(colNum).getNumericCellValue())/100));
				totalProjects++;
			} catch(NullPointerException e) {
				System.out.println("Caught NullPointerException: " + e);
				totalProjects--;
			}
		}
		return (totalGrade/totalProjects);
	}

	public void addIndividualContributions(String projectName, HashMap<Student, Integer> contributions) {
		for(HashMap.Entry<Student, Integer> s : contributions.entrySet()){
			String name = s.getKey().getName();	
			int grade = s.getValue();
			this.addGradeForAssignment(projectName, name, grade, this.sheetContribs);
		}	
	}
	public String getStudentEmail(Student student) {
		Row row = this.seekRowByString(this.sheetInfo, student.getName());
		return row.getCell(2).getStringCellValue(); //email;
	}
	public int getStudentAttendance(Student student) {
		Row row = this.seekRowByString(this.sheetAttendance, student.getName());
		return (int)(row.getCell(1).getNumericCellValue()+.5); //attednace
	}

	public void addStudent(Student student1) {
		// TODO Auto-generated method stub

	}

	public void addProject(String string) {
		// TODO Auto-generated method stub

	}

	public void addGradesForProject(String teamName, String projectName, int grade) {
		// TODO Auto-generated method stub

	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getFormula() {
		return this.formula;
	}
	public int getOverallGrade(Student s) throws GradeFormulaException {
		try {
			ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");  
			Bindings bindings = engine.createBindings();  
			bindings.put("AT", s.getAttendance());//attendance
			bindings.put("AS", s.getAverageAssignmentsGrade());//assignments 
			bindings.put("PR", s.getAverageProjectsGrade());//projects
			engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);  
			Object result = engine.eval(this.formula);  // dangerous!
			double val = new BigDecimal(result.toString()).doubleValue();
			return (int)(val+.5);
		} catch (Exception e){
			throw new GradeFormulaException("misformed formula!");
		}
		
	}
}
