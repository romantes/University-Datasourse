package com.mentat.OOP.task6.Exceptions;

@SuppressWarnings("serial")
public class RoomExistsException extends UniversityObjectExistanseException {
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
