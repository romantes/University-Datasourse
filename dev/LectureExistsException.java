package com.mentat.OOP.task6;

@SuppressWarnings("serial")
public class LectureExistsException extends Exception {
	public LectureExistsException() {
		super();
		System.out.println("The lecture you trying to add is already exists");
	}
}
