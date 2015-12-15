package domain.exceptions;

public class LectureExistsException extends UniversityObjectExistanseException {

	private static final long serialVersionUID = -8105840375729162360L;

	public LectureExistsException() {
		super();
	}

	public LectureExistsException(String message) {
		super(message);
	}

	public LectureExistsException(Throwable cause) {
		super(cause);
	}

	public LectureExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
