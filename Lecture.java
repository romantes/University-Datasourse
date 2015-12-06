package com.mentat.University;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mentat.University.exception.*;


public class Lecture {
	private Date date;
	private Subject subject;
	private Professor professor;
	private Group group;
	private Room room;

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

	public Lecture(Date date, Subject subject, Professor professor,
			Group group, Room room) {
		this.date = date;
		this.subject = subject;
		this.professor = professor;
		this.group = group;
		this.room = room;
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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return "Lecture " + subject + "Date: "+ formatter.format(date) + 
				" |" + room + "professor: " + professor + " " + group;
	}

}
