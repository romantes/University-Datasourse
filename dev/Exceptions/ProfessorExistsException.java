package com.mentat.OOP.task6.Exceptions;

@SuppressWarnings("serial")
public class ProfessorExistsException extends PersonExistsException {
	public ProfessorExistsException() {
		super();
	}

	public ProfessorExistsException(String message) {
		super(message);
	}

	public ProfessorExistsException(Throwable cause) {
		super(cause);
	}

	public ProfessorExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
