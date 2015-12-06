package com.mentat.University;

public abstract class Person {
	private long personId;
	private String firstName;
	private String secondName;

	public Person(long personId, String firstName, String secondName) {
		this.personId = personId;
		this.firstName = firstName;
		this.secondName = secondName;
	}

	public long getPersonId() {
		return personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (personId ^ (personId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (personId != other.personId)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return firstName + ", " + secondName + ", " + "Id: " + personId;
	}
	
	
}
