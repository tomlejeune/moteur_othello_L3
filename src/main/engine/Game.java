package engine;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.UUID;
import java.awt.Color;

/**
 * Game is a game of Othello. Every Game is unique and is distinguished by an UUID.
 * It has a Rule, two Players, a State and a Board.
 * It notifies classes that listens to it when a changes occurs.
 *
 * @version 1.0
 */
public class Game {

    /**
     * Name of the property "idGame".
     */
    private final static String ID_GAME_PROPERTY = "IdGame";

    /**
     * Name of the property "rule".
     */
    private final static String RULE_PROPERTY = "Rule";

    /**
     * Name of the property "player1".
     */
    private final static String PLAYER_1_PROPERTY = "Player1";

    /**
     * Name of the property "player2".
     */
    private final static String PLAYER_2_PROPERTY = "Player2";

    /**
     * Name of the property "currentPlayer".
     */
    private final static String CURRENT_PLAYER_PROPERTY = "CurrentPlayer";

    /**
     * Name of the property "state".
     */
    private final static String STATE_PROPERTY = "State";

    /**
     * Name of the property "board".
     */
    private final static String BOARD_PROPERTY = "Board";

    /**
     * Listener support.
     */
    private PropertyChangeSupport changeSupport;

    /**
     * UUID of a Game which makes it unique.
     */
    private UUID idGame;

    /**
     * Rule of a Game
     */
    private Rule rule;

    /**
     * First Player of a Game
     */
    private Player player1;

    /**
     * Second Player of a Game
     */
    private Player player2;

    /**
     * Current player of a Game
     */
    private Player currentPlayer;

    /**
     * State of a Game
     */
    private State state;

    /**
     * Board of a Game
     */
    private Board board;

    /**
     * Constructs a Game with two Players and a Rule. It also initializes all other attributes.
     *
     * @param player1 First player of the Game
     * @param player2 Second player of the Game
     * @param rule Rule of the Game
     */
    Game(Player player1, Player player2, Rule rule) {
        this.changeSupport = new PropertyChangeSupport(this);

        //this.idGame = ;
        this.rule = rule;

        this.player1 = player1;
        this.player2 = player2;
        this.player1.initializeDisks(this, Color.WHITE);
        this.player2.initializeDisks(this, Color.BLACK);

        this.currentPlayer = this.rule.getFirstPlayer(this);
        this.state = State.INIT;
        this.board = this.rule.initializeBoard(this);
    }

    /**
     * Gets the Game UUID.
     *
     * @return The Game UUID
     */
    public UUID getUUID() {
        return this.idGame;
    }

    /**
     * Gets the Rule of the Game.
     *
     * @return The Rule of the Game
     */
    public Rule getRule() {
        return this.rule;
    }

    /**
     * Gets the first Player of the Game.
     *
     * @return The first Player of the Game
     */
    public Player getPlayer1() {
        return this.player1;
    }

    /**
     * Gets the second Player of the Game.
     *
     * @return The second Player of the Game
     */
    public Player getPlayer2() {
        return this.player2;
    }

    /**
     * Gets the current Player of the Game.
     *
     * @return The current Player of the Game
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Gets the State of the Game.
     *
     * @return The State of the Game
     */
    public State getState() {
        return this.state;
    }

    /**
     * Gets the Board of the Game.
     *
     * @return The Board of the Game
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Gets the Positions where the given Player can play by asking the Rule.
     *
     * @param player Player that plays
     * @return Array of the Positions where the given Player can play
     */
    public Position[] getPlayablePositions(Player player) {
        return this.rule.getPlayablePositions(this, player);
    }

    /**
     * Gets all the Positions where a Player placed his Disks by asking the Board.
     *
     * @param player The Player that plays
     * @return Array of Positions where a Player placed his Disks
     */
    public Position[] getPositions(Player player) {
        return this.board.getPositions(this, player);
    }

    /**
     * Gets the number of points of the given Player by asking the Counter.
     *
     * @param player Player that plays
     * @return The number of points of the given Player
     */
    public int getNbPoints(Player player) {
        return Counter.getNbPoint(this, player);
    }

