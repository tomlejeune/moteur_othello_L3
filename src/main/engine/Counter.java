package engine;

/**
 * Counter provides static methods which counts things.
 *
 * @version 1.0
 */
public abstract class Counter {

    /**
     * Static method which counts the number of points of the given Player in the given Game.
     *
     * @param game Game played
     * @param player Player that plays
     * @return The number of points of the given Player in the given Game
     */
    static int getNbPoints(Game game, Player player) {
        int retour = 0;

        Disk[][] board = game.getBoard().getBoard();

        for(int i = 0 ; i < board.length ; i++) {
            for(int j = 0 ; j < board[i].length ; j++) {
                if(board[i][j] != null) {
                    if(board[i][j].getPlayer() == player) {
                        retour++;
                    }
                }
            }
        }

        return retour;
    }
}