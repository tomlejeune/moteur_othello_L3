package engine;

import java.util.ArrayList;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

/**
 * Board of the Game. It has a Hashtable of Disks at a Position.
 * It notifies classes that listens to it when a changes occurs.
 *
 * @version 1.0
 */
public class Board {

    /**
     * Name of the property "board".
     */
    public static final String BOARD_PROPERTY = "Board";

    /**
     * Name of the property "game".
     */
    public static final String GAME_PROPERTY = "Game";

    /**
     * Listener support.
     */
    private PropertyChangeSupport changeSupport;

    /**
     * Maximum size for the width of the Board.
     */
    private final static int MAX_SIZE_WIDTH = 8;

    /**
     * Maximum size for the height of the Board.
     */
    private final static int MAX_SIZE_HEIGHT = 8;

    /**
     *
     */
    private Disk[][] board = new Disk[MAX_SIZE_WIDTH][MAX_SIZE_HEIGHT];

    /**
     *
     */
    private Game game;


    /**
     * Constructs a Board by initializing the HashTable.
     *
     * @param game Game played
     */
    Board(Game game) {
        this.changeSupport = new PropertyChangeSupport(this);

        this.game = game;
    }

    /**
     * Gets the Board of Disks.
     *
     * @return The Board of Disks
     */
    Disk[][] getBoard() {
        return this.board;
    }

    /**
     * Sets the Board of Disks.
     *
     * @param board The new Board of Disks
     */
    void setBoard(Disk[][] board) {
        Disk[][] oldBoard = this.board;
        this.board = board;

        this.changeSupport.firePropertyChange(BOARD_PROPERTY, oldBoard, this.board);
    }

    /**
     * Gets the Disk at the given Position, null if no Disk.
     *
     * @param position Position on the Board
     * @return The Disk at the given Position
     */
    public Disk getDisk(Position position) {
        return board[position.getXCoordinate()][position.getYCoordinate()];
    }

    /**
     * Gets all the Positions where a Player placed his Disks during a Game.
     *
     * @param game The Game played
     * @param player The Player that plays
     * @return Array of Positions where a Player placed his Disks during a Game
     */
    Position[] getPositions(Game game, Player player) {
        ArrayList<Position> positions = new ArrayList<Position>();

        for(int i = 0 ; i < board.length ; i++) {
            for(int j = 0 ; j < board[i].length ; j++) {
                if(board[i][j] != null) {
                    if(board[i][j].getPlayer() == player) {
                        positions.add(new Position(i, j));
                    }
                }
            }
        }

        Position[] retour = new Position[positions.size()];

        for(int i = 0 ; i < retour.length ; i++) {
            retour[i] = positions.get(i);
        }

        return retour;
    }

    /**
     * Places a Disk on the Board at the given Position. Returns true if the Position exists and is not already taken.
     *
     * @param position Position where to place the Disk
     * @return true if the Position exists and is not already taken
     */
    boolean placeDisk(Player player, Position position) {
        boolean retour = false;

        if(board[position.getXCoordinate()][position.getYCoordinate()] == null) {
            retour = true;

            Disk[] disksPlayer = player.getDisks();
            board[position.getXCoordinate()][position.getYCoordinate()] = disksPlayer[2];
        }

        return retour;
    }

    /**
     * String version of a Board.
     *
     * @return String version of a Board
     */
    public String toString() {

        String retour = "";

        retour += " _______________________________";

        for(int i = 0 ; i < this.board.length ; i++) {
            retour += "\n| ";

            for(int j = 0 ; j < this.board[i].length ; j++) {
                if(this.board[j][i] == null) {
                    retour += "0 | ";
                }

                else {
                    if(this.board[j][i].getPlayer() == this.game.getPlayer1()) {
                        retour += "1 | ";
                    }

                    else if(this.board[j][i].getPlayer() == this.game.getPlayer2()) {
                        retour += "2 | ";
                    }
                }

            }

            retour += "\n|___|___|___|___|___|___|___|___|";
        }

        return retour;
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