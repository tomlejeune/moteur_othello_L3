package engine;

/**
 * Handles Exceptions when a Game is played
 */
public class PlayException extends Exception {

    /**
     * Constructs a PlayException with the given message
     *
     * @param message Description of the PlayException
     */
    public PlayException(String message) {
        super(message);
    }
}
