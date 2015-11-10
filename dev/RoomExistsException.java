package com.mentat.OOP.task6;

@SuppressWarnings("serial")
public class RoomExistsException extends Exception{
	public RoomExistsException() {
		super();
		System.out.println("Room you trying to add is already exists");
	}
}
