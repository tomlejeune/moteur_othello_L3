package engine;

import java.util.UUID;

/**
 * GameRequest is a class that let a Player ask another Player to join a Game.
 * The second player can accept or refuse the request, the first
 * player can always cancel the request.
 * When both players are ready, the game can be started
 *
 * @version 1.0.0
 */
public class GameRequest {

    private Player player1;
    private Player player2;
    private Rule rule;

    private boolean accepted;
    private boolean canceled;
    private boolean gameStarted;

    /**
     * Constuctor of the class GameRequest
     *
     * @param player1 The player who wants to create the game
     * @param player2 The other player
     * @param rule The rule of the game to create
     */
    public GameRequest(Player player1, Player player2, Rule rule) {
        this.player1 = player1;
        this.player2 = player2;
        this.rule = rule;

        this.accepted = false;
        this.canceled = false;
        this.gameStarted = false;
    }

    /**
     * Method to cancel the game request, only the first player can use it
     * @param player1 The player who created the game request
     * @return true if the request has been canceled, false if not
     */
    public boolean cancel(Player player1) {
        boolean ret = false;

        if(this.isAvailable() && (player1.getId().equals(this.player1.getId()))) {
            canceled = true;
            ret = true;
        }

        return ret;
    }

    /**
     * Method to accept the game request, only the second player can use it
     * @param player2 The player who has been asked for the game
     * @return true if the request has been accepted, false if not
     */
    public boolean accept(Player player2) {
        boolean ret = false;

        if(this.isAvailable() && (player2.getId().equals(this.player2.getId()))) {
            accepted = true;
            ret = true;
        }

        return ret;
    }

    /**
     * Method to accept the game request, only the second player can use it
     * @param player2 The player who has been asked for the game
     * @return true if the request has been refused, false if not
     */
    public boolean refuse(Player player2) {
        boolean ret = false;

        if(this.isAvailable() && (player2.getId().equals(this.player2.getId()))) {
            canceled = true;
            ret = true;
        }

        return ret;
    }

    /**
     * Starts the game if both player are ready
     * @return The Game that has been created the the two players and the rule
     */
    public Game startGame() {
        Game ret = null;

        if(this.isAvailable() && this.isAccepted()) {
            ret = EngineBridge.createGame(UUID.randomUUID(), player1, player2, rule);
        }

        return ret;
    }

    /**
     * Returns true if the second player has accepted the request
     * @return True if the second player has accepted the request, false if not
     */
    public boolean isAccepted() {
        return this.accepted;
    }

    /**
     * Returns true if the first player has canceled the request
     * @return True if the first player has canceled the request, false if not
     */
    public boolean isCanceled() {
        return this.canceled;
    }

    /**
     * Returns true is the GameRequest is available.
     * The request is available it has not been canceled and if the game has not
     * already been started
     * @return True if the game request is available, false if not
     */
    public boolean isAvailable() {
        return (!this.canceled && !this.gameStarted);
    }
}
