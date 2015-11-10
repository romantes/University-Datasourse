package main.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import com.mentat.OOP.task6.*;

import org.junit.*;


public class UniversityTest {
	@BeforeClass
	public static void setUp() throws Exception {
		University.addRoom(new Room("123c"));
		University.addRoom(new Room("220v"));
		University.addRoom(new Room("330g"));
		
		
		University.addSubject(new Subject("CS"));
		University.addSubject(new Subject("Algebra"));
		University.addSubject(new Subject("Philosophy"));
		
		University.addProfessor(new Professor(12345, "Ivanov", "Ivan"));
		University.addProfessor(new Professor(666, "Gates", "Bill"));
		University.addProfessor(new Professor(777, "Martens", "Dr."));
		
		University.addGroup(new Group("210/q"));
		University.addGroup(new Group("110/r"));
		
		University.getGroupByNumber("210/q").addStudent(new Student(999, "Egor", "Egorov"));
		University.getGroupByNumber("210/q").addStudent(new Student(888, "Sidor", "Sidorov"));
		University.getGroupByNumber("210/q").addStudent(new Student(444, "Alexandr", "Alexandrov"));
		
	}

	@Test
	public void universityRoomObjectEquals() throws RoomExistsException {
		Assert.assertEquals(new Room("123c"),
				University.getRoomByNumber("123c"));
		Assert.assertNull(University.getRoomByNumber("123"));
	}

	@Test(expected = RoomExistsException.class)
	public void universityDublicatedAddRoom() throws RoomExistsException {
		University.addRoom(new Room("123c"));
	}

	@Test
	public void universitySubjectObjectEquals() {
		Assert.assertEquals(new Subject("CS"), University.getSubjectByTitle("CS"));
		Assert.assertNull(University.getSubjectByTitle("Mathematic"));
	}

	@Test(expected = SubjectExistsException.class) 
	public void iniversityDublicateAddSubject() throws SubjectExistsException {	
		University.addSubject(new Subject("CS"));
	}
	
	@Test
	public void universityProfessorObjectEquals() {
		Assert.assertEquals(new Professor(12345, "Ivanov", "Ivan"), 
				University.getProfessorById(12345));
		Assert.assertNull(University.getProfessorById(123));
	}
	
	@Test(expected = PersonExistsException.class)
	public void universityDublicateAddProfessor() throws PersonExistsException {
		University.addProfessor(new Professor(12345, "Ivanov", "Ivan"));
	}
	
	@Test
	public void universityGroupObjectEquals() throws GroupExistsException {
		Assert.assertEquals(new Group("210/q"), University.getGroupByNumber("210/q"));
		Assert.assertNull(University.getGroupByNumber("210"));
	}
	
	@Test(expected = GroupExistsException.class)
	public void universityDublicateAddGroup() throws GroupExistsException {
		University.addGroup(new Group("210/q"));
	}

	@Test
	public void lectureCreate()  {
		Date date = new Date(2015, Calendar.NOVEMBER, 6, 15, 30);
		Lecture lecture = new Lecture().createLecture(date, "123c", "CS", (long)12345, "210/q");	
		Assert.assertEquals(University.getRoomByNumber("123c"), lecture.getRoom());
		Assert.assertEquals(University.getSubjectByTitle("CS"), lecture.getSubject());
		Assert.assertEquals(University.getProfessorById(12345), lecture.getProfessor());
		Assert.assertEquals(University.getGroupByNumber("210/q"), lecture.getGroup());		
	}	

	@Test
	public void ScheduleBoardgetPersonByIdProfessor() {
		ScheduleBoard sb = new ScheduleBoard();
		Person actual = sb.getPersonById((long)12345);
		Person expected = new Professor(12345, "Ivanov", "Ivan");
		Assert.assertEquals(expected, actual);
	}

	@Test 
	public void ScheduleBoardgetPersonByIdStudent() throws PersonExistsException {
		ScheduleBoard sb = new ScheduleBoard();
		Person actual = sb.getPersonById((long)444);
		Person expected = new Student(444, "Alexandr", "Alexandrov");
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void ScheduleBoardgetPersonByIdNull() throws PersonExistsException {
		ScheduleBoard sb = new ScheduleBoard();
		Assert.assertNull(sb.getPersonById((long)987));
	}
	
	@Test
	public void ScheduleBoardExtractLecturesByPersonIdAndDateStudent() throws LectureExistsException {
		ScheduleBoard sb = new ScheduleBoard();
		
		Date l1date = new Date(2015, 4, 3, 15, 30);
		Date l2date = new Date(2015, 4, 3, 18, 30);
		Date l3date = new Date(2015, 4, 3, 9, 00);
		
		Long studentId = (long)888;
		
		Lecture l1 = new Lecture().createLecture(l1date, "123c", "Algebra", 666, "210/q");
		Lecture l2 = new Lecture().createLecture(l2date, "220v", "CS", 777, "210/q");
		Lecture l3 = new Lecture().createLecture(l3date, "220v", "CS", 12345, "210/q");
		
		sb.addLecture(l1);
		sb.addLecture(l2);
		sb.addLecture(l3);
		
		List<Lecture> expected = new ArrayList<Lecture>();
		expected.add(l1);
		expected.add(l2);
		expected.add(l3);
		
		List<Lecture> actual = sb.extractLecturesByPersonIdAndDate(l1date, studentId);
		
		Assert.assertEquals(expected, actual);	
	}
	
	@Test
	public void ScheduleBoardExtractLecturesByPersonIdAndDateProfessor() throws LectureExistsException {
		ScheduleBoard sb = new ScheduleBoard();
		
		Date l1date = new Date(2015, 4, 3, 15, 30);
		Date l2date = new Date(2015, 4, 3, 18, 30);
		Date l3date = new Date(2015, 4, 3, 9, 00);
		
		Long professorId = (long)666;
		
		Lecture l1 = new Lecture().createLecture(l1date, "123c", "Algebra", 666, "210/q");
		Lecture l2 = new Lecture().createLecture(l2date, "220v", "CS", 777, "210/q");
		Lecture l3 = new Lecture().createLecture(l3date, "220v", "CS", 12345, "210/q");
		Lecture l4 = new Lecture().createLecture(l3date, "123c", "Algebra", 666, "110/r");
		
		sb.addLecture(l1);
		sb.addLecture(l2);
		sb.addLecture(l3);
		sb.addLecture(l4);
		
		List<Lecture> expected = new ArrayList<Lecture>();
		expected.add(l1);
		expected.add(l4);
		List<Lecture> actual = sb.extractLecturesByPersonIdAndDate(l1date, professorId);
		Assert.assertEquals(expected, actual);	
	}
}