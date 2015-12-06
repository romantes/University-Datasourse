package com.mentat.University;

public class Subject {
	private long subjectId;
	private String subjectTitle;

	public Subject() {

	}

	public Subject(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	public Subject(long subjectId, String subjectTitle) {

		this.subjectId = subjectId;
		this.subjectTitle = subjectTitle;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public String getSubjectTitle() {
		return subjectTitle;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((subjectTitle == null) ? 0 : subjectTitle.hashCode());
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
		Subject other = (Subject) obj;
		if (subjectTitle == null) {
			if (other.subjectTitle != null)
				return false;
		} else if (!subjectTitle.equals(other.subjectTitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return subjectTitle + "| ";
	}

}
