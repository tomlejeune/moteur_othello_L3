package engine;

import java.util.UUID;
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
     * Name of the property "hashPassword"
     */
    private final static String HASH_PASSWORD_PROPERTY = "HashPassword";

    /**
     * Name of the property "mail"
     */
    private final static String MAIL_PROPERTY = "Mail";

    /**
     * Listener support.
     */
    private PropertyChangeSupport changeSupport;

    /**
     * Mail of a HumanPlayer.
     */
    private String mail;

    /**
     * Password of a HumanPlayer.
     */
    private String hashPassword;

    /**
     * Constructs a HumanPlayer with an UUID which makes it unique.
     *
     * @param id The UUID of the HumanPlayer
     */
    HumanPlayer(UUID id, String nickname, String mail, String hashPassword) {
        super(id, nickname);

        this.mail = mail;
        this.hashPassword = hashPassword;
    }

    /**
     * Gets the mail of the HumanPlayer.
     *
     * @return The mail of the HumanPlayer
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * Gets the password of the HumanPlayer.
     *
     * @return The password of the HumanPlayer
     */
    public String getHashPassword() {
        return this.hashPassword;
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
