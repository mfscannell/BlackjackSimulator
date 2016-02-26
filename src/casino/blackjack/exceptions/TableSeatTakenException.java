package casino.blackjack.exceptions;

public class TableSeatTakenException extends Exception {
    /**
     * Constructor.
     * @param message  The error message
     */
    public TableSeatTakenException(String message) {
        super(message);
    }

}
