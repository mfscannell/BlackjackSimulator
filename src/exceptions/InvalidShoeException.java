package exceptions;

public class InvalidShoeException extends Exception {
	/**
	 * Constructor
	 * @param message  Error message.
	 */
	public InvalidShoeException(String message) {
		super(message);
	}

}
