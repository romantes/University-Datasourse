package com.mentat.OOP.task6;

import java.util.Date;
import java.util.List;

public class Main {
	public static void main(String[] args) throws RoomExistsException,
			SubjectExistsException, PersonExistsException,
			GroupExistsException, LectureExistsException, StudentExistException {

		ScheduleBoard sb = new ScheduleBoard();
		// dates
		Date l1date = new Date(2015, 4, 3, 15, 30);
		Date l2date = new Date(2015, 4, 4, 16, 30);
		Date l3date = new Date(2015, 4, 5, 17, 00);
		Date l4date = new Date(2015, 4, 5, 11, 00);
		Date l5date = new Date(2015, 4, 5, 9, 00);
		// /dates

		// rooms
		University.addRoom(new Room("123c"));
		University.addRoom(new Room("220v"));
		University.addRoom(new Room("330g"));
		// /rooms

		// subjects
		University.addSubject(new Subject("CS"));
		University.addSubject(new Subject("Algebra"));
		University.addSubject(new Subject("Philosophy"));
		// /subjects

		// professors
		University.addProfessor(new Professor(12345, "Ivanov", "Ivan"));
		University.addProfessor(new Professor(666, "Gates", "Bill"));
		University.addProfessor(new Professor(777, "Martens", "Dr."));
		// /professors

		// groups
		University.addGroup(new Group("210/q"));
		University.addGroup(new Group("110/r"));
		// /groups

		// students
		University.getGroupByNumber("210/q").addStudent(
				new Student(999, "Egor", "Egorov"));
		University.getGroupByNumber("210/q").addStudent(
				new Student(888, "Sidor", "Sidorov"));
		University.getGroupByNumber("210/q").addStudent(
				new Student(444, "Alexandr", "Alexandrov"));
		// /students

		// addLecture
		Lecture l1 = new Lecture().createLecture(l1date, "123c", "Algebra",
				666, "210/q");
		Lecture l2 = new Lecture().createLecture(l2date, "220c", "CS", 777,
				"110/r");
		Lecture l3 = new Lecture().createLecture(l3date, "330g", "CS", 666,
				"210/q");
		Lecture l4 = new Lecture().createLecture(l4date, "330g", "CS", 12345,
				"210/q");
		Lecture l5 = new Lecture().createLecture(l5date, "330g", "CS", 666,
				"210/q");

		sb.addLecture(l1);
		sb.addLecture(l2);
		sb.addLecture(l3);
		sb.addLecture(l4);
		sb.addLecture(l5);
		// /addLecture
		
		
		Date searchDate = l4date;
		Long personId = (long)888;

		List<Lecture> searchResult = sb.extractLecturesByPersonIdAndDate(searchDate, personId);
		System.out.println("Day schedule for: " + sb.getPersonById(personId) 
				+ "Month: " + searchDate.getMonth() + " Day: "
				+ searchDate.getDate());
		for (Lecture l : searchResult) {
			System.out.println(l.toString());
		}
		
		System.out.println("idStudents set: " + University.getStudentsIdSet().toString());
		System.out.println("idProfessor set: " + University.getProfessorIdset().toString());
	}

}
