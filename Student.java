package com.mentat.University;

public class Student extends Person {
	private String goupNumber;

	public Student(long personId, String firstName, String secondName) {
		super(personId, firstName, secondName);
	}

	public String getGoupNumber() {
		return goupNumber;
	}

	public void setGoupNumber(String goupNumber) {
		this.goupNumber = goupNumber;
	}
	
}
