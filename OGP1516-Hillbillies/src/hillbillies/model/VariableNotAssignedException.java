package hillbillies.model;

public class VariableNotAssignedException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public VariableNotAssignedException() {
		super();
	}
	
	public VariableNotAssignedException(String message) {
		super(message);
	}
	
	public VariableNotAssignedException(Throwable cause) {
		super(cause);
	}
	
	public VariableNotAssignedException(String message, Throwable cause) {
		super(message, cause);
	}
}
