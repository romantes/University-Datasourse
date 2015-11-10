package com.mentat.OOP.task6;

@SuppressWarnings("serial")
public class GroupExistsException extends Exception {
	public GroupExistsException() {
		super();
		System.out.println("Group you trying to add is already exists");
	}
}
