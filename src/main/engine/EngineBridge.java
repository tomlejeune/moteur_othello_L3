package engine;

import java.util.UUID;

import bot.BotDescription;
import bot.BotManager;
import bot.Bot;
import fr.univubs.inf1603.othello.DAO.DAOException;
import fr.univubs.inf1603.othello.DAOSGDB.DAOSGBD;

/**
 * EngineBridge provides static methods to use the engine classes of the othello project.
 */
public class EngineBridge {

    private static final DAOSGBD daosgbd = new DAOSGBD();

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
        return botDescription.createBot(null);
    }

    /**
     * Finds an random HumanPlayer to play a game with
     * @return The HumanPlayer instance
     */
    public static HumanPlayer getRandomOpponent(HumanPlayer player) {
        return player.findRandomOpponent();
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
     * Creates a Game with the given players and rule
     *
     * @param id UUID of the Game
     * @param player1 The first Player
     * @param player2 The second Player
     * @param rule The rule for the game
     * @param positions Positions played in the Game
     * @return The Game instance
     */
    public static Game createGame(UUID id, Player player1, Player player2, Rule rule, Position[] positions) {
        return new Game(id, player1, player2, rule, positions);
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
     * Saves the given Player
     * @param player The player to save
     */
    public static void savePlayer(Player player) {
        try {
            daosgbd.savePlayer(player);
        }

        catch(DAOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the Player matching the given mail and password
     * @param mail The mail address of the Player
     * @param password The password of the Player
     * @return The Player instance, null if no matching player was found
     */
    public static Player loadPlayer(String mail, String password) {
        try {
            return daosgbd.findPlayer(mail, password);
        }

        catch(DAOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Finds the Player matching the given id
     * @param id The id of the Player to find
     */
    public static Player loadPlayer(UUID id) {
        try {
            return daosgbd.findPlayer(id);
        }

        catch(DAOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Saves the given Game
     * @param game The Game to save
     */
    public static void saveGame(Game game) {
        try {
            daosgbd.saveGame(game);
        }

        catch(DAOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads all the games of a Player
     * @param player The player from whom the games are loaded
     * @return An array of GameDescriptor of the Player games
     */
    public static GameDescriptor[] loadGames(Player player) {
        // TODO
        return null;
    }

    /**
     * Loads the Game matching the given GameDescriptor
     * @param gameDesc The game to load
     * @return The Game instance
     */
    public static Game loadGame(GameDescriptor gameDesc) {
        try {
            return daosgbd.findGame(gameDesc.getId());
        }

        catch(DAOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Removes the player matching the given mail and password from the persitance
     * @param mail The mail address of the Player
     * @param password The password of the Player
     */
    public static void removePlayer(String mail, String password) {
        try {
            daosgbd.deletePlayer(daosgbd.findPlayer(mail, password).getId());
        }

        catch(DAOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Removes the Games matching the given GameDescriptor from the persistance
     * @param gameDesc The game to remove
     */
    public static void removeGame(GameDescriptor gameDesc) {
        try {
            daosgbd.deleteGame(gameDesc.getId());
        }

        catch(DAOException e) {
            System.out.println(e.getMessage());
        }
    }
}
