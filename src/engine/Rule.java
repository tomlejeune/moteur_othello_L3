package engine;

/**
 * Rule is an abstract class that specifies every method that a specific Rule of the Othello should have.
 * Every rule should extend this class.
 * All of the methods are also abstract. Every Rule that extends this class should implement those methods.
 *
 * @version 1.0
 */
public abstract class Rule {

    /**
     * Initializes the Board at the beginning of the given game
     *
     * @param game Game played
     */
    abstract Board initializeBoard(Game game);

    /**
     * Gets the first Player to play in the given Game.
     *
     * @param game Game played
     * @return The first Player to play in the given Game
     */
    abstract Player getFirstPlayer(Game game);

    /**
     * Gets the Positions where the given Player can play in the given Game.
     *
     * @param game Game played
     * @param player Player that plays
     * @return Array of the Positions where the given Player can play in the given Game
     */
    abstract Position[] getPlayablePositions(Game game, Player player);

    /**
     * Turns the Disks on the Board of the given Game.
     *
     * @param game Game played
     */
    abstract void turnDisks(Game game);

    /**
     * Gets the Player that won the given Game.
     *
     * @param game Game played
     * @return The Player that won the given Game
     */
    abstract Player getWinner(Game game);

    /**
     * Gets the Player that lost the given Game.
     *
     * @param game Game played
     * @return The Player that lost the given Game
     */
    abstract Player getLoser(Game game);

    /**
     * Returns true if the given Game is draw.
     *
     * @param game
     * @return true if the given Game is draw
     */
    abstract boolean isDraw(Game game);
}
