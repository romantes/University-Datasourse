package com.mentat.OOP.task6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class University {
	private static List<Room> rooms = new ArrayList<Room>();
	private static List<Subject> subjects = new ArrayList<Subject>();
	private static List<Professor> professors = new ArrayList<Professor>();
	private static List<Group> groups = new ArrayList<Group>();
	private static Set<Long> studentsIdSet = new HashSet<Long>();
	private static Set<Long> professorIdset = new HashSet<Long>();

	public static void addRoom(Room room) throws RoomExistsException {
		if (rooms.contains(room))
			throw new RoomExistsException();
		else
			rooms.add(room);
	}

	public static void addSubject(Subject subject)
			throws SubjectExistsException {
		if (subjects.contains(subject))
			throw new SubjectExistsException();
		else
			subjects.add(subject);
	}

	public static void addProfessor(Professor professor)
			throws PersonExistsException {
		if (professors.contains(professor)
				|| professorIdset.contains(professor.getPersonId()))
			throw new PersonExistsException();
		else {
			professors.add(professor);
			professorIdset.add(professor.getPersonId());
		}
	}

	public static void addGroup(Group group) throws GroupExistsException {
		if (groups.contains(group))
			throw new GroupExistsException();
		else
			groups.add(group);
	}

	public static Room getRoomByNumber(String roomNumber) {
		for (Room r : rooms) {
			if (r.getRoomNumber().equals(roomNumber))
				return r;
		}
		return null;
	}

	public static Set<Long> getStudentsIdSet() {
		return studentsIdSet;
	}

	public static Set<Long> getProfessorIdset() {
		return professorIdset;
	}
	
	public static List<Group> getGroups() {
		return groups;
	}

	public static void setGroups(List<Group> groups) {
		University.groups = groups;
	}

	public static void setStudentsIdSet(Set<Long> studentsIdSet) {
		University.studentsIdSet = studentsIdSet;
	}

	public static void setProfessorIdset(Set<Long> professorIdset) {
		University.professorIdset = professorIdset;
	}

	public static Subject getSubjectByTitle(String subjectTitle) {
		for (Subject s : subjects) {
			if (s.getSubjectTitle().equals(subjectTitle))
				return s;
		}
		return null;
	}

	public static Professor getProfessorById(long professorId) {
		for (Professor p : professors) {
			if (p.getPersonId() == professorId)
				return p;
		}
		return null;
	}

	public static Group getGroupByNumber(String groupNumber) {
		for (Group g : groups) {
			if (g.getGroupNumber().equals(groupNumber))
				return g;
		}
		return null;
	}

}
