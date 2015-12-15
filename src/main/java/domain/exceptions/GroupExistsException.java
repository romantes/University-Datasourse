package domain.exceptions;


public class GroupExistsException extends UniversityObjectExistanseException {

	private static final long serialVersionUID = -13850010769358079L;

	public GroupExistsException() {
		super();
	}
	
	public GroupExistsException(String message) {
		super(message);
	}
		
	public GroupExistsException(Throwable cause) {
		super(cause);
	}
	
	public GroupExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
