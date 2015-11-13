package com.mentat.OOP.task6.Exceptions;

@SuppressWarnings("serial")
public class GroupExistsException extends UniversityObjectExistanseException {
	
	public GroupExistsException() {
		super();
	}
	
	public GroupExistsException(String message) {
		super(message);
	}
		
	public GroupExistsException(Throwable cause) {
		super(cause);
	}
	
	public GroupExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
