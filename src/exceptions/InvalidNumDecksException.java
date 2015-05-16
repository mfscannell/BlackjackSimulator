package exceptions;

public class InvalidNumDecksException extends Exception {
	/**
	 * Constructor
	 * @param message  Error message.
	 */
	public InvalidNumDecksException(String message) {
		super(message);
	}

}
