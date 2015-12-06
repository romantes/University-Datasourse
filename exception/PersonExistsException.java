package com.mentat.University.exception;

public class PersonExistsException extends UniversityObjectExistanseException {

	private static final long serialVersionUID = -4782113228363895153L;

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
