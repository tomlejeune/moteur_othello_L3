package engine;

import java.util.ArrayList;
import java.util.UUID;

import bot.BotDescription;
import bot.BotManager;
import bot.Bot;

/**
 * EngineBridge provides static methods to use the engine classes of the othello project.
 */
public class EngineBridge {

    /**
     * Creates a non-persisted HumanPlayer
     * @return The HumanPlayer instance
     */
    public static HumanPlayer createHumanPlayer(String nickname) {
        return createHumanPlayer(nickname, null, null);
    }

    /**
     * Creates a persisted HumanPlayer
     * @param mail The mail address of the player to create
     * @param hashPassword The password of the player to create
     * @param nickname The name of the player to create
     * @return The HumanPlayer instance
     */
    public static HumanPlayer createHumanPlayer(String nickname, String mail, String hashPassword) {
        return new HumanPlayer(UUID.randomUUID(), nickname, mail, hashPassword);
    }

    /**
     * Creates a persisted HumanPlayer
     * @param id The UUID of the Player
     * @param mail The mail address of the player to create
     * @param hashPassword The password of the player to create
     * @param nickname The name of the player to create
     * @return The HumanPlayer instance
     */
    public static HumanPlayer createHumanPlayer(UUID id, String mail, String hashPassword, String nickname) {
        return new HumanPlayer(id, nickname, mail, hashPassword);
    }

    /**
     * Returns an array of BotDescription
     *
     * @return An array of BotDescription
     */
    public static BotDescription[] getBots() {
        return BotManager.getBots();
    }

    /**
     * Returns the Bot for the given BotDescription
     *
     * @param botDescription BotDescription of a Bot
     * @return The instance of the Bot
     */
    public static Bot chooseBot(BotDescription botDescription) {
        return botDescription.createBot();
    }

    /**
     * Finds an random HumanPlayer to play a game with
     *
     * @param player Player which searches a random opponent
     * @param opponents List of opponents where to search
     * @return A HumanPlayer to play against
     */
    public static HumanPlayer getRandomOpponent(HumanPlayer player, ArrayList<HumanPlayer> opponents) {
        return player.findRandomOpponent(opponents);
    }

    /**
     * Removes the given Player from the given List
     *
     * @param player Player which wants to be removed from the given List
     * @param opponents List of opponents where to remove the given Player
     */
    public static void removeFromWaitingList(HumanPlayer player, ArrayList<HumanPlayer> opponents) {
        player.removeFromWaitingList(opponents);
    }

    /**
     * Returns the Rule for the given item of EnumRule
     * @param enumRule The chosen Rule
     * @return The Rule instance
     */
    public static Rule getRule(EnumRule enumRule) {
        if(enumRule == EnumRule.OTHELLO) {
            return new OthelloRule();
        }

        else if(enumRule == EnumRule.REVERSI) {
            return new ReversiRule();
        }

        else {
            return null;
        }
    }

    /**
     * Creates a Game with the given players and rule
     *
     * @param id UUID of the Game
     * @param player1 The first Player
     * @param player2 The second Player
     * @param rule The rule for the game
     * @return The Game instance
     */
    public static Game createGame(UUID id, Player player1, Player player2, Rule rule) {
        return new Game(id, player1, player2, rule);
    }

    /**
     * Creates a Game with the given Players, the given Rule and the given array of Positions
     *
     * @param id UUID of the Game
     * @param player1 The first Player
     * @param player2 The second Player
     * @param rule The Rule for the Game
     * @param positions Positions played in the Game
     * @return The Game instance
     */
    public static Game createGame(UUID id, Player player1, Player player2, Rule rule, Position[] positions) {
        return new Game(id, player1, player2, rule, positions);
    }

    /**
     * Creates a GameRequest with the given Players and the given Rule
     *
     * @param player1 The first Player
     * @param player2 The second Player
     * @param rule The rule of the Game
     * @return The GameRequest created
     */
    public static GameRequest createGameRequest(Player player1, Player player2, Rule rule) {
        return new GameRequest(player1, player2, rule);
    }

    /**
     * Returns the Position matching the given coordinates
     * @param x The abscissa of the Position
     * @param y The ordinate of the Position
     * @return The Position instance
     */
    public static Position getPosition(int x, int y) {
        return new Position(x, y);
    }

    /**
     * Loads all the games of a Player
     * @param player The player from whom the games are loaded
     * @return An array of GameDescriptor of the Player games
     */
    public static GameDescriptor[] loadGames(Player player, ArrayList<Game> games) {
        GameDescriptor[] gameDescriptors = new GameDescriptor[games.size()];

        int i = 0;

        for(Game game : games) {
            GameDescriptor gameDescriptor = new GameDescriptor(game.getUUID(), game.getRule(), game.getState(), game.getPlayer1(), game.getPlayer2());
            gameDescriptors[i] = gameDescriptor;

            i++;
        }

        return gameDescriptors;
    }

}
