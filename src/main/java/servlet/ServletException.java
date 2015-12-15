package servlet;

public class ServletException extends Exception {

	private static final long serialVersionUID = -3763242910495737653L;
	
	public ServletException() {
		super();
	}
	
	public ServletException(String message) {
		super(message);
	}
	
	public ServletException(Throwable cause) {
		super(cause);
	}
	
	public ServletException(String message, Throwable cause) {
		super(message, cause);
	}
}
	