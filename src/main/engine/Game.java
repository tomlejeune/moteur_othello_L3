package engine;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.UUID;
import java.awt.Color;

/**
 * Game is a game of Othello. Every Game is unique and is distinguished by an UUID.
 * It has a Rule, two Players, a State and a Board.
 * It notifies classes that listens to it when a changes occurs.
 *
 * @version 1.0
 */
public class Game implements Cloneable, Serializable {

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
     * Name of the property "positions".
     */
    private final static String POSITIONS_PROPERTY = "Positions";

    /**
     * Name of the property "turn".
     */
    private final static String TURN_PROPERTY = "Turn";

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
     * Positions played in the Game
     */
    private Position[] positions;

    /**
     * Turn of the Game
     */
    private int turn;

    /**
     * Constructs a Game with two Players and a Rule. It also initializes all other attributes.
     *
     * @param id UUID of the Game
     * @param player1 First player of the Game
     * @param player2 Second player of the Game
     * @param rule Rule of the Game
     */
    Game(UUID id, Player player1, Player player2, Rule rule) {
        this.changeSupport = new PropertyChangeSupport(this);

        this.idGame = null;
        this.setUUID(id);
        this.rule = null;
        this.setRule(rule);
        this.player1 = null;
        this.setPlayer1(player1);
        this.player2 = null;
        this.setPlayer2(player2);
        this.currentPlayer = null;
        this.setCurrentPlayer(this.rule.getFirstPlayer(this));

        this.getCurrentPlayer().initializeDisks(this, Color.BLACK);
        this.getOtherPlayer().initializeDisks(this, Color.WHITE);

        this.state = null;
        this.setState(State.INIT);
        this.board = null;
        this.setBoard(this.rule.initializeBoard(this));
        this.positions = null;
        this.setPositions(new Position[64]);
        this.turn = 0;
        this.setTurn(0);

        if(this.rule instanceof OthelloRule) {
            this.setState(State.PLAY);
        }
    }

