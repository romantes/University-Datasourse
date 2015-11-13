package com.mentat.OOP.task6;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;





import com.mentat.OOP.task6.Exceptions.*;

public class Main {
	public static void main(String[] args) throws UniversityObjectExistanseException {

		ScheduleBoard sb = new ScheduleBoard();
		// dates
		Date l1date = new Date(2015, 4, 3, 15, 30);
		Date l2date = new Date(2015, 4, 4, 16, 30);
		Date l3date = new Date(2015, 4, 5, 17, 00);
		Date l4date = new Date(2015, 4, 5, 11, 00);
		Date l5date = new Date(2015, 4, 5, 9, 00);
		// /dates

		// rooms
		Room room1 = new Room("123c");
		Room room2 = new Room("220v");
		Room room3 = new Room("330g");
		
		University.addRoom(room1);
		University.addRoom(room2);
		University.addRoom(room3);
		// /rooms

		// subjects
		University.addSubject(new Subject("CS"));
		University.addSubject(new Subject("Algebra"));
		University.addSubject(new Subject("Philosophy"));
		// /subjects

		// professors	
		Professor professor1 = new Professor(12345, "Ivanov", "Ivan");
		Professor professor2 = new Professor(666, "Gates", "Bill");
		Professor professor3 = new Professor(777, "Martens", "Dr.");
		
		University.addPerson(professor1);
		University.addPerson(professor2);
		University.addPerson(professor3);
		// /professors

		// groups
		Group group1 = new Group ("210/q");
		Group group2 = new Group("110/r");
		
		University.addGroup(group1);
		University.addGroup(group2);
		// /groups

		// students
		Student student1 = new Student(999, "Egor", "Egorov");
		Student student2 = new Student(888, "Sidor", "Sidorov");
		Student student3 = new Student(444, "Alexandr", "Alexandrov");
	
		University.addPerson(student1);
		University.addPerson(student2);
		University.addPerson(student3);	
		
		University.addStudentToGroup(group1, student1);
		University.addStudentToGroup(group1, student2);
		University.addStudentToGroup(group1, student3);
		// /students

		// addLecture
		Lecture l1 = new Lecture().createLecture(l1date, "123c", "Algebra",
				666, "210/q");
		Lecture l2 = new Lecture().createLecture(l2date, "220v", "CS", 777,
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

		Date searchDate = new Date(2015, 4, 5);
		Long personId = (long) 888;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Lecture> searchResult = sb.extractLecturesByIdAndDate(
				searchDate, personId);
		
		System.out.println("Day schedule for: " + University.getPersonById(personId).toString()
				+ "Date: " + sdf.format(searchDate));
		
		for (Lecture l : searchResult) {
			System.out.println(l.toString());
		}

	}

}
