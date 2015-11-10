package com.mentat.OOP.task6;

@SuppressWarnings("serial")
public class PersonExistsException extends Exception {
	public PersonExistsException() {
		super();
		System.out.println("Person you trying to add is already exists");
	}
}
