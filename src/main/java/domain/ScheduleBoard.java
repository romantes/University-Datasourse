package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.*;
import domain.exceptions.*;

public class ScheduleBoard {
	private Person person;
	private List<Lecture> lectures;

	public ScheduleBoard() throws DAOException {
		lectures = new LectureDAO().getAllLectures();
	}
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
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
		List<Lecture> result = new ArrayList<Lecture>();
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
			throws PersonExistsException, DAOException {
		
		String idpersonStringid = Long.toString(personId);

		if (idpersonStringid.charAt(0) == '1') {
			person = new ProfessorDAO().getProfessor(personId);
		}
		if (idpersonStringid.charAt(0) == '2') {
			person = new StudentDAO().getStudent(personId);
		}
		return extractLecturesByPersonAndDate(date, person);
	}

	public void printLecturesToConsole() throws LectureExistsException {
		if (lectures != null) {
			String personStatus = "everybody";
			if (person instanceof Student) {
				personStatus = "student";
			}
			if (person instanceof Professor) {
				personStatus = "professor";
			}
			System.out.println("List of lectures for: " + personStatus + " "
					+ person);

			if (lectures.size() == 0) {
				System.out.println("Unfortunately, the schedule list is empty");
			}
			for (Lecture l : lectures) {
				System.out.println(l);
			}
		} else {
			throw new LectureExistsException(
					"List of lectures which you trying to print is not exists");
		}
	}
}
