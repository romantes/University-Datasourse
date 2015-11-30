package com.mentat.OOP.task6;

import java.util.Date;
import com.mentat.OOP.task6.Exceptions.*;


public class Lecture {
	private Date date;
	private Room room;
	private Subject subject;
	private Professor professor;
	private Group group;

	public Date getDate() {
		return date;
	}

	public Room getRoom() {
		return room;
	}

	public Subject getSubject() {
		return subject;
	}

	public Professor getProfessor() {
		return professor;
	}

	public Group getGroup() {
		return group;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Lecture createLecture(Date date, String roomNumber,
			String subjectTitle, long personId, String groupNumber) throws UniversityObjectExistanseException {
		Lecture lecture = new Lecture();
		lecture.date = date;
		lecture.room = University.getRoomByNumber(roomNumber);
		lecture.subject = University.getSubjectByTitle(subjectTitle);
		lecture.professor = (Professor) University.getPersonById(personId);
		lecture.group = University.getGroupByNumber(groupNumber);
		return lecture;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lecture other = (Lecture) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lecture " + subject +" Time: "+ date.getHours() + ":" + date.getMinutes() + "|" + room + "professor: " + professor + group;
	}

}
