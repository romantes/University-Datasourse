package domain;

public class Room {
	private long roomId;
	private String roomNumber;

	public Room() {

	}

	public Room(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Room(long roomId, String roomNumber) {
		this.roomId = roomId;
		this.roomNumber = roomNumber;
	}

	public long getRoomId() {
		return roomId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((roomNumber == null) ? 0 : roomNumber.hashCode());
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
		Room other = (Room) obj;
		if (roomNumber == null) {
			if (other.roomNumber != null)
				return false;
		} else if (!roomNumber.equals(other.roomNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return roomNumber;
	}

}
