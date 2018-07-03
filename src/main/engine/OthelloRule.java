package engine;

import java.util.ArrayList;

/**
 * OthelloRule is a specific Rule ...
 */
public class OthelloRule extends Rule {

    /**
     * Constructs a OthelloRule
     */
    OthelloRule() {

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

        Disk[] disksPlayer1 = game.getPlayer1().getDisks();
        Disk[] disksPlayer2 = game.getPlayer2().getDisks();

        board[3][3] = disksPlayer2[0];
        board[3][4] = disksPlayer1[0];
        board[4][3] = disksPlayer1[1];
        board[4][4] = disksPlayer2[1];

        retour.setBoard(board);

        Position[] positions = new Position[70];

        positions[game.getTurn()] = new Position(3, 4);
        game.setTurn(game.getTurn()+1);
        positions[game.getTurn()] = new Position(3, 3);
        game.setTurn(game.getTurn()+1);
        positions[game.getTurn()] = new Position(4, 3);
        game.setTurn(game.getTurn()+1);
        positions[game.getTurn()] = new Position(4, 4);
        game.setTurn(game.getTurn()+1);

        game.setPositions(positions);

        return retour;
    }

    /**
     * Gets the first Player to play in the given Game.
     *
     * @param game Game played
     * @return The first Player to play in the given Game
     */
    Player getFirstPlayer(Game game) {
        return game.getPlayer1();
    }



    /**
     * Gets the Player that won the given Game.
     *
     * @param game Game played
     * @return The Player that won the the given Game
     */
    Player getWinner(Game game) {
        Player retour = null;

        if(game.getPlayer1().getForfeit()) {
            retour = game.getPlayer2();
        }

        else if(game.getPlayer2().getForfeit()) {
            retour = game.getPlayer1();
        }

        else {
            if(Counter.getNbPoints(game, game.getPlayer1()) > Counter.getNbPoints(game, game.getPlayer2())) {
                retour = game.getPlayer1();
            }

            else {
                retour = game.getPlayer2();
            }
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

        if(game.getPlayer1().getForfeit()) {
            retour = game.getPlayer1();
        }

        else if(game.getPlayer2().getForfeit()) {
            retour = game.getPlayer2();
        }

        else {
            if(Counter.getNbPoints(game, game.getPlayer1()) > Counter.getNbPoints(game, game.getPlayer2())) {
                retour = game.getPlayer2();
            }

            else {
                retour = game.getPlayer1();
            }
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

        if(game.getPlayer1().getForfeit()) {
            retour = false;
        }

        else if(game.getPlayer2().getForfeit()) {
            retour = false;
        }

        else {
            if(Counter.getNbPoints(game, game.getPlayer1()) == Counter.getNbPoints(game, game.getPlayer2())) {
                retour = true;
            }
        }

        return retour;
    }

    /**
     * Check if the coordinates given is a possible move for the current player of the game
     * @param x coordinate x
     * @param y coordinate y
     * @param turn current player
     * @param board board of the game
     * @return true if x,y is a possible move
     */
    private boolean legalMove(int x, int y, final Player turn, Disk[][] board) {
        boolean ret = false;
        if(board[x][y] == null)ret = true;
        return ret && (
                canFlip(x, y, 0, -1, turn, board) ||
                        canFlip(x, y, -1, -1, turn, board) ||
                        canFlip(x, y, -1, 0, turn, board) ||
                        canFlip(x, y, -1, 1, turn, board) ||
                        canFlip(x, y, 0, 1, turn, board) ||
                        canFlip(x, y, 1, 1, turn, board) ||
                        canFlip(x, y, 1, 0, turn, board) ||
                        canFlip(x, y, 1, -1, turn, board)
        );
    }

    /**
     * Check if you can flip disks in the given direction
     * @param cellX the coordinate x
     * @param cellY the coordinate y
     * @param directionX the x direction we are checking
     * @param directionY the y direction we are checking
     * @param turn the current player
     * @param board the board of the game
     * @return true if there is one of the disk of the current player forward in the direction
     */
    private boolean canFlip(final int cellX, final int cellY, final int directionX, final int directionY, final Player turn, Disk[][] board) {
        int x = cellX + directionX;
        int y = cellY + directionY;
        boolean first = true;

        while (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] != null) {
            if (board[x][y].getPlayer() == turn) {
                return !first;
            }
            else {
                first = false;
            }

            x += directionX;
            y += directionY;
        }
        return false;
    }

    /**
     * Flip the disks between the given position and a disk of the current player
     * @param cellX the x coordinate
     * @param cellY the y coordinate
     * @param directionX the x direction we are checking
     * @param directionY the y direction we are checking
     * @param turn the current player
     * @param board the board of the game
     * @param currentPlayerDisks the current player disks list
     * @param game the current game
     */
    private void flip(int cellX, int cellY, int directionX, int directionY, Player turn, Disk[][] board, Disk[] currentPlayerDisks, Game game) {
        if (canFlip(cellX, cellY, directionX, directionY, turn, board)) {
            int x = cellX + directionX;
            int y = cellY + directionY;
            while (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y].getPlayer() != turn) {
                board[x][y] = currentPlayerDisks[game.getNbPoints(turn)];
                x += directionX;
                y += directionY;
            }
        }
    }

