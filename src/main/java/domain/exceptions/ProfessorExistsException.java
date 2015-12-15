package domain.exceptions;

public class ProfessorExistsException extends PersonExistsException {

	private static final long serialVersionUID = -6575147381056791090L;

	public ProfessorExistsException() {
		super();
	}

	public ProfessorExistsException(String message) {
		super(message);
	}

	public ProfessorExistsException(Throwable cause) {
		super(cause);
	}

	public ProfessorExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
