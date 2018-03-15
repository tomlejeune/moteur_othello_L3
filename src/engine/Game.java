package engine;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.UUID;

/**
 * Game is a game of Othello. Every Game is unique and is distinguished by an UUID.
 * It has a Rule, two Players, a State and a Board.
 * It notifies classes that listens to it when a changes occurs.
 *
 * @version 1.0
 */
public class Game {

    /**
     * Name of the property "idGame".
     */
    private final static String ID_GAME_PROPERTY = "IdGame";

    /**
     * Name of the property "rule".
     */
    private final static String RULE_PROPERTY = "Rule";

    /**
     * Name of the property "player1".
     */
    private final static String PLAYER_1_PROPERTY = "Player1";

    /**
     * Name of the property "player2".
     */
    private final static String PLAYER_2_PROPERTY = "Player2";

    /**
     * Name of the property "state".
     */
    private final static String STATE_PROPERTY = "State";

    /**
     * Name of the property "board".
     */
    private final static String BOARD_PROPERTY = "Board";

    /**
     * Listener support.
     */
    private PropertyChangeSupport changeSupport;

    /**
     * UUID of a Game which makes it unique.
     */
    private UUID idGame;

    /**
     * Rule of a Game
     */
    private Rule rule;

    /**
     * First Player of a Game
     */
    private Player player1;

    /**
     * Second Player of a Game
     */
    private Player player2;

    /**
     * State of a Game
     */
    private State state;

    /**
     * Board of a Game
     */
    private Board board;

    /**
     * Constructs a Game with two Players and a Rule. It also initializes all other attributes.
     *
     * @param player1 First player of the Game
     * @param player2 Second player of the Game
     * @param rule Rule of the Game
     */
    public Game(Player player1, Player player2, Rule rule) {
        this.changeSupport = new PropertyChangeSupport(this);

        //this.idGame = DAO...;
        this.player1 = player1;
        this.player2 = player2;
        this.rule = rule;
        this.state = State.INIT;
        this.board = this.rule.initializeBoard(this);
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
     * Gets the first Player of the Game.
     *
     * @return The first Player of the Game
     */
    public Player getPlayer1() {
        return this.player1;
    }

    /**
     * Gets the second Player of the Game.
     *
     * @return The second Player of the Game
     */
    public Player getPlayer2() {
        return this.player2;
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
     * Gets the Board of the Game.
     *
     * @return The Board of the Game
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Gets the current Player of the Game.
     *
     * @return The current Player of the Game
     */
    public Player getCurrentPlayer() {
        return null;
    }

    /**
     * Gets the Positions where the given Player can play by asking the Rule.
     *
     * @param player Player that plays
     * @return Array of the Positions where the given Player can play
     */
    public Position[] getPlayablePositions(Player player) {
        return null;
    }

    /**
     * Gets all the Positions where a Player placed his Disks by asking the Board.
     *
     * @param player The Player that plays
     * @return Array of Positions where a Player placed his Disks
     */
    public Position[] getPositions(Player player) {
        return this.board.getPositions(this, player);
    }

    /**
     * Gets the number of points of the given Player by asking the Counter.
     *
     * @param player Player that plays
     * @return The number of points of the given Player
     */
    public int getNbPoints(Player player) {
        return Counter.getNbPoint(this, player);
    }

    /**
     * The given player plays at the given position.
     *
     * @param player Player that plays
     * @param position Position of where the Player wants to play
     */
    public void play(Player player, Position position) {
        this.board.placeDisk(player, position);
        this.rule.turnDisks(this);
    }

    /**
     * Gets the Player that won the Game.
     *
     * @return The Player that won the Game
     */
    public Player getWinner() {
        return this.rule.getWinner(this);
    }

    /**
     * Gets the Player that lost the Game.
     *
     * @return The Player that lost the Game
     */
    public Player getLoser() {
        return this.rule.getLoser(this);
    }

    /**
     * Returns true if the Game is draw.
     *
     * @return true if the Game is draw
     */
    public boolean isDraw() {
        return this.rule.isDraw(this);
    }
}