    /**
     * Gets the Positions where the given Player can play in the given Game.
     * @param game Game played
     * @param player Player that plays
     * @return Array of the Positions where the given Player can play in the given Game
     */
    Position[] getPlayablePositions(Game game, Player player) {
        ArrayList<Position> playablePositions = new ArrayList<>();
        Disk[][] board = game.getBoard().getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (legalMove(i, j, player, board)) playablePositions.add(new Position(i, j));
            }
        }
        Position[] retour = new Position[playablePositions.size()];
        for (int i = 0; i < retour.length; i++) {
            retour[i] = playablePositions.get(i);
        }
        return retour;
    }

    /**
     * Turns the Disks on the Board of the given Game.
     *
     * @param game Game played
     * @param lastPositionPlayed Last Position played at the given Game
     */
    void turnDisks(Game game, Position lastPositionPlayed) {
        Board b = game.getBoard();
        Disk[][] boardDisks = b.getBoard();
        int cellX = lastPositionPlayed.getXCoordinate();
        int cellY = lastPositionPlayed.getYCoordinate();

        Disk[] currentPlayerDisks;
        Player currentPlayer;
        if (b.getDisk(lastPositionPlayed).getPlayer() == game.getPlayer1()) {
            currentPlayer = game.getPlayer1();
            currentPlayerDisks = currentPlayer.getDisks();
        } else {
            currentPlayer = game.getPlayer2();
            currentPlayerDisks = currentPlayer.getDisks();
        }

        flip(cellX, cellY, 0, -1, currentPlayer, boardDisks, currentPlayerDisks, game);
        flip(cellX, cellY, -1, -1, currentPlayer, boardDisks, currentPlayerDisks, game);
        flip(cellX, cellY, -1, 0, currentPlayer, boardDisks, currentPlayerDisks, game);
        flip(cellX, cellY, -1, 1, currentPlayer, boardDisks, currentPlayerDisks, game);
        flip(cellX, cellY, 0, 1, currentPlayer, boardDisks, currentPlayerDisks, game);
        flip(cellX, cellY, 1, 1, currentPlayer, boardDisks, currentPlayerDisks, game);
        flip(cellX, cellY, 1, 0, currentPlayer, boardDisks, currentPlayerDisks, game);
        flip(cellX, cellY, 1, -1, currentPlayer, boardDisks, currentPlayerDisks, game);

        game.getBoard().setBoard(boardDisks);
    }


    /**
     * Private method which checks if the given array of Positions contains the given Position
     *
     * @param positions The array of Positions
     * @param position The Position
     * @return true if the array Positions contains the given Position
     */
    private boolean contains(Position[] positions, Position position) {
        int i = 0;

        while((i < positions.length) && (positions[i] != null)) {
            if (positions[i] == position) {
                return true;
            }

            i++;
        }

        return false;
    }
}
