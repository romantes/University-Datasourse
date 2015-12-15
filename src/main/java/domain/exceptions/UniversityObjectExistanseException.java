package domain.exceptions;


public class UniversityObjectExistanseException extends Exception {

	private static final long serialVersionUID = 3083934050924334975L;

	public UniversityObjectExistanseException() {
		super();
	}
	
	public UniversityObjectExistanseException(String message) {
		super(message);
	}
	
	public UniversityObjectExistanseException(Throwable cause) {
		super(cause);
	}
	
	public UniversityObjectExistanseException(String message, Throwable cause) {
		super(message, cause);
	}
}
