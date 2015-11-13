package com.mentat.OOP.task6.Exceptions;

@SuppressWarnings("serial")
public class PersonExistsException extends UniversityObjectExistanseException {
	public PersonExistsException() {
		super();
	}

	public PersonExistsException(String message) {
		super(message);
	}

	public PersonExistsException(Throwable cause) {
		super(cause);
	}

	public PersonExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
