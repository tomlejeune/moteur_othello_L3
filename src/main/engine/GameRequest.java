package engine;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.UUID;

/**
 * GameRequest is a class that let a Player ask another Player to join a Game.
 * The second player can accept or refuse the request, the first
 * player can always cancel the request.
 * When both players are ready, the game can be started
 *
 * @version 1.0.0
 */
public class GameRequest implements Serializable {

    /**
     * Name of the property "accepted".
     */
    private final static String ACCEPTED_PROPERTY = "Accepted";

    /**
     * Name of the property "canceled".
     */
    private final static String CANCELED_PROPERTY = "Canceled";

    /**
     * Name of the property "gameStarted".
     */
    private final static String GAME_STARTED_PROPERTY = "GameStarted";

    /**
     * Listener support.
     */
    private PropertyChangeSupport changeSupport;

    /**
     * Game
     */
    private Game game;

    /**
     * First Player
     */
    private Player player1;

    /**
     * Second Player
     */
    private Player player2;

    /**
     * Rule
     */
    private Rule rule;

    /**
     * True if the GameRequest is accepted
     */
    private boolean accepted;

    /**
     * True if the GameRequest is canceled
     */
    private boolean canceled;

    /**
     * True if the Game started
     */
    private boolean gameStarted;

    /**
     * Constuctor of the class GameRequest
     *
     * @param player1 The player who wants to create the game
     * @param player2 The other player
     * @param rule The rule of the game to create
     */
    public GameRequest(Player player1, Player player2, Rule rule) {
        this.changeSupport = new PropertyChangeSupport(this);

        this.game = null;
        this.player1 = player1;
        this.player2 = player2;
        this.rule = rule;

        this.accepted = false;
        this.setAccepted(false);
        this.canceled = false;
        this.setCanceled(false);
        this.gameStarted = false;
        this.setGameStarted(false);

        this.player2.requested(this);
    }

    /**
     * Sets if a GameRequest is accepted.
     *
     * @param accepted If a GameRequest is accepted
     */
    void setAccepted(boolean accepted) {
        boolean oldAccepted = this.accepted;

        this.accepted = accepted;
        this.changeSupport.firePropertyChange(ACCEPTED_PROPERTY, oldAccepted, this.accepted);
    }

    /**
     * Sets if a GameRequest is canceled.
     *
     * @param canceled If a GameRequest is canceled
     */
    void setCanceled(boolean canceled) {
        boolean oldCanceled = this.canceled;

        this.canceled = canceled;
        this.changeSupport.firePropertyChange(CANCELED_PROPERTY, oldCanceled, this.canceled);
    }

    /**
     * Sets if a GameRequest is started.
     *
     * @param gameStarted If a GameRequest is started
     */
    void setGameStarted(boolean gameStarted) {
        boolean oldGameStarted = this.gameStarted;

        this.gameStarted = gameStarted;
        this.changeSupport.firePropertyChange(GAME_STARTED_PROPERTY, oldGameStarted, this.gameStarted);
    }

    /**
     * Method to cancel the game request, only the first player can use it
     * @param player1 The player who created the game request
     * @return true if the request has been canceled, false if not
     */
    public boolean cancel(Player player1) {
        boolean ret = false;

        if(this.isAvailable() && (player1.getId().equals(this.player1.getId()))) {
            this.setCanceled(true);
            ret = true;
        }

        return ret;
    }

    /**
     * Method to accept the game request, only the second player can use it
     * @param player2 The player who has been asked for the game
     * @return true if the request has been accepted, false if not
     */
    public boolean accept(Player player2) {
        boolean ret = false;

        if(this.isAvailable() && (player2.getId().equals(this.player2.getId()))) {
            this.setAccepted(true);
            ret = true;
        }

        return ret;
    }

    /**
     * Method to accept the game request, only the second player can use it
     * @param player2 The player who has been asked for the game
     * @return true if the request has been refused, false if not
     */
    public boolean refuse(Player player2) {
        boolean ret = false;

        if(this.isAvailable() && (player2.getId().equals(this.player2.getId()))) {
            this.setCanceled(true);
            ret = true;
        }

        return ret;
    }

    /**
     * Starts the game if both player are ready
     * @return The Game that has been created the the two players and the rule
     */
    public Game startGame() {
        Game ret = null;

        if(this.isAvailable() && this.isAccepted()) {
            this.game = EngineBridge.createGame(UUID.randomUUID(), this.player1, this.player2, this.rule);
            this.setGameStarted(true);

            ret = this.game;
        }

        return ret;
    }

    /**
     * Gets the Game
     *
     * @return The Game
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Returns true if the second player has accepted the request
     * @return True if the second player has accepted the request, false if not
     */
    public boolean isAccepted() {
        return this.accepted;
    }

    /**
     * Returns true if the first player has canceled the request
     * @return True if the first player has canceled the request, false if not
     */
    public boolean isCanceled() {
        return this.canceled;
    }

    /**
     * Returns true if the Game is started
     * @return True if the Game is started, false if not
     */
    public boolean isGameStarted() {
        return this.gameStarted;
    }

    /**
     * Returns true is the GameRequest is available.
     * The request is available it has not been canceled and if the game has not
     * already been started
     * @return True if the game request is available, false if not
     */
    public boolean isAvailable() {
        return (!this.canceled && !this.gameStarted);
    }

    /**
     * Returns the player who created the request
     * @return The player 1
     */
    public Player getPlayer1() {
        return this.player1;
    }

    /**
     * Returns the player who has been requested for a game
     * @return The player 2
     */
    public Player getPlayer2() {
        return this.player2;
    }

    /**
     * Returns the rule of the game request
     * @return The rule
     */
    public Rule getRule() {
        return this.rule;
    }

    /**
     * Add a PropertyChangeListener to the listener list.
     *
     * @param listener The PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.changeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove a PropertyChangeListener from the listener list.
     *
     * @param listener The PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.changeSupport.removePropertyChangeListener(listener);
    }
}
