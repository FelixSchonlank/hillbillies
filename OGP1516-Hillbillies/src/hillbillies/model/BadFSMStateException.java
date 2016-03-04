package hillbillies.model;

public class BadFSMStateException extends Throwable {
	private static final long serialVersionUID = 1L;
	
	public BadFSMStateException() {
		super();
	}
	
	public BadFSMStateException(String message) {
		super(message);
	}
	
	public BadFSMStateException(Throwable cause) {
		super(cause);
	}
	
	public BadFSMStateException(String message, Throwable cause) {
		super(message, cause);
	}
}