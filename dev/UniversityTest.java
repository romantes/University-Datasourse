package main.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import com.mentat.OOP.task6.*;
import com.mentat.OOP.task6.Exceptions.*;

import org.junit.*;

public class UniversityTest {
	@BeforeClass
	public static void setUp() throws Exception {
		Room room1 = new Room("123c");
		Room room2 = new Room("220v");
		Room room3 = new Room("330g");

		University.addRoom(room1);
		University.addRoom(room2);
		University.addRoom(room3);

		University.addSubject(new Subject("CS"));
		University.addSubject(new Subject("Algebra"));
		University.addSubject(new Subject("Philosophy"));

		Group group1 = new Group("210/q");
		Group group2 = new Group("110/r");

		University.addGroup(group1);
		University.addGroup(group2);

		Professor professor1 = new Professor(12345, "Ivanov", "Ivan");
		Professor professor2 = new Professor(666, "Gates", "Bill");
		Professor professor3 = new Professor(777, "Martens", "Dr.");

		University.addPerson(professor1);
		University.addPerson(professor2);
		University.addPerson(professor3);

		Student student1 = new Student(999, "Egor", "Egorov");
		Student student2 = new Student(888, "Sidor", "Sidorov");
		Student student3 = new Student(444, "Alexandr", "Alexandrov");

		University.addPerson(student1);
		University.addPerson(student2);
		University.addPerson(student3);

		University.addStudentToGroup(group1, student1);
		University.addStudentToGroup(group1, student2);
		University.addStudentToGroup(group1, student3);

	}

	@Test
	public void universityRoomObjectEquals() throws RoomExistsException {
		Assert.assertEquals(new Room("123c"),
				University.getRoomByNumber("123c"));
	}

	@Test(expected = RoomExistsException.class)
	public void universityRoomObjectEx() throws RoomExistsException {
		University.addRoom(new Room("123c"));
	}

	@Test
	public void universitySubjectObjectEquals() throws SubjectExistsException {
		Assert.assertEquals(new Subject("CS"),
				University.getSubjectByTitle("CS"));
	}
	
	@Test(expected = SubjectExistsException.class)
	public void iniversityDublicateAddSubject() throws SubjectExistsException {
		University.addSubject(new Subject("CS"));
	}

	@Test
	public void universityProfessorObjectEquals() throws PersonExistsException {
		Assert.assertEquals(new Professor(12345, "Ivanov", "Ivan"),
				University.getPersonById(12345));
	}

	@Test(expected = PersonExistsException.class)
	public void universityDublicateAddProfessor() throws PersonExistsException {
		University.addPerson(new Professor(12345, "Ivanov", "Ivan"));
	}

	@Test
	public void universityGroupObjectEquals() throws GroupExistsException {
		Assert.assertEquals(new Group("210/q"),
				University.getGroupByNumber("210/q"));
	}

	@Test(expected = GroupExistsException.class)
	public void universityDublicateAddGroup() throws GroupExistsException {
		University.addGroup(new Group("210/q"));
	}

	@Test
	public void lectureCreate() throws UniversityObjectExistanseException {
		Date date = new Date(2015, Calendar.NOVEMBER, 6, 15, 30);
		Lecture lecture = new Lecture().createLecture(date, "123c", "CS",
				(long) 12345, "210/q");
		Assert.assertEquals(University.getRoomByNumber("123c"),
				lecture.getRoom());
		Assert.assertEquals(University.getSubjectByTitle("CS"),
				lecture.getSubject());
		Assert.assertEquals(University.getPersonById(12345),
				lecture.getProfessor());
		Assert.assertEquals(University.getGroupByNumber("210/q"),
				lecture.getGroup());
	}

	@Test
	public void UniversityPersonByIdProfessor() throws PersonExistsException {
		Person actual = University.getPersonById((long) 12345);
		Person expected = new Professor(12345, "Ivanov", "Ivan");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void UniversityPersonByIdStudent() throws PersonExistsException {
		Person actual = University.getPersonById((long) 444);
		Person expected = new Student(444, "Alexandr", "Alexandrov");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void ScheduleBoardExtractLecturesByPersonIdAndDateStudent()
			throws UniversityObjectExistanseException {
		ScheduleBoard sb = new ScheduleBoard();

		Date l1date = new Date(2015, 4, 3, 15, 30);
		Date l2date = new Date(2015, 4, 3, 18, 30);
		Date l3date = new Date(2015, 4, 3, 9, 00);

		Long studentId = (long) 888;

		Lecture l1 = new Lecture().createLecture(l1date, "123c", "Algebra",
				666, "210/q");
		Lecture l2 = new Lecture().createLecture(l2date, "220v", "CS", 777,
				"210/q");
		Lecture l3 = new Lecture().createLecture(l3date, "220v", "CS", 12345,
				"210/q");

		sb.addLecture(l1);
		sb.addLecture(l2);
		sb.addLecture(l3);

		List<Lecture> expected = new ArrayList<Lecture>();
		expected.add(l1);
		expected.add(l2);
		expected.add(l3);

		List<Lecture> actual = sb.extractLecturesByIdAndDate(l1date, studentId);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void scheduleBoardextractLecturesByDate()
			throws UniversityObjectExistanseException {
		Date l1date = new Date(2015, 4, 3, 15, 30);
		Date l2date = new Date(2015, 4, 3, 18, 30);
		Date l3date = new Date(2015, 4, 3, 9, 00);

		Lecture l1 = new Lecture().createLecture(l1date, "123c", "Algebra",
				666, "210/q");
		Lecture l2 = new Lecture().createLecture(l2date, "220v", "CS", 777,
				"210/q");
		Lecture l3 = new Lecture().createLecture(l3date, "220v", "CS", 12345,
				"210/q");

		ArrayList<Lecture> expected = new ArrayList<>();
		expected.add(l1);
		expected.add(l2);
		expected.add(l3);

		ScheduleBoard sb = new ScheduleBoard();
		List<Lecture> actual = sb.extractLecturesByDate(expected, l1date);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void scheduleBoardExtractLecturesByPersonIdAndDateProfessor()
			throws UniversityObjectExistanseException {
		ScheduleBoard sb = new ScheduleBoard();

		Date l1date = new Date(2015, 4, 3, 15, 30);
		Date l2date = new Date(2015, 4, 3, 18, 30);
		Date l3date = new Date(2015, 4, 3, 9, 00);

		Long professorId = (long) 666;

		Lecture l1 = new Lecture().createLecture(l1date, "123c", "Algebra",
				666, "210/q");
		Lecture l2 = new Lecture().createLecture(l2date, "220v", "CS", 777,
				"210/q");
		Lecture l3 = new Lecture().createLecture(l3date, "220v", "CS", 12345,
				"210/q");
		Lecture l4 = new Lecture().createLecture(l3date, "123c", "Algebra",
				666, "110/r");

		sb.addLecture(l1);
		sb.addLecture(l2);
		sb.addLecture(l3);
		sb.addLecture(l4);

		List<Lecture> expected = new ArrayList<Lecture>();
		expected.add(l1);
		expected.add(l4);
		List<Lecture> actual = sb.extractLecturesByIdAndDate(l1date,
				professorId);
		Assert.assertEquals(expected, actual);
	}
}