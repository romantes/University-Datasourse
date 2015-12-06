package com.mentat.University.exception;

public class SubjectExistsException extends UniversityObjectExistanseException {

	private static final long serialVersionUID = -916942570732444608L;

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
