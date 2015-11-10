package com.mentat.OOP.task6;

@SuppressWarnings("serial")
public class SubjectExistsException extends Exception {
	public SubjectExistsException() {
		super();
		System.out.println("Subject you trying to add is already exists");
	}
}
