package com.mentat.OOP.task6.Exceptions;

@SuppressWarnings("serial")
public class StudentExistException extends PersonExistsException {

	public StudentExistException() {
		super();
	}

	public StudentExistException(String message) {
		super(message);
	}

	public StudentExistException(Throwable cause) {
		super(cause);
	}

	public StudentExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
