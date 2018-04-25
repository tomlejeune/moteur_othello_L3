package engine;

import java.util.ArrayList;
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
     * Name of the property "mail"
     */
    private final static String MAIL_PROPERTY = "Mail";

    /**
     * Name of the property "hashPassword"
     */
    private final static String HASH_PASSWORD_PROPERTY = "HashPassword";

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

        this.changeSupport = new PropertyChangeSupport(this);

        this.mail = "";
        this.setMail(mail);
        this.hashPassword = "";
        this.setHashPassword(hashPassword);
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
     * Sets the mail of the HumanPlayer.
     *
     * @param mail The new mail of the HumanPlayer
     */
    public void setMail(String mail) {
        String oldMail = this.mail;

        this.mail = mail;
        this.changeSupport.firePropertyChange(MAIL_PROPERTY, oldMail, this.mail);
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
     * Gets the password of the HumanPlayer.
     *
     * @param hashPassword The new password of the HumanPlayer
     */
    public void setHashPassword(String hashPassword) {
        String oldHashPassword = this.hashPassword;

        this.hashPassword = hashPassword;
        this.changeSupport.firePropertyChange(HASH_PASSWORD_PROPERTY, oldHashPassword, this.hashPassword);
    }

    /**
     * Finds a random Player to play against.
     *
     * @param opponents List of opponents
     * @return A random Player to play against
     */
    HumanPlayer findRandomOpponent(ArrayList<HumanPlayer> opponents) {
        return RandomOpponentHandler.getRandomOppoment(this, opponents);
    }

    /**
     * Removes the Player from the waiting list of finding a random Player to play against.
     *
     * @param opponents List of opponents
     */
    void removeFromWaitingList(ArrayList<HumanPlayer> opponents) {
        RandomOpponentHandler.removeFromRandomOppoment(this, opponents);
    }

    /**
     * String version of a HumanPlayer
     *
     * @return String version of a HumanPlayer
     */
    public String toString() {
        return "HumanPlayer : "+this.getNickname();
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
