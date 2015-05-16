package exceptions;

public class InvalidConfigFileException extends Exception {
	
	/**
	 * Report an invalidly formatted configuration file.
	 */
	public InvalidConfigFileException() {
		super("Invalidly formatted configuration file.");
	}
	/**
	 * Report an invalidly formatted configuration file.
	 * @param message The detail message.
	 */
	public InvalidConfigFileException(String message) {
		super(message);
	}

}
