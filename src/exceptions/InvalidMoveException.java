package exceptions;

public class InvalidMoveException extends Exception {
	
	/**
	 * Constructor
	 * @param message  Error message.
	 */
	public InvalidMoveException(String message) {
		super(message);
	}

}
