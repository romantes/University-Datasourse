package domain;

import java.util.HashSet;
import java.util.Set;

public class Group {
	private long groupId;
	private String groupNumber;
	private Set<Long> students = new HashSet();

	public Group(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public Group(long groupId, String groupNumber) {
		this.groupId = groupId;
		this.groupNumber = groupNumber;
	}

	public long getGroupId() {
		return groupId;
	}
	
	public String getGroupNumber() {
		return groupNumber;
	}
	
	public Set<Long> getStudents() {
		return students;
	}
	
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((groupNumber == null) ? 0 : groupNumber.hashCode());
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
		Group other = (Group) obj;
		if (groupNumber == null) {
			if (other.groupNumber != null)
				return false;
		} else if (!groupNumber.equals(other.groupNumber))
			return false;
		return true;
	}

	@Override
	public String toString () {
		return groupNumber;
	}

}