    /**
     * Constructs a Game with two Players and a Rule. It also initializes all other attributes.
     *
     * @param id UUID of the Game
     * @param player1 First player of the Game
     * @param player2 Second player of the Game
     * @param rule Rule of the Game
     * @param positions Positions played in the Game
     */
    Game(UUID id, Player player1, Player player2, Rule rule, Position[] positions) {
        this.changeSupport = new PropertyChangeSupport(this);

        this.idGame = null;
        this.setUUID(id);
        this.rule = null;
        this.setRule(rule);
        this.player1 = null;
        this.setPlayer1(player1);
        this.player2 = null;
        this.setPlayer2(player2);
        this.currentPlayer = null;
        this.setCurrentPlayer(this.rule.getFirstPlayer(this));

        this.currentPlayer.initializeDisks(this, Color.BLACK);
        this.getOtherPlayer().initializeDisks(this, Color.WHITE);

        this.state = null;
        this.setState(State.INIT);
        this.board = null;
        this.setBoard(this.rule.initializeBoard(this));
        this.positions = null;
        this.setPositions(positions);
        this.turn = 0;
        this.setTurn(this.positions.length);

        if(this.rule instanceof OthelloRule) {
            this.setState(State.PLAY);
        }

        Disk[][] b = new Disk[8][8];

        Disk[] disksPlayer1 = this.player1.getDisks();
        Disk[] disksPlayer2 = this.player2.getDisks();

        for(int i = 0 ; i < this.positions.length ; i++) {
            if((i % 2) == 0) {
                b[this.positions[i].getXCoordinate()][this.positions[i].getYCoordinate()] = disksPlayer1[this.getNbPoints(player1)];
            }

            else {
                b[this.positions[i].getXCoordinate()][this.positions[i].getYCoordinate()] = disksPlayer2[this.getNbPoints(player2)];
            }
        }

        this.board.setBoard(b);
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
     * Sets the Game UUID.
     *
     * @param id The new UUID of the Game
     */
    void setUUID(UUID id) {
        UUID oldId = this.idGame;

        this.idGame = id;
        this.changeSupport.firePropertyChange(ID_GAME_PROPERTY, oldId, this.idGame);
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
     * Sets the Rule of the Game.
     *
     * @param rule The new Rule of the Game
     */
    void setRule(Rule rule) {
        Rule oldRule = this.rule;

        this.rule = rule;
        this.changeSupport.firePropertyChange(RULE_PROPERTY, oldRule, this.rule);
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
     * Sets the first Player of the Game.
     *
     * @param player The new first Player of the Game
     */
    void setPlayer1(Player player) {
        Player oldPlayer = this.player1;

        this.player1 = player;
        this.changeSupport.firePropertyChange(PLAYER_1_PROPERTY, oldPlayer, this.player1);
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
     * Sets the second Player of the Game.
     *
     * @param player The new second Player of the Game
     */
    void setPlayer2(Player player) {
        Player oldPlayer = this.player2;

        this.player2 = player;
        this.changeSupport.firePropertyChange(PLAYER_2_PROPERTY, oldPlayer, this.player2);
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
     * Sets the current player
     *
     * @param player The new current player
     */
    void setCurrentPlayer(Player player) {
        Player oldPlayer = this.currentPlayer;

        if(player == this.getOtherPlayer()) {
            this.currentPlayer = player;

            this.changeSupport.firePropertyChange(CURRENT_PLAYER_PROPERTY, oldPlayer, this.currentPlayer);
        }
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
     * Sets the State of the Game.
     *
     * @param state New state of the Game.
     */
    void setState(State state) {
        State oldState = this.state;

        this.state = state;
        this.changeSupport.firePropertyChange(STATE_PROPERTY, oldState, this.state);
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
     * Sets the Board of the Game.
     *
     * @param board The new Board of the Game
     */
    void setBoard(Board board) {
        Board oldBoard = this.board;

        this.board = board;
        this.changeSupport.firePropertyChange(BOARD_PROPERTY, oldBoard, this.board);
    }

    /**
     * Gets the Positions played in the Game
     *
     * @return The Positions played in the Game
     */
    public Position[] getPositions() {
        return this.positions;
    }

    /**
     * Sets the Positions played in the Game.
     *
     * @param positions The new Positions played in the Game
     */
    void setPositions(Position[] positions) {
        Position[] oldPositions = this.positions;

        this.positions = positions;
        this.changeSupport.firePropertyChange(POSITIONS_PROPERTY, oldPositions, this.positions);
    }

    /**
     * Gets the turn of the Game
     *
     * @return The turn of the Game
     */
    public int getTurn() {
        return this.turn;
    }

    /**
     * Sets the turn of the Game
     *
     * @param turn The new turn of the Game
     */
    void setTurn(int turn) {
        int oldTurn = this.turn;

        this.turn = turn;
        this.changeSupport.firePropertyChange(TURN_PROPERTY, oldTurn, this.turn);
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
        return Counter.getNbPoints(this, player);
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
        boolean first = true;
        Disk[][] b = this.board.getBoard();

        Position[] playablePositions = this.rule.getPlayablePositions(this, player);

        int j;

        for(int i = 0 ; i < playablePositions.length ; i++) {

            //is the position playable ?
            if((currentX == playablePositions[i].getXCoordinate()) && (currentY == playablePositions[i].getYCoordinate())) {
                //nbPoint++; //point of the disk itself

                //North position
                if((currentY != 0) && (b[currentX][currentY-1] != null) && (b[currentX][currentY-1].getPlayer() != player)) {
                    j = 2;

                    while(((currentY-j) > 0) && (b[currentX][currentY-j] != null) && (b[currentX][currentY-j].getPlayer() != player)) {
                        j++;
                    }

                    if(((currentY-j) >= 0) && (b[currentX][currentY-j] != null) && (b[currentX][currentY-j].getPlayer() == player)) {
                        if (first) {
                            nbPoint=j;
                            first =false;
                        } else {
                            nbPoint += j-1;
                        }
                    }
                }

                //South position
                if((currentY != 7) && (b[currentX][currentY+1] != null) && (b[currentX][currentY+1].getPlayer() != player)) {
                    j = 2;

                    while(((currentY+j) < 7) && (b[currentX][currentY+j] != null) && (b[currentX][currentY+j].getPlayer() != player)) {
                        j++;
                    }

                    if (((currentY+j) <= 7) && (b[currentX][currentY+j] != null) && (b[currentX][currentY+j].getPlayer() == player)) {
                        if (first) {
                            nbPoint=j;
                            first = false;
                        } else {
                            nbPoint += j-1;
                        }
                    }
                }

                //West position
                if((currentX != 0) && (b[currentX-1][currentY] != null) && (b[currentX-1][currentY].getPlayer() != player)) {
                    j = 2;

                    while(((currentX-j) > 0) && (b[currentX-j][currentY] != null) && (b[currentX-j][currentY].getPlayer() != player)) {
                        j++;
                    }

                    if (((currentX-j) >= 0) && (b[currentX-j][currentY] != null) && (b[currentX-j][currentY].getPlayer() == player)) {
                        if (first) {
                            nbPoint=j;
                            first = false;
                        } else {
                            nbPoint += j-1;
                        }
                    }
                }

                //East position
                if((currentX != 7) && (b[currentX+1][currentY] != null) && (b[currentX+1][currentY].getPlayer() != player)) {
                    j = 2;

                    while(((currentX+j) < 7) && (b[currentX+j][currentY] != null) && (b[currentX+j][currentY].getPlayer() != player)) {
                        j++;
                    }

                    if(((currentX+j) <= 7) && (b[currentX+j][currentY] != null) && (b[currentX+j][currentY].getPlayer() == player)) {
                        if (first) {
                            nbPoint=j;
                            first = false;
                        } else {
                            nbPoint += j-1;
                        }
                    }
                }

                //South-East position
                if ((currentX != 7) && (currentY != 7) && (b[currentX+1][currentY+1] != null) && (b[currentX+1][currentY+1].getPlayer() != player)) {
                    j = 2;

                    while(((currentX+j) < 7) && ((currentY+j) < 7) && (b[currentX+j][currentY+j] != null) && (b[currentX+j][currentY+j].getPlayer() != player)) {
                        j++;
                    }

                    if(((currentX+j) <= 7) && ((currentY+j) <= 7) && (b[currentX+j][currentY+j] != null) && (b[currentX+j][currentY+j].getPlayer() == player)) {
                        if (first) {
                            nbPoint=j;
                            first = false;
                        } else {
                            nbPoint += j-1;
                        }
                    }
                }

                //North-West position
                if((currentX != 0) && (currentY != 0) && (b[currentX-1][currentY-1] != null) && (b[currentX-1][currentY-1].getPlayer() != player)) {
                    j = 2;

                    while(((currentX-j) > 0) && ((currentY-j) > 0) && (b[currentX-j][currentY-j] != null) && (b[currentX-j][currentY-j].getPlayer() != player)) {
                        j++;
                    }

                    if(((currentX-j) >= 0) && ((currentY-j) >= 0) && (b[currentX-j][currentY-j] != null) && (b[currentX-j][currentY-j].getPlayer() == player)) {
                        if (first) {
                            nbPoint=j;
                            first = false;
                        } else {
                            nbPoint += j-1;
                        }
                    }
                }

                //North-East position
                if((currentX != 7) && (currentY != 0) && (b[currentX+1][currentY-1] != null) && (b[currentX+1][currentY-1].getPlayer() != player)) {
                    j = 2;

                    while(((currentX+j) < 7) && ((currentY-j) > 0) && (b[currentX+j][currentY-j] != null) && (b[currentX+j][currentY-j].getPlayer() != player)) {
                        j++;
                    }

                    if(((currentX+j) <= 7) && ((currentY-j) >= 0) && (b[currentX+j][currentY-j] != null) && (b[currentX+j][currentY-j].getPlayer() == player)) {
                        if (first) {
                            nbPoint=j;
                            first = false;
                        } else {
                            nbPoint += j-1;
                        }
                    }
                }

                //South-West position
                if((currentX != 0) && (currentY != 7) && (b[currentX-1][currentY+1] != null) && (b[currentX-1][currentY+1].getPlayer() != player)) {
                    j = 2;

                    while(((currentX-j) > 0) && ((currentY+j) < 7) && (b[currentX-j][currentY+j] != null) && (b[currentX-j][currentY+j].getPlayer() != player)) {
                        j++;
                    }

                    if(((currentX-j) >= 0) && ((currentY+j) <= 7) && (b[currentX-j][currentY+j] != null) && (b[currentX-j][currentY+j].getPlayer() == player)) {
                        if (first) {
                            nbPoint=j;
                        } else {
                            nbPoint += j-1;
                        }
                    }
                }

                break;
            }
        }

        return nbPoint;
    }

    /**
     * The given player plays at the given position.
     *
     * @param player Player that plays
     * @param position Position of where the Player wants to play
     * @throws PlayException
     */
    public void play(Player player, Position position) throws PlayException {
        if(player == this.getCurrentPlayer()) {
            if(this.state == State.PLAY) {
                if(player.counterOfDisks() > 0) {
                    if((board.getPositions(this, this.player1).length + board.getPositions(this, this.player2).length) < 64) {
                        player.setCanPlay(false);

                        if(this.getPlayablePositions(player).length > 0) {
                            Position[] playablePositions = this.getPlayablePositions(player);
                            int i = 0;

                            while(i < playablePositions.length) {
                                if((position.getXCoordinate() == playablePositions[i].getXCoordinate()) && (position.getYCoordinate() == playablePositions[i].getYCoordinate())) {
                                    player.setCanPlay(true);
                                    this.board.placeDisk(player, position);

                                    if(this.rule instanceof ReversiRule) {
                                        player.removeADisk();
                                    }

                                    this.rule.turnDisks(this, position);

                                    if(getPositions(this.getOtherPlayer()).length == 0) {
                                        this.state = State.END;
                                    }

                                    this.changePlayer();
                                }

                                i++;
                            }

                            if(!player.getCanPlay()) {
                                throw new PlayException("Not a playable position");
                            }

                        }

                        else {
                            if((this.rule instanceof ReversiRule) || !getOtherPlayer().getCanPlay()) {
                                this.state = State.END;
                            }

                            this.changePlayer();
                        }
                    }

                    else {
                        this.state = State.END;
                    }
                }

                else {
                    this.changePlayer();
                }
            }

            else if ((this.state == State.INIT) && (this.rule instanceof ReversiRule)) {
                Position[] playablePositions = this.getPlayablePositions(player);
                player.setCanPlay(false);

                if(playablePositions.length > 0) {
                    int j=0;

                    while(j < playablePositions.length) {
                        if((position.getXCoordinate() == playablePositions[j].getXCoordinate()) && (position.getYCoordinate() == playablePositions[j].getYCoordinate())) {
                            player.setCanPlay(true);
                            this.board.placeDisk(player, position);
                            player.removeADisk();
                            this.changePlayer();

                            if (playablePositions.length == 1) {
                                this.state = State.PLAY;
                            }
                        }

                        j++;
                    }

                    if(!player.getCanPlay()) {
                        throw new PlayException("Not a playable position");
                    }
                }
            }

            else {
                throw new PlayException("You can't play, the State of the Game is not PLAY or INIT");
            }
        }

        else{
            throw new PlayException("Not the turn of this player");
        }
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
     * Private method which changes the current Player of the Game
     */
    private void changePlayer() {
        if(this.currentPlayer == this.player1) {
            this.setCurrentPlayer(this.player2);
        }

        else {
            this.setCurrentPlayer(this.player1);
        }
    }

    /**
     * Private method which gets the other Player of the Game
     */
    Player getOtherPlayer() {
        if(this.currentPlayer == this.player1) {
            return player2;
        }

        else {
            return player1;
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

    /**
     * Clones a Game
     *
     * @return Clone of a Game
     */
    public Object clone() {
        Object o = null;

        try {
            o = super.clone();
        }

        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return o;
    }
}