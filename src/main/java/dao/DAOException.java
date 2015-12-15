package dao;

public class DAOException extends Exception {

	private static final long serialVersionUID = -581486367694811879L;

	public DAOException() {
		super();
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(Throwable cause, String message) {
		super(message, cause);
	}
}
