package engine;

import fr.univubs.inf1603.othello.DAO.DAOException;
import fr.univubs.inf1603.othello.DAOSGDB.DAOSGBD;

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
     * Name of the property "forfeit"
     */
    private final static String FORFEIT_PROPERTY = "Forfeit";

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
     * True if a Player forfeits
     */
    private boolean forfeit;

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
        this.forfeit = false;
        this.setForfeit(false);
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
     * Gets if a HumanPlayer forfeits.
     *
     * @return If a HumanPlayer forfeits
     */
    public boolean getForfeit() {
        return this.forfeit;
    }

    /**
     * Sets if a HumanPlayer forfeits.
     *
     * @param forfeit If a HumanPlayer forfeits
     */
    void setForfeit(boolean forfeit) {
        boolean oldForfeit = this.forfeit;

        this.forfeit = forfeit;
        this.changeSupport.firePropertyChange(FORFEIT_PROPERTY, oldForfeit, this.forfeit);
    }

    /**
     * Persists a HumanPlayer into the DAO.
     *
     * @param email Email of the HumanPlayer
     * @param password Password of the HumanPlayer
     * @param name Name of the HumanPlayer
     */
    void createAccount(String email, String password, String name) {
        DAOSGBD daosgbd = new DAOSGBD();

        this.setMail(email);
        this.setHashPassword(password);
        this.setNickname(name);

        try {
            daosgbd.savePlayer(this);
        }

        catch (DAOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Remove a HumanPlayer from the DAO.
     *
     * @param email Email of the HumanPlayer
     * @param password Password of the HumanPlayer
     */
    void removeAccount(String email, String password) {
        DAOSGBD daosgbd = new DAOSGBD();

        if(this.mail == email && this.hashPassword == password) {
            try {
                daosgbd.deletePlayer(this.getId());
            }

            catch (DAOException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * Finds a random Player to play against.
     *
     * @return A random Player to play against
     */
    HumanPlayer findRandomOpponent() {

        /*
            package engine;

            import java.util.Queue;



            public class GestionForRandomOppement {

                private static Queue<Player> opponent;


                public static Player getRandomOppoment(Player player1) {
                    // on vérifie que player1 n'est pas deja inscrit en liste d'attente
                    if(!opponent.contains(player1)){
                        // dépile le premier Player de la queue
                        Player jrs = opponent.poll();
                        if(jrs == null) {
                            // si aucun opposant n'est trouvé player1 est ajouté en liste d'attente
                            opponent.add(player1);
                            System.out.println("Veuillez patienter le temps de trouver un adversaire");
                        }
                        else {
                            return opponent.poll();
                        }

                    }
                    return null;
                }
            }
        */

        return null;
    }

    /**
     * Forfeits the given game.
     *
     * @param game Game played
     */
    public void forfeit(Game game) {
        this.setForfeit(true);

        game.setState(State.END);
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
