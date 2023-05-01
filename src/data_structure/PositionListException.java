package data_structure;

public class PositionListException extends RuntimeException {

	private static final Long serialVersionUID = 1L;
	
	public PositionListException() {
		super();
	}
	
	public PositionListException(String message) {
		super(message);
	}
	
	public PositionListException(Throwable cause) {
		super(cause);
	}
	
	public PositionListException(String message, Throwable cause) {
		super(cause);
	}
}
