package engine;

import java.awt.Color;
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
     * Name of the property "disks"
     */
    private final static String DISKS_PROPERTY = "Disks";

    /**
     * Listener support.
     */
    private PropertyChangeSupport changeSupport;

    /**
     * Disks of a Player
     */
    private Disk[] disks;

    /**
     * Constructs a Player by initializing its Disks.
     */
    Player(Color color) {
        this.changeSupport = new PropertyChangeSupport(this);

        this.disks = new Disk[32];

        for(int i  = 0 ; i < this.disks.length ; i++) {
            this.disks[i] = new Disk(this, color);
        }
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
