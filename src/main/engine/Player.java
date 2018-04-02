package engine;

import java.awt.*;
import java.util.UUID;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

/**
 * Player is a player in the Game. It has Disks.
 * It notifies classes that listens to it when a changes occurs.
 *
 * @version 1.0
 */
public abstract class Player {

    /**
     * Name of the property "playerUUID".
     */
    private final static String ID_PLAYER_PROPERTY = "PlayerUUID";

    /**
     * Name of the property "nicknamePlayer".
     */
    private final static String NICKNAME_PLAYER_PROPERTY = "NicknamePlayer";

    /**
     * Name of the property "disks"
     */
    private final static String DISKS_PROPERTY = "Disks";

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
     * Constructs a Player by initializing its Disks.
     */
    Player(UUID id, String nickname) {
        this.changeSupport = new PropertyChangeSupport(this);

        this.id = id;
        this.nickname = nickname;
        this.disks = new Disk[64];
    }

    /**
     * Gets the UUID of a Player.
     *
     * @return The UUID of a Player
     */
    public UUID getPlayerUUID() {
        return this.id;
    }

    /**
     * Gets the nickname of a Player.
     *
     * @return The nickname of a Player
     */
    public String getNicknamePlayer() {
        return this.nickname;
    }

    /**
     * Gets the Disks of a Player.
     *
     * @return Array of the Disks of a Player
     */
    public Disk[] getDisks() {
        return this.disks;
    }

    void initializeDisks(Game game, Color color) {
        for(int i  = 0 ; i < this.disks.length ; i++) {
            this.disks[i] = new Disk(game, this, color);
        }
    }

    /**
     * Places a Disk on the Board at the given Position. Returns true if the Position exists and is not already taken.
     *
     * @param position Position where to place the Disk
     * @return true if the Position exists and is not already taken
     */
    boolean placeDisk(Position position) {
        return false;
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
