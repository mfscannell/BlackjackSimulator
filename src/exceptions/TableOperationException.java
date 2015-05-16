package exceptions;

public class TableOperationException extends Exception {
	/**
	 * Constructor.
	 * @param message  The error message
	 */
	public TableOperationException(String message) {
		super(message);
	}

}
