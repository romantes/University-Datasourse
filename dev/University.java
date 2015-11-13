package com.mentat.OOP.task6;

import java.util.HashSet;
import java.util.Set;

import com.mentat.OOP.task6.Exceptions.*;

public class University {
	private static Set<Room> rooms = new HashSet<>();
	private static Set<Subject> subjects = new HashSet<>();
	private static Set<Person> persons = new HashSet<>();
	private static Set<Group> groups = new HashSet<>();

	public static void addRoom(Room room) throws RoomExistsException {
		if (rooms.contains(room)) {
			throw new RoomExistsException();
		} else {
			rooms.add(room);
		}
	}

	public static void addSubject(Subject subject)
			throws SubjectExistsException {
		if (subjects.contains(subject)) {
			throw new SubjectExistsException();
		} else {
			subjects.add(subject);
		}
	}

	public static void addPerson(Person person)
			throws PersonExistsException {
		if (persons.contains(person)) {
			throw new PersonExistsException();
		} else {
			persons.add(person);
		}
	}

	public static void addGroup(Group group) throws GroupExistsException {
		if (groups.contains(group)) {
			throw new GroupExistsException();
		} else {
			groups.add(group);
		}
	}

	public static void addStudentToGroup(Group group, Student student)
			throws UniversityObjectExistanseException {
		if (!groups.contains(group)) {
			throw new GroupExistsException();
		}
		if (!persons.contains(student)) {
			throw new StudentExistException();
		} else {
			String groupNumber = group.getGroupNumber();
			Long personId = student.getPersonId();
			University.getGroupByNumber(groupNumber).getStudents()
					.add(personId);
			student.setGoupNumber(groupNumber);
		}
	}
	
	public static Room getRoomByNumber(String roomNumber)
			throws RoomExistsException {
		for (Room r : rooms) {
			if (r.getRoomNumber().equals(roomNumber))
				return r;
		}
		throw new RoomExistsException();
	}

	public static Subject getSubjectByTitle(String subjectTitle)
			throws SubjectExistsException {
		for (Subject s : subjects) {
			if (s.getSubjectTitle().equals(subjectTitle)) {
				return s;
			}
		}
		throw new SubjectExistsException();
	}

	public static Person getPersonById(long personId)
			throws PersonExistsException {
		for (Person p : persons) {
			if (p.getPersonId() == personId) {
				return p;
			}
		}
		throw new PersonExistsException();
	}

	public static Group getGroupByNumber(String groupNumber)
			throws GroupExistsException {
		for (Group g : groups) {
			if (g.getGroupNumber().equals(groupNumber)) {
				return g;
			}
		}
		throw new GroupExistsException();
	}
	 
}
