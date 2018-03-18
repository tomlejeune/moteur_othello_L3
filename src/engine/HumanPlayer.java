package engine;

import java.util.UUID;
import java.awt.Color;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

/**
 * HumanPlayer is a Player which is human. Every HumanPlayer is unique and is distinguished by an UUID.
 * It extends the class Player.
 *
 * @version 1.0
 */
public class HumanPlayer extends Player {

    /**
     * Name of the property "idHumanPlayer".
     */
    private final static String ID_HUMAN_PLAYER_PROPERTY = "IdHumanPlayer";

    /**
     * Listener support.
     */
    private PropertyChangeSupport changeSupport;

    /**
     * UUID of a HumanPlayer which makes it unique.
     */
    private UUID idHumanPlayer;

    /**
     * Constructs a HumanPlayer with an UUID which makes it unique.
     *
     * @param id The UUID of the HumanPlayer
     */
    HumanPlayer(UUID id, Color color) {
        super(color);

        this.idHumanPlayer = id;
    }

    /**
     * Gets the UUID of the HumanPlayer.
     *
     * @return The UUID of the HumanPlayer
     */
    public UUID getIdHumanPlayer() {
        return this.idHumanPlayer;
    }

    /**
     * Persists a HumanPlayer into the DAO.
     *
     * @param email Email of the HumanPlayer
     * @param password Password of the HumanPlayer
     * @param name Name of the HumanPlayer
     */
    void createAccount(String email, String password, String name) {

    }

    /**
     * Remove a HumanPlayer from the DAO.
     *
     * @param email Email of the HumanPlayer
     * @param password Password of the HumanPlayer
     */
    void removeAccount(String email, String password) {

    }

    /**
     * Finds a random Player to play against.
     *
     * @return A random Player to play against
     */
    Player findRandomOpponent() {
        return null;
    }

    /**
     * Forfeits the given game.
     *
     * @param game Game played
     */
    public void forfeit(Game game) {

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
