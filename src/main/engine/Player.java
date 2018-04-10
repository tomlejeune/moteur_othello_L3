package engine;

import java.io.Serializable;
import java.util.UUID;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

/**
 * Player is a player in the Game. It has Disks.
 * It notifies classes that listens to it when a changes occurs.
 *
 * @version 1.0
 */
public abstract class Player implements Serializable {

    /**
     * Name of the property "id".
     */
    private final static String ID_PLAYER_PROPERTY = "Id";

    /**
     * Name of the property "nickname".
     */
    private final static String NICKNAME_PROPERTY = "Nickname";

    /**
     * Name of the property "disks"
     */
    private final static String DISKS_PROPERTY = "Disks";

    /**
     * Name of the property "disks"
     */
    private final static String CAN_PLAY_PROPERTY = "CanPlay";

    /**
     * Listener support.
     */
    private PropertyChangeSupport changeSupport;

    /**
     * UUID of a Player which makes it unique.
     */
    private UUID id;

    /**
     * Nickname of a Player.
     */
    private String nickname;

    /**
     * Disks of a Player
     */
    private Disk[] disks;

    /**
     * True if a Player can play
     */
    private boolean canPlay;

    /**
     * Constructs a Player by initializing its Disks.
     */
    protected Player(UUID id, String nickname) {
        this.changeSupport = new PropertyChangeSupport(this);

        this.id = null;
        this.setId(id);
        this.nickname = "";
        this.setNickname(nickname);
        this.disks = null;
        this.setDisks(new Disk[64]);
        this.canPlay = true;
        this.setCanPlay(true);
    }

    /**
     * Gets the UUID of a Player.
     *
     * @return The UUID of a Player
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Sets the UUID of a Player.
     *
     * @param id The new UUID of a Player
     */
    void setId(UUID id) {
        UUID oldId = this.id;

        this.id = id;
        this.changeSupport.firePropertyChange(ID_PLAYER_PROPERTY, oldId, this.id);
    }

    /**
     * Gets the nickname of a Player.
     *
     * @return The nickname of a Player
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * Sets the nickname of a Player.
     *
     * @param nickname The new nickname of a Player
     */
    public void setNickname(String nickname) {
        String oldNickname = this.nickname;

        this.nickname = nickname;
        this.changeSupport.firePropertyChange(NICKNAME_PROPERTY, oldNickname, this.nickname);
    }

    /**
     * Gets the Disks of a Player.
     *
     * @return Array of the Disks of a Player
     */
    public Disk[] getDisks() {
        return this.disks;
    }

    /**
     * Sets the Disks of a Player.
     *
     * @param disks New array of the Disks of a Player
     */
    void setDisks(Disk[] disks) {
        Disk[] oldDisks = this.disks;

        this.disks = disks;
        this.changeSupport.firePropertyChange(DISKS_PROPERTY, oldDisks, this.disks);
    }

    /**
     * Gets if a Player can play.
     *
     * @return If a Player can play
     */
    public boolean getCanPlay() {
        return this.canPlay;
    }

    /**
     * Sets if a Player can play.
     *
     * @param canPlay true if a Player can play
     */
    void setCanPlay(boolean canPlay) {
        boolean oldCanPlay = this.canPlay;

        this.canPlay = canPlay;
        this.changeSupport.firePropertyChange(CAN_PLAY_PROPERTY, oldCanPlay, this.canPlay);
    }

    /**
     * Initializes the Disks of the Player at the given Game with the given Color
     *
     * @param game The Game played
     * @param color The Color of the Disks
     */
    void initializeDisks(Game game, EnumColor color) {
        for(int i  = 0 ; i < this.disks.length ; i++) {
            this.disks[i] = new Disk(game, this, color);
        }
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
