package com.mentat.OOP.task6;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mentat.OOP.task6.Exceptions.*;

public class ScheduleBoard {
	private List<Lecture> lectures = new ArrayList<Lecture>();

	public List<Lecture> getLectures() {
		return lectures;
	}

	public void addLecture(Lecture lecture) throws LectureExistsException {
		if (!lectures.contains(lecture))
			lectures.add(lecture);
		else
			throw new LectureExistsException();
	}

	public List<Lecture> extractLecturesByDate(List<Lecture> lectures, Date date) {
		ArrayList<Lecture> result = new ArrayList<Lecture>();
		for (Lecture l : lectures) {
			if (l.getDate().getYear() == date.getYear()
					&& l.getDate().getMonth() == date.getMonth()
					&& l.getDate().getDay() == date.getDay())
				result.add(l);
		}
		return result;
	}

	public List<Lecture> extractLecturesByPersonAndDate(Date date, Person person)
			throws PersonExistsException {
		ArrayList<Lecture> result = new ArrayList<Lecture>();
		long personId = person.getPersonId();

		if (person instanceof Professor) {
			for (Lecture l : lectures) {
				if (l.getProfessor().getPersonId() == personId) {
					result.add(l);
				}
			}
		}
		if (person instanceof Student) {
			String groupNumber = ((Student) person).getGoupNumber();
			for (Lecture l : lectures) {
				if (l.getGroup().getGroupNumber().equals(groupNumber)) {
					result.add(l);

				}
			}
		}
		return extractLecturesByDate(result, date);
	}

	public List<Lecture> extractLecturesByIdAndDate(Date date, Long personId)
			throws PersonExistsException {
		Person person = University.getPersonById(personId);
		return extractLecturesByPersonAndDate(date, person);
	}

}
