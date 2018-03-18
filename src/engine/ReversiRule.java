package engine;

/**
 * ReversiRule is specific Rule ...
 */
public class ReversiRule extends Rule {

    /**
     * Constructs a ReversiRule
     */
    ReversiRule() {

    }

    /**
     * Initializes the Board at the beginning of the given game
     *
     * @param game Game played
     */
    Board initializeBoard(Game game) {
        Board retour = new Board(game);

        Disk[][] board = retour.getBoard();

        for(int i = 0 ; i < board.length ; i++) {
            for(int j = 0 ; j < board[i].length ; j++) {
                board[i][j] = null;
            }
        }

        retour.setBoard(board);

        return retour;
    }

    /**
     * Gets the first Player to play in the given Game.
     *
     * @param game Game played
     * @return The first Player to play in the given Game
     */
    Player getFirstPlayer(Game game) {
        return null;
    }

    /**
     * Gets the Positions where the given Player can play in the given Game.
     *
     * @param game   Game played
     * @param player Player that plays
     * @return Array of the Positions where the given Player can play in the given Game
     */
    Position[] getPlayablePositions(Game game, Player player) {
        return new Position[0];
    }

    /**
     * Turns the Disks on the Board of the given Game.
     *
     * @param game Game played
     */
    void turnDisks(Game game) {

    }

    /**
     * Gets the Player that won the given Game.
     *
     * @param game Game played
     * @return The Player that won the the given Game
     */
    Player getWinner(Game game) {
        Player retour = null;

        if(Counter.getNbPoint(game, game.getPlayer1()) > Counter.getNbPoint(game, game.getPlayer2())) {
            retour = game.getPlayer1();
        }

        else {
            retour = game.getPlayer2();
        }

        return retour;
    }

    /**
     * Gets the Player that lost the given Game.
     *
     * @param game Game played
     * @return The Player that lost the given Game
     */
    Player getLoser(Game game) {
        Player retour = null;

        if(Counter.getNbPoint(game, game.getPlayer1()) > Counter.getNbPoint(game, game.getPlayer2())) {
            retour = game.getPlayer2();
        }

        else {
            retour = game.getPlayer1();
        }

        return retour;
    }

    /**
     * Returns true if the given Game is draw.
     *
     * @param game
     * @return true if the given Game is draw
     */
    boolean isDraw(Game game) {
        boolean retour = false;

        if(Counter.getNbPoint(game, game.getPlayer1()) == Counter.getNbPoint(game, game.getPlayer2())) {
            retour = true;
        }

        return false;
    }
}
