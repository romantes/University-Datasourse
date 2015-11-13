package com.mentat.OOP.task6.Exceptions;

public class RoomExistsException extends UniversityObjectExistanseException {

	private static final long serialVersionUID = 2126368443897468289L;

	public RoomExistsException() {
		super();
	}

	public RoomExistsException(String message) {
		super(message);
	}

	public RoomExistsException(Throwable cause) {
		super(cause);
	}

	public RoomExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
