package engine;

import java.awt.Color;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

/**
 * Disk is a disk on the Board. It has a color.
 * It notifies classes that listens to it when a changes occurs.
 *
 * @version 1.0
 */
public class Disk {

    /**
     * Name of the property "player".
     */
    public static final String PLAYER_PROPERTY = "Player";

    /**
     * Name of the property "color".
     */
    public static final String COLOR_PROPERTY = "Color";

    /**
     * Listener support.
     */
    private PropertyChangeSupport changeSupport;

    /**
     * Player that owns the Disk.
     */
    private Player player;

    /**
     * Color of the Disk.
     */
    private Color color;

    /**
     * Constructs a Disk with a color.
     *
     * @param player The Player that owns the Disk
     * @param color The color of the Disk
     */
    public Disk(Player player, Color color) {
        this.changeSupport = new PropertyChangeSupport(this);
        this.player = player;
        this.color = color;
    }

    /**
     * Gets the Player that owns the Disk.
     *
     * @return The Player that owns the Disk
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Gets the color of a Disk.
     *
     * @return The color of the Disk
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of a Disk with the one in parameter.
     *
     * @param color The new color of the Disk
     */
    public void setColor(Color color) {
        Color oldColor = this.color;
        this.color = color;

        this.changeSupport.firePropertyChange(COLOR_PROPERTY, oldColor, color);
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
