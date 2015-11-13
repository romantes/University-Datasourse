package com.mentat.OOP.task6.Exceptions;

@SuppressWarnings("serial")
public class LectureExistsException extends UniversityObjectExistanseException {
	public LectureExistsException() {
		super();
	}

	public LectureExistsException(String message) {
		super(message);
	}

	public LectureExistsException(Throwable cause) {
		super(cause);
	}

	public LectureExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
