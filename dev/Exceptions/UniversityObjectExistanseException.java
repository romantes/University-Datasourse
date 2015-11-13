package com.mentat.OOP.task6.Exceptions;

@SuppressWarnings("serial")
public class UniversityObjectExistanseException extends Exception {
	public UniversityObjectExistanseException() {
		super();
	}
	
	public UniversityObjectExistanseException(String message) {
		super(message);
	}
	
	public UniversityObjectExistanseException(Throwable cause) {
		super(cause);
	}
	
	public UniversityObjectExistanseException(String message, Throwable cause) {
		super(message, cause);
	}
}
