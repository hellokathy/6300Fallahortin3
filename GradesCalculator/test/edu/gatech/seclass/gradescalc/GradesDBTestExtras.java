//package edu.gatech.seclass.gradescalc;
//
//import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.io.File;
//import java.nio.file.FileSystem;
//import java.nio.file.FileSystems;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.HashMap;
//import java.util.HashSet;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class GradesDBTestExtras {
//
//	GradesDB db = null;
//	static final String GRADES_DB_GOLDEN = "DB" + File.separator
//			+ "GradesDatabase6300-goldenversion.xlsx";
//	static final String GRADES_DB = "DB" + File.separator
//			+ "GradesDatabase6300.xlsx";
//
//	@Before
//	public void setUp() throws Exception {
//		FileSystem fs = FileSystems.getDefault();
//		Path dbfilegolden = fs.getPath(GRADES_DB_GOLDEN);
//		Path dbfile = fs.getPath(GRADES_DB);
//		Files.copy(dbfilegolden, dbfile, REPLACE_EXISTING);
//		db = new GradesDB(GRADES_DB);
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		db = null;
//	}
//
//	@Test
//	public void testGetNumStudents() {
//		int numStudents = db.getNumStudents();
//		assertEquals(14, numStudents);
//	}
//
//	@Test
//	public void testGetNumAssignments() {
//		int numAssignments = db.getNumAssignments();
//		assertEquals(3, numAssignments);
//	}
//
//	@Test
//	public void testGetNumProjects() {
//		int numProjects;
//		numProjects = db.getNumProjects();
//		assertEquals(3, numProjects);
//	}
//
//	@Test
//	public void testGetStudents1() {
//		HashSet<Student> students = null;
//		students = db.getStudents();
//		assertEquals(14, students.size());
//	}
//
//	@Test
//	public void testGetStudents2() {
//		HashSet<Student> students = null;
//		students = db.getStudents();
//		boolean found = false;
//		for (Student s : students) {
//			if (s.getName().compareTo("Cynthia Faast") == 0
//					&& s.getGtid().compareTo("901234514") == 0) {
//				found = true;
//				break;
//			}
//		}
//		assertTrue(found);
//	}
//
//	@Test
//	public void testGetStudentsByName1() {
//		Student student = null;
//		student = db.getStudentByName("Rastus Kight");
//		assertTrue(student.getGtid().compareTo("901234512") == 0);
//	}
//
//	@Test
//	public void testGetStudentsByName2() {
//		Student student = null;
//		student = db.getStudentByName("Grier Nehling");
//		assertEquals(96, student.getAttendance());
//	}
//
//	@Test
//	public void testGetStudentsByID() {
//		Student student = db.getStudentByID("901234504");
//		assertTrue(student.getName().compareTo("Shevon Wise") == 0);
//	}
//
//	// New tests
//	@Test
//	public void testAddAssignment() {
//		db.addAssignment("ASSIGNMENT: black-box testing");
//		db = new GradesDB(GRADES_DB);
//		assertEquals(4, db.getNumAssignments());
//		db.addAssignment("ASSIGNMENT: white-box testing");
//		db = new GradesDB(GRADES_DB);
//		assertEquals(5, db.getNumAssignments());
//	}
//
//	@Test
//	public void testAddGradesForAssignment() {
//		String assignmentName = "ASSIGNMENT: category partition";
//		Student student1 = new Student("Josepha Jube", "901234502", db);
//		Student student2 = new Student("Christine Schaeffer", "901234508", db);
//		Student student3 = new Student("Ernesta Anderson", "901234510", db);
//		db.addAssignment(assignmentName);
//		db = new GradesDB(GRADES_DB);
//		HashMap<Student, Integer> grades = new HashMap<Student, Integer>();
//		grades.put(student1, 87);
//		grades.put(student2, 94);
//		grades.put(student3, 100);
//		db.addGradesForAssignment(assignmentName, grades);
//		db = new GradesDB(GRADES_DB);
//		assertEquals(90, db.getAverageAssignmentsGrade(student1), 1);
//		assertEquals(94, db.getAverageAssignmentsGrade(student2), 1);
//		assertEquals(93, db.getAverageAssignmentsGrade(student3), 1);
//	}
//
//	@Test
//	public void testGetAverageAssignmentsGrade() {
//		// Rounded to the closest integer
//		Student student = new Student("Ernesta Anderson", "901234510", db);
//		assertEquals(90, db.getAverageAssignmentsGrade(student), 1);
//	}
//
//	@Test
//	public void testGetAverageProjectsGrade() {
//		// Rounded to the closest integer
//		Student student = new Student("Rastus Kight", "901234512", db);
//		assertEquals(86, db.getAverageProjectsGrade(student), 1);
//	}
//
//	@Test
//	public void testAddIndividualContributions() {
//		String projectName1 = "PROJECT 2";
//		Student student1 = new Student("Josepha Jube", "901234502", db);
//		Student student2 = new Student("Grier Nehling", "901234503", db);
//		HashMap<Student, Integer> contributions1 = new HashMap<Student, Integer>();
//		contributions1.put(student1, 96);
//		contributions1.put(student2, 87);
//		db.addIndividualContributions(projectName1, contributions1);
//		db = new GradesDB(GRADES_DB);
//		String projectName2 = "PROJECT 3";
//		HashMap<Student, Integer> contributions2 = new HashMap<Student, Integer>();
//		contributions2.put(student1, 98);
//		contributions2.put(student2, 100);
//		db.addIndividualContributions(projectName2, contributions2);
//		assertEquals(90, db.getAverageProjectsGrade(student1), 1);
//		assertEquals(84, db.getAverageProjectsGrade(student2), 1);
//	}
//	// //////////////////////////////
//	// NO CHANGES ABOVE THIS LINE //
//	// //////////////////////////////
//
//
//	@Test
//	public void testAddStudent(){
//		Student student = new Student("Alex Hortin", "1337",db);
//		db.addStudent(student);
//		student = null;
//		student = db.getStudentByName("Alex Hortin");
//		assertEquals("1337", student.getGtid());
//		Student student1 = new Student("Bob Loblaw", "7331",db);
//		db.addStudent(student1);
//		student1 = null;
//		student1 = db.getStudentByName("Bob Loblaw");
//		assertEquals("7331", student1.getGtid());
//	}
//
//	@Test
//	public void testAddProject(){
//		db.addProject("PROJECT: Manhattan");
//		db = new GradesDB(GRADES_DB);
//		assertEquals(4, db.getNumProjects());
//	}
//
//	@Test
//	public void testAddProjectGrade(){
//		String projectName = "PROJECT: Manhattan";
//		Student student1 = new Student("Caileigh Raybould", "901234506", db); // contrib 50	90	89 (96)/ team3 90 86	102 (100) 
//		Student student2 = new Student("Kym Hiles", "901234507", db); // contrib 93	99	92 (100) / team1 93	95	100 (25)
//		HashMap<Student, Integer> contributions1 = new HashMap<Student, Integer>();
//		contributions1.put(student1, 96);
//		contributions1.put(student2, 100);
//		db.addIndividualContributions(projectName, contributions1);
//		
//		db.addGradesForProject("Team 3", "Project: Manhattan", 100);
//		db.addGradesForProject("Team 1", "Project: Manhattan", 25);
//		db = new GradesDB(GRADES_DB);
//		assertEquals(77, db.getAverageAssignmentsGrade(student1), 1); //((50*90) + (90*86) + (102*89)+(96*100))/100/4
//		assertEquals(74, db.getAverageAssignmentsGrade(student2), 1); //((93*93) + (99*95) + (92*100) + (100*25))/100/4
//	}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
