package com.mentat.OOP.task6.Exceptions;

@SuppressWarnings("serial")
public class SubjectExistsException extends UniversityObjectExistanseException {
	public SubjectExistsException() {
		super();
	}

	public SubjectExistsException(String message) {
		super(message);
	}

	public SubjectExistsException(Throwable cause) {
		super(cause);
	}

	public SubjectExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
