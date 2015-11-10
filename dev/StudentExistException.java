package com.mentat.OOP.task6;

@SuppressWarnings("serial")
public class StudentExistException extends Exception {

	public StudentExistException() {
		super();
		System.out.println("Student you trying to add is already exists");
	}

}
