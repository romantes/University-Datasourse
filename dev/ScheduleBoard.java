package com.mentat.OOP.task6;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleBoard {
	private List<Lecture> lectures = new ArrayList<Lecture>();

	public List<Lecture> getLectures() {
		return lectures;
	}
	
	public void addLecture(Lecture lecture) throws LectureExistsException {
		if (!lectures.contains(lecture)) 
			lectures.add(lecture);
		else throw new LectureExistsException();
	}
	
	private List<Lecture> extractLecturesByDate(List<Lecture> lectures, Date date) {
		ArrayList<Lecture> result = new ArrayList<Lecture>();
		for (Lecture l : lectures) {
			if (l.getDate().getYear() == date.getYear()
					&& l.getDate().getMonth() == date.getMonth()
					&& l.getDate().getDay() == date.getDay())
				result.add(l);
		}
		return result;
	}

	public List<Lecture> extractLecturesByPersonAndDate(Date date, Person person) {
		ArrayList<Lecture> result = new ArrayList<Lecture>();
		long personId = person.getPersonId();
		for (Lecture l : lectures) {
			if ((l.getGroup().getStudentById(personId) != null)
					|| (l.getProfessor().getPersonId() == personId)) {
				result.add(l);
			}
		}
		return extractLecturesByDate(result, date);
	}
	
	public List<Lecture> extractLecturesByPersonIdAndDate(Date date, Long personId) {
		Person person = getPersonById(personId);
		return extractLecturesByPersonAndDate(date, person); 
	}
	
	public Person getPersonById (Long personId) {
		if (University.getStudentsIdSet().contains(personId)) {
			List <Group> groups = University.getGroups();
			for (Group g : groups) {
				if (g.getStudentById(personId) != null);
				return g.getStudentById(personId);
			}
		} 
		if (University.getProfessorIdset().contains(personId)) {
			return University.getProfessorById(personId);
		}
		return null;	
	}
	
}
