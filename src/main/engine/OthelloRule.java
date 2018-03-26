package engine;

import java.awt.Color;

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

        board[3][3] = disksPlayer1[0];
        board[3][4] = disksPlayer2[0];
        board[4][3] = disksPlayer2[1];
        board[4][4] = disksPlayer1[1];

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
        double randomNumber = Math.random();

        if (randomNumber < 0.5) {
            return game.getPlayer1();
        }

        else {
            return game.getPlayer2();
        }
    }

    /**
     * Gets the Positions where the given Player can play in the given Game.
     *
     * @param game Game played
     * @param player Player that plays
     * @return Array of the Positions where the given Player can play in the given Game
     */
    Position[] getPlayablePositions(Game game, Player player) {
        Position[] playablePositions = new Position[64];
        Position[] playerPositions = game.getPositions(player);
        Disk board[][] = game.getBoard().getBoard();

        int index = 0;

        for(int i = 0 ; i < playerPositions.length ; i++) {
            //Coordonnées du disk courant du joueur courant
            int currentX = playerPositions[i].getXCoordinate();
            int currentY = playerPositions[i].getYCoordinate();

            //on vérifie la position sud du disk en cours
            //on regarde si la position n'est pas nulle et si le disk appartient à l'autre joueur
            if((board[currentX][currentY+1] != null) && (board[currentX][currentY+1].getPlayer() != player)) {
                int j = 2;

                //on regarde jusqu'ou va l'alignement des disk de l'autre jouerur en prenant garde a ne pas dépasser du board
                while(((currentY+j) < 8) && (board[currentX][currentY+j] != null) && (board[currentX][currentY+j].getPlayer() != player)) {
                    j++;
                }

                //ajout de la position si on est toujours dans le board, qu'au bout il n'y a pas un disk appartenant au joueur courant
                if(((currentY+j) < 8) && (board[currentX][currentY+j] == null)) {
                    Position newPosition = new Position(currentX, (currentY+j));

                    //on regarde si la position jouable n'existe pas déjà
                    if(!contains(playablePositions, newPosition)) {
                        playablePositions[index] = newPosition;
                        index++;
                    }
                }
            }

            //on verifie la position nord du disk en cours
            if((board[currentX][currentY-1] != null) && (board[currentX][currentY-1].getPlayer() != player)) {
                int j = -2;

                while(((currentY+j) >= 0) && (board[currentX][currentY+j] != null) && (board[currentX][currentY+j].getPlayer() != player)) {
                    j--;
                }

                if((board[currentX][currentY+j] == null) && ((currentY+j) >= 0)) {
                    Position newPosition = new Position(currentX,(currentY+j));

                    if(!contains(playablePositions, newPosition)) {
                        playablePositions[index] = newPosition;
                        index++;
                    }
                }
            }

            //on verifie la position est du disk en cours
            if((board[currentX+1][currentY] != null) && (board[currentX+1][currentY].getPlayer() != player)) {
                int j = 2;

                while(((currentX+j) < 8) && (board[currentX+j][currentY] != null) && (board[currentX+j][currentY].getPlayer() != player)) {
                    System.out.println("j : "+j);
                    j++;
                }

                if((board[currentX+j][currentY] == null) && ((currentX+j) < 8)) {
                    Position newPosition = new Position((currentX+j),currentY);

                    if(!contains(playablePositions, newPosition)) {
                        playablePositions[index] = newPosition;
                        index++;
                    }
                }
            }

            //on verifie la position ouest du disk en cours
            if((board[currentX-1][currentY] != null) && (board[currentX-1][currentY].getPlayer() != player)) {
                int j = -2;

                while(((currentX+j) >= 0) && (board[currentX+j][currentY] != null) && (board[currentX+j][currentY].getPlayer() != player)) {
                    j--;
                }

                if((board[currentX+j][currentY] == null) && ((currentX+j) >= 0)) {
                    Position newPosition = new Position((currentX+j),currentY);

                    if(!contains(playablePositions, newPosition)) {
                        playablePositions[index] = newPosition;
                        index++;
                    }
                }
            }

            //on verifie la position sud-est du disk en cours
            if((board[currentX+1][currentY+1] != null) && (board[currentX+1][currentY+1].getPlayer() != player)) {
                int j = 2;

                while(((currentX+j) < 8) && ((currentY+j) < 8) && (board[currentX+j][currentY+j] != null) && (board[currentX+j][currentY+j].getPlayer() != player)) {
                    j++;
                }

                if((board[currentX+j][currentY+j] == null) && ((currentX+j) < 8) && ((currentY+j) < 8)) {
                    Position newPosition = new Position((currentX+j),(currentY+j));

                    if(!contains(playablePositions, newPosition)) {
                        playablePositions[index] = newPosition;
                        index++;
                    }
                }
            }

            //on verifie la position nord-ouest du disk en cours
            if((board[currentX-1][currentY-1] != null) && (board[currentX-1][currentY-1].getPlayer() != player)) {
                int j = -2;

                while(((currentX + j) >= 0) && ((currentY + j) >= 0) && (board[currentX+j][currentY+j] != null) && (board[currentX+j][currentY+j].getPlayer() != player)) {
                    j--;
                }

                if((board[currentX+j][currentY+j] == null) && ((currentX + j) >= 0) && ((currentY + j) >= 0)) {
                    Position newPosition = new Position((currentX+j),(currentY + j));

                    if (!contains(playablePositions, newPosition)) {
                        playablePositions[index] = newPosition;
                        index++;
                    }
                }
            }

            //on verifie la position nord-est du disk en cours
            if((board[currentX+1][currentY-1] != null) && (board[currentX+1][currentY-1].getPlayer() != player)) {
                int j = 2;

                while(((currentX+j) < 8) && ((currentY-j) >= 0) && (board[currentX+j][currentY-j] != null) && (board[currentX+j][currentY-j].getPlayer() != player)) {
                    j++;
                }

                if((board[currentX+j][currentY-j] == null) && ((currentX+j) < 8) && ((currentY-j) >= 0)) {
                    Position newPosition = new Position((currentX+j),(currentY-j));

                    if(!contains(playablePositions, newPosition)) {
                        playablePositions[index] = newPosition;
                        index++;
                    }
                }
            }

            //on verifie la position sud-ouest du disk en cours
            if((board[currentX-1][currentY+1] != null) && (board[currentX-1][currentY+1].getPlayer() != player)) {
                int j = 2;

                while(((currentX-j) >= 0) && ((currentY+j) < 8) && (board[currentX-j][currentY+j] != null) && (board[currentX-j][currentY+j].getPlayer() != player)) {
                    j++;
                }

                if(((currentX-j) >= 0) && (board[currentX-j][currentY+j] == null) && ((currentY+j) < 8)) {
                    Position newPosition = new Position((currentX-j),(currentY + j));

                    if(!contains(playablePositions, newPosition)) {
                        playablePositions[index] = newPosition;
                        index++;
                    }
                }
            }
        }

        index = 0;

        //on regarde le nombre d'éléments non null de ArrayToFill
        for(int i = 0 ; i < 64 ; i++) {
            if(playablePositions[i] != null) {
                index++;
            }
        }

        Position[] retour = new Position[index];

        for(int i = 0 ; i < retour.length ; i++) {
            retour[i] = playablePositions[i];
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
        int x = lastPositionPlayed.getXCoordinate();
        int y = lastPositionPlayed.getYCoordinate();
        int lower;
        int diff;

        Color c = b.getDisk(lastPositionPlayed).getColor();
        Position posTest = new Position(x,y);

        // Vérification droite
        if (x < 7) {
            posTest.setXCoordinate(x+1);
            if (b.getDisk(posTest) != null) {
                if (!(b.getDisk(posTest).getColor() == c)) {
                    posTest.setXCoordinate(x);
                    for (int i = x ; i < 7 ; i++) {
                        posTest.setXCoordinate(posTest.getXCoordinate()+1);
                        if (b.getDisk(posTest) != null) {
                            if (b.getDisk(posTest).getColor() == c) {
                                posTest.setXCoordinate(x);
                                for (int j = x ; j < i ; j++) {
                                    posTest.setXCoordinate(posTest.getXCoordinate()+1);
                                    b.getDisk(posTest).setColor(c);
                                }
                                break; // sortir du for
                            }
                        }
                    }
                }
            }
            posTest.setXCoordinate(x);
            posTest.setYCoordinate(y);
        }

        // Vérification gauche
        if (x > 0) {
            posTest.setXCoordinate(x-1);
            if (b.getDisk(posTest) != null) {
                if (!(b.getDisk(posTest).getColor() == c)) {
                    posTest.setXCoordinate(x);
                    for (int i = x ; i > 0 ; i--) {
                        posTest.setXCoordinate(posTest.getXCoordinate()-1);
                        if (b.getDisk(posTest) != null) {
                            if (b.getDisk(posTest).getColor() == c) {
                                posTest.setXCoordinate(x);
                                for (int j = x ; j > i ; j--) {
                                    posTest.setXCoordinate(posTest.getXCoordinate()-1);
                                    b.getDisk(posTest).setColor(c);
                                }
                                break; // sortir du for
                            }
                        }
                    }
                }
            }
            posTest.setXCoordinate(x);
            posTest.setYCoordinate(y);
        }

        // Vérification haut
        if (y > 0) {
            posTest.setYCoordinate(y-1);
            if (b.getDisk(posTest) != null) {
                if (!(b.getDisk(posTest).getColor() == c)) {
                    posTest.setYCoordinate(y);
                    for (int i = y ; i > 0 ; i--) {
                        posTest.setYCoordinate(posTest.getYCoordinate()-1);
                        if (b.getDisk(posTest) != null) {
                            if (b.getDisk(posTest).getColor() == c) {
                                posTest.setYCoordinate(y);
                                for (int j = y ; j > i ; j--) {
                                    posTest.setYCoordinate(posTest.getYCoordinate()-1);
                                    b.getDisk(posTest).setColor(c);
                                }
                                break; // sortir du for
                            }
                        }
                    }
                }
            }
            posTest.setXCoordinate(x);
            posTest.setYCoordinate(y);
        }

        // Vérification bas
        if (y < 7) {
            posTest.setYCoordinate(y+1);
            if (b.getDisk(posTest) != null) {
                if (!(b.getDisk(posTest).getColor() == c)) {
                    posTest.setYCoordinate(y);
                    for (int i = y ; i < 7 ; i++) {
                        posTest.setYCoordinate(posTest.getYCoordinate()+1);
                        if (b.getDisk(posTest) != null) {
                            if (b.getDisk(posTest).getColor() == c) {
                                posTest.setYCoordinate(y);
                                for (int j = y ; j < i ; j++) {
                                    posTest.setYCoordinate(posTest.getYCoordinate()+1);
                                    b.getDisk(posTest).setColor(c);
                                }
                                break; // sortir du for
                            }
                        }
                    }
                }
            }
            posTest.setXCoordinate(x);
            posTest.setYCoordinate(y);
        }

        // Vérification droite bas
        if (x < 7 && y < 7) {
            posTest.setXCoordinate(x+1);
            posTest.setYCoordinate(y+1);
            if (b.getDisk(posTest) != null) {
                if (!(b.getDisk(posTest).getColor() == c)) {
                    posTest.setXCoordinate(x);
                    posTest.setYCoordinate(y);
                    if (7 - x > 7 - y) {
                        lower = y;
                    } else {
                        lower = x;
                    }
                    for (int i = lower ; i < 7 ; i++) {
                        posTest.setXCoordinate(posTest.getXCoordinate()+1);
                        posTest.setYCoordinate(posTest.getYCoordinate()+1);
                        if (b.getDisk(posTest) != null) {
                            if (b.getDisk(posTest).getColor() == c) {
                                posTest.setXCoordinate(x);
                                posTest.setYCoordinate(y);
                                for (int j = lower ; j < i ; j++) {
                                    posTest.setXCoordinate(posTest.getXCoordinate()+1);
                                    posTest.setYCoordinate(posTest.getYCoordinate()+1);
                                    b.getDisk(posTest).setColor(c);
                                }
                                break; // sortir du for
                            }
                        }
                    }
                }
            }
            posTest.setXCoordinate(x);
            posTest.setYCoordinate(y);
        }
        // Vérification droite haut
        if (x < 7 && y > 0) {
            posTest.setXCoordinate(x+1);
            posTest.setYCoordinate(y-1);
            if (b.getDisk(posTest) != null) {
                if (!(b.getDisk(posTest).getColor() == c)) {
                    posTest.setXCoordinate(x);
                    posTest.setYCoordinate(y);
                    if (7 - x > y) {
                        lower = y;
                        diff = y;
                    } else {
                        lower = x;
                        diff = 7 - x;
                    }
                    for (int i = 0 ; i < diff ; i++) {
                        posTest.setXCoordinate(posTest.getXCoordinate()+1);
                        posTest.setYCoordinate(posTest.getYCoordinate()-1);
                        if (b.getDisk(posTest) != null) {
                            if (b.getDisk(posTest).getColor() == c) {
                                posTest.setXCoordinate(x);
                                posTest.setYCoordinate(y);
                                for (int j = 0 ; j < i ; j++) {
                                    posTest.setXCoordinate(posTest.getXCoordinate()+1);
                                    posTest.setYCoordinate(posTest.getYCoordinate()-1);
                                    b.getDisk(posTest).setColor(c);
                                }
                                break; // sortir du for
                            }
                        }
                    }
                }
            }
            posTest.setXCoordinate(x);
            posTest.setYCoordinate(y);
        }

        // Vérification gauche bas
        if (x > 0 && y < 7) {
            posTest.setXCoordinate(x-1);
            posTest.setYCoordinate(y+1);
            if (b.getDisk(posTest) != null) {
                if (!(b.getDisk(posTest).getColor() == c)) {
                    posTest.setXCoordinate(x);
                    posTest.setYCoordinate(y);
                    if (x > 7 - y) {
                        lower = y;
                        diff = 7 - y;
                    } else {
                        lower = x;
                        diff = x;
                    }
                    for (int i = 0 ; i < diff ; i++) {
                        posTest.setXCoordinate(posTest.getXCoordinate()-1);
                        posTest.setYCoordinate(posTest.getYCoordinate()+1);
                        if (b.getDisk(posTest) != null) {
                            if (b.getDisk(posTest).getColor() == c) {
                                posTest.setXCoordinate(x);
                                posTest.setYCoordinate(y);
                                for (int j = 0 ; j < i ; j++) {
                                    posTest.setXCoordinate(posTest.getXCoordinate()-1);
                                    posTest.setYCoordinate(posTest.getYCoordinate()+1);
                                    b.getDisk(posTest).setColor(c);
                                }
                                break; // sortir du for
                            }
                        }
                    }
                }
            }
            posTest.setXCoordinate(x);
            posTest.setYCoordinate(y);
        }

        // Vérification gauche haut
        if (x > 0 && y > 0) {
            posTest.setXCoordinate(x-1);
            posTest.setYCoordinate(y-1);
            if (b.getDisk(posTest) != null) {
                if (!(b.getDisk(posTest).getColor() == c)) {
                    posTest.setXCoordinate(x);
                    posTest.setYCoordinate(y);
                    if (x > y) {
                        lower = y;
                    } else {
                        lower = x;
                    }
                    for (int i = lower ; i > 0 ; i--) {
                        posTest.setXCoordinate(posTest.getXCoordinate()-1);
                        posTest.setYCoordinate(posTest.getYCoordinate()-1);
                        if (b.getDisk(posTest) != null) {
                            if (b.getDisk(posTest).getColor() == c) {
                                posTest.setXCoordinate(x);
                                posTest.setYCoordinate(y);
                                for (int j = lower ; j > i ; j--) {
                                    posTest.setXCoordinate(posTest.getXCoordinate()-1);
                                    posTest.setYCoordinate(posTest.getYCoordinate()-1);
                                    b.getDisk(posTest).setColor(c);
                                }
                                break; // sortir du for
                            }
                        }
                    }
                }
            }
            posTest.setXCoordinate(x);
            posTest.setYCoordinate(y);
        }

        game.getBoard().setBoard(b.getBoard());
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

        return retour;
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
