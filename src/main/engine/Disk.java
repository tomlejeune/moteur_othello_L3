package engine;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 * Disk is a disk on the Board. It has a color.
 * It notifies classes that listens to it when a changes occurs.
 *
 * @version 1.0
 */
public class Disk implements Serializable {

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
    private Game game;

    /**
     * Player that owns the Disk.
     */
    private Player player;

    /**
     * Color of the Disk.
     */
    private EnumColor color;

    /**
     * Constructs a Disk with a color.
     *
     * @param player The Player that owns the Disk
     * @param color The color of the Disk
     */
    Disk(Game game, Player player, EnumColor color) {
        this.changeSupport = new PropertyChangeSupport(this);

        this.game = game;
        this.player = player;
        this.color = null;
        this.setColor(color);
    }

    /**
     * Gets the Player that owns the Disk.
     *
     * @return The Player that owns the Disk
     */
    public Game getGame() {
        return this.game;
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
    public EnumColor getColor() {
        return this.color;
    }

    /**
     * Sets the color of a Disk with the one in parameter.
     *
     * @param color The new color of the Disk
     */
    public void setColor(EnumColor color) {
        EnumColor oldColor = this.color;
        this.color = color;

        this.changeSupport.firePropertyChange(COLOR_PROPERTY, oldColor, color);
    }

    /**
     * String version of a Disk.
     *
     * @return String version of a Disk
     */
    public String toString() {
        return "Joueur : "+this.getPlayer()+"\tCouleur : "+this.getColor().toString()+"\n";
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