    /**
     * Returns the number of point the Player would have
     * if he plays the given position
     *
     * @param position The Position the player wants to play
     * @param player The player who plays
     * @return The number of point he would have
     */
    public int getNbPointsIfPlayed(Position position, Player player) {
        int nbPoint = 0;
        int currentX = position.getXCoordinate();
        int currentY = position.getYCoordinate();

        Position[] playablePositions = this.rule.getPlayablePositions(this, player);

        /*for (int i=0 ; i < playablePositions.length ; i++) {

            //is the position playable ?
            if (position == playablePositions[i]) {
                nbPoint++; //point of the disk itself

                //North position
                if ((currentY != 0) && (board[currentX][currentY-1].getPlayer() != player) && (board[currentX][currentY-1] != null)) {
                    j = 2;
                    while (((currentY-j) > 0) && (board[currentX][currentY-j] != null) && (board[currentX][currentY-j].getPlayer() != player)) {
                        j++;
                    }
                    if (((currentY-j) >= 0) && (board[currentX][currentY-j].getPlayer() == player)) {
                        nbPoint=j-1;
                    }
                }

                //South position
                if ((currentY != 7) && (board[currentX][currentY+1].getPlayer() != player) && (board[currentX][currentY+1] != null)) {
                    j = 2;
                    while (((currentY+j) < 7) && (board[currentX][currentY+j] != null) && (board[currentX][currentY+j].getPlayer() != player)) {
                        j++;
                    }
                    if (((currentY+j) <= 7) && (board[currentX][currentY+j].getPlayer() == player)) {
                        nbPoint=j-1;
                    }
                }

                //West position
                if ((currentX != 0) && (board[currentX-1][currentY].getPlayer() != player) && (board[currentX-1][currentY] != null)) {
                    j = 2;
                    while (((currentX-j) > 0) && (board[currentX-j][currentY] != null) && (board[currentX-j][currentY].getPlayer() != player)) {
                        j++;
                    }
                    if (((currentX-j) >= 0) && (board[currentX-j][currentY].getPlayer() == player)) {
                        nbPoint=j-1;
                    }
                }

                //East position
                if ((currentX != 7) && (board[currentX+1][currentY].getPlayer() != player) && (board[currentX+1][currentY] != null)) {
                    j = 2;
                    while (((currentX+j) < 7) && (board[currentX+j][currentY] != null) && (board[currentX+j][currentY].getPlayer() != player)) {
                        j++;
                    }
                    if (((currentX+j) <=7) && (board[currentX+j][currentY].getPlayer() == player)) {
                        nbPoint=j-1;
                    }
                }

                //South-East position
                if ((currentX != 7) && (currentY != 7) && (board[currentX+1][currentY+1].getPlayer() != player) && (board[currentX+1][currentY+1] != null)) {
                    j = 2;
                    while (((currentX+j) < 7) && ((currentY+j) < 7) && (board[currentX+j][currentY+j] != null) && (board[currentX+j][currentY+j].getPlayer() != player)) {
                        j++;
                    }
                    if (((currentX+j) <= 7) && ((currentY+j) <= 7) && (board[currentX+j][currentY+j].getPlayer() == player)) {
                        nbPoint=j-1;
                    }
                }

                //Nord-West position
                if ((currentX != 0) && (currentY != 0) && (board[currentX-1][currentY-1].getPlayer() != player) && (board[currentX-1][currentY-1] != null)) {
                    j = 2;
                    while (((currentX-j) > 0) && ((currentY-j) > 0) && (board[currentX-j][currentY-j] != null) && (board[currentX-j][currentY-j].getPlayer() != player)) {
                        j++;
                    }
                    if (((currentX-j) >= 0) && ((currentY-j) >= 0) && (board[currentX-j][currentY-j].getPlayer() == player)) {
                        nbPoint=j-1;
                    }
                }

                //North-East position
                if ((currentX != 7) && (currentY != 0) && (board[currentX-1][currentY+1].getPlayer() != player) && (board[currentX-1][currentY+1] != null)) {
                    j = 2;
                    while (((currentX-j) > 0) && ((currentY+j) < 7) && (board[currentX-j][currentY+j] != null) && (board[currentX-j][currentY+j].getPlayer() != player)) {
                        j++;
                    }
                    if (((currentX-j) >= 0) && ((currentY+j) <= 7) && (board[currentX-j][currentY+j].getPlayer() == player)) {
                        nbPoint=j-1;
                    }
                }

                //South-West position
                if ((currentX != 0) && (currentY != 7) && (board[currentX+1][currentY-1].getPlayer() != player) && (board[currentX+1][currentY-1] != null)) {
                    j = 2;
                    while (((currentX+j) < 7) && ((currentY-j) > 0) && (board[currentX+j][currentY-j] != null) && (board[currentX+j][currentY-j].getPlayer() != player)) {
                        j++;
                    }
                    if (((currentX+j) <= 7) && ((currentY-j) >= 0) && (board[currentX+j][currentY-j].getPlayer() == player)) {
                        nbPoint=j-1;
                    }
                }
                return nbPoint;
            }
        }*/

        return nbPoint;
    }

    /**
     * The given player plays at the given position.
     *
     * @param player Player that plays
     * @param position Position of where the Player wants to play
     */
    public void play(Player player, Position position) {
        this.board.placeDisk(player, position);
        this.rule.turnDisks(this, position);
        this.changePlayer();

        /*if (this.state == State.PLAY ) {
            if ((board.getPositions(this, this.player1).length + board.getPositions(this, this.player2).length) < 64) {
                if (this.getPlayablePositions(player).length != 0) {
                    player.canPlay = true;
                    this.board.placeDisk(player, position);
                    this.rule.turnDisks(this, position);
                    this.changePlayer();
                } else if (!getOtherPlayer().canPlay){
                    this.state = State.END;
                } else {
                    player.canPlay = false;
                }
            } else {
                this.state = State.END;
            }
        }*/
    }

    /**
     * Gets the Player that won the Game.
     *
     * @return The Player that won the Game
     */
    public Player getWinner() {
        return this.rule.getWinner(this);
    }

    /**
     * Gets the Player that lost the Game.
     *
     * @return The Player that lost the Game
     */
    public Player getLoser() {
        return this.rule.getLoser(this);
    }

    /**
     * Returns true if the Game is draw.
     *
     * @return true if the Game is draw
     */
    public boolean isDraw() {
        return this.rule.isDraw(this);
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

    /**
     * Private method which changes the current Player of the Game
     */
    private void changePlayer() {
        if (this.currentPlayer == this.player1) {
            currentPlayer = player2;
        }

        else {
            currentPlayer = player1;
        }
    }
}