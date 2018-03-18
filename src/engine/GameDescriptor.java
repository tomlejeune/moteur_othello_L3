package engine;

import java.util.UUID;
import java.util.Hashtable;

/**
 * GameDescriptor describes a Game without instantiating a Game.
 *
 * @version 1.0
 */
public class GameDescriptor {

    /**
     * UUID of a Game
     */
    private UUID id;

    /**
     * Rule of a Game
     */
    private Rule rule;

    /**
     * State of a Game
     */
    private State state;

    /**
     * Scores of the Players of a Game
     */
    private Hashtable<Player, Integer> playersScores;

    /**
     * Constructs a GameDescriptor with a UUID, a Rule, a State and two Players.
     * It also constructs the Hashtable with the Players and their score.
     *
     * @param id UUID of the Game
     * @param rule Rule of the Game
     * @param state State of the Game
     * @param player1 First Player of the Game
     * @param player2 Second Player of the Game
     */
    GameDescriptor(UUID id, Rule rule, State state, Player player1, Player player2) {

    }

    /**
     * Gets the Rule of the Game.
     *
     * @return The Rule of the Game
     */
    public Rule getRule() {
        return this.rule;
    }

    /**
     * Gets the State of the Game.
     *
     * @return The State of the Game
     */
    public State getState() {
        return this.state;
    }

    /**
     * Gets the Players of the Game and their score.
     *
     * @return The Players of the Game and their score
     */
    public Hashtable<Player, Integer> getPlayersScores() {
        return this.playersScores;
    }
}
