package domain.exceptions;

public class StudentExistException extends PersonExistsException {

	private static final long serialVersionUID = -8575740833982605217L;

	public StudentExistException() {
		super();
	}

	public StudentExistException(String message) {
		super(message);
	}

	public StudentExistException(Throwable cause) {
		super(cause);
	}

	public StudentExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
