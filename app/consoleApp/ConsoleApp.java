package consoleApp;

import java.util.Calendar;
import java.util.UUID;
import java.util.Locale;
import java.util.Date;
import java.util.ArrayList;

import dao.DAO;
import dao.DAOException;
import DAOFile.DAOFile;
import engine.EngineBridge;
import engine.Game;
import engine.HumanPlayer;
import engine.Player;
import engine.Position;
import engine.EnumRule;

import bot.Bot;
import bot.BotManager;
import fr.univubs.inf1603.othello.Tournament.Tournament;
import fr.univubs.inf1603.othello.Tournament.KnockoutTournament;
import fr.univubs.inf1603.othello.Tournament.PoolTournament;

/**
 * ConsoleApp contains all the methods necessary to run the App.
 *
 * @version 1.0.0
 */
public class ConsoleApp {

    /**
     * Gets the name of every Bot
     *
     * @return The name of every Bot
     */
    static String[] getBotsName() {
        return BotManager.getBotsName();
    }

    /**
     * Get the name of every Bot in the given Locale (Language)
     *
     * @return The name of every Bot in the given Locale (Language)
     */
    static String[] getBotsName(Locale locale) {
        return BotManager.getBotsName(locale);
    }

    /**
     * Creates a Game with the given Players and the given EnumRule
     *
     * @param player1 First Player of the Game
     * @param player2 Second Player of the Game
     * @param rule EnumRule of the Game which is either OTHELLO or REVERSI
     * @return A Game with the given Players and the given EnumRule
     */
    static Game createGame(Player player1, Player player2, EnumRule rule) {
        Game game = EngineBridge.createGame(UUID.randomUUID(), player1, player2, EngineBridge.getRule(rule));

        if(player1 instanceof Bot) {
            ((Bot) player1).setDelay(1500);
            ((Bot) player1).start();
        }

        if(player2 instanceof Bot) {
            ((Bot) player2).setDelay(1500);
            ((Bot) player2).start();
        }

        return game;
    }

    /**
     * Creates a Game with the given Players, the given EnumRule and the given array of Position
     * This method is used for Games that were saved.
     *
     * @param player1 First Player of the Game
     * @param player2 Second Player of the Game
     * @param rule EnumRule of the Game which is either OTHELLO or REVERSI
     * @param positions Array of Positions which contains every move played in a Game.
     * @return A Game with the given Players, the given EnumRule and the given array of Position
     */
    static Game createGame(Player player1, Player player2, EnumRule rule, Position[] positions) {
        Game game = EngineBridge.createGame(UUID.randomUUID(), player1, player2, EngineBridge.getRule(rule), positions);

        if(player1 instanceof Bot) {
            ((Bot) player1).setDelay(1500);
            ((Bot) player1).start();
        }

        if(player2 instanceof Bot) {
            ((Bot) player2).setDelay(1500);
            ((Bot) player2).start();
        }

        return game;
    }

    /**
     * Creates a KnockoutTournament with the given UUID, name, password, maximum capacity, start date and EnumRule
     *
     * @param tournamentUUID UUUID of the KnockoutTournament
     * @param tournamentName Name of the KnockoutTournament
     * @param password Password of the KnockoutTournament (can be null)
     * @param maximumCapacity Maximum capacity of the KnockoutTournament
     * @param startDateGame Start date of the KnockoutTournament
     * @param rule EnumRule of the KnockoutTournament which is either OTHELLO or REVERSI
     * @return A KnockoutTournament with the given UUID, name, password, maximum capacity, start date and EnumRule
     */
    static KnockoutTournament createKnockoutTournament(UUID tournamentUUID, String tournamentName, String password, int maximumCapacity, Date startDateGame, EnumRule rule) {
        KnockoutTournament knockoutTournament;

        Calendar calendar = Calendar.getInstance();
        calendar.set(startDateGame.getYear(), startDateGame.getMonth(), startDateGame.getDate(), startDateGame.getHours(), startDateGame.getMinutes());

        if(password != null) {
            knockoutTournament = new KnockoutTournament(tournamentUUID, tournamentName, password, maximumCapacity, calendar, EngineBridge.getRule(rule));
        }

        else{
            knockoutTournament = new KnockoutTournament(tournamentUUID, tournamentName, maximumCapacity, calendar, EngineBridge.getRule(rule));
        }

        return knockoutTournament;
    }

    /**
     * Creates a PoolTournament with the given UUID, name, password, maximum capacity, start date and EnumRule
     *
     * @param tournamentUUID UUUID of the PoolTournament
     * @param tournamentName Name of the PoolTournament
     * @param password Password of the PoolTournament (can be null)
     * @param maximumCapacity Maximum capacity of the PoolTournament
     * @param startDateGame Start date of the PoolTournament
     * @param rule EnumRule of the PoolTournament which is either OTHELLO or REVERSI
     * @return A PoolTournament with the given UUID, name, password, maximum capacity, start date and EnumRule
     */
    static PoolTournament createPoolTournament(UUID tournamentUUID, String tournamentName, String password, int maximumCapacity, Date startDateGame, EnumRule rule) {
        PoolTournament poolTournament;

        Calendar calendar = Calendar.getInstance();
        calendar.set(startDateGame.getYear(), startDateGame.getMonth(), startDateGame.getDate(), startDateGame.getHours(), startDateGame.getMinutes());

        if(password != null) {
            poolTournament = new PoolTournament(tournamentUUID, tournamentName, password, maximumCapacity, calendar, EngineBridge.getRule(rule));
        }

        else {
            poolTournament = new PoolTournament(tournamentUUID, tournamentName, maximumCapacity, calendar, EngineBridge.getRule(rule));
        }

        return poolTournament;
    }

    /**
     * Saves the given Game with the given DAO
     *
     * @param game Game saved
     * @param dao DAO where the Game will be saved
     * @throws DAOException
     */
    public static void saveGame(Game game, DAO dao) throws DAOException {
        if(game.getPlayer1() instanceof HumanPlayer) {
            dao.savePlayer(game.getPlayer1());
        }

        if(game.getPlayer2() instanceof HumanPlayer) {
            dao.savePlayer(game.getPlayer2());
        }

        dao.saveGame(game);
        System.out.println("Partie sauvegardée avec succès !");
    }

    /**
     * Loads a Game with the given UUID with the given DAO
     *
     * @param uuid UUID of the Game that is loaded
     * @param dao DAO where the Game is saved
     * @return Game loaded
     * @throws DAOException
     */
    public static Game loadGame(UUID uuid, DAO dao) throws DAOException {
        Game g = dao.findGame(uuid);
        return g;
    }

    /**
     * Loads Games of a Player with the given Player's UUID with the given DAO
     *
     * @param uuid UUID of a Player
     * @param dao DAO where the Games are saved
     * @return Games loaded
     * @throws DAOException
     */
    public static ArrayList<Game> loadGames(UUID uuid, DAO dao) throws DAOException {
        ArrayList<Game> list = dao.findGames(uuid);
        return list;
    }

    /**
     * Loads Games with the given DAO
     *
     * @param dao DAO where the Games are saved
     * @return Games loaded
     * @throws DAOException
     */
    public static ArrayList<Game> loadGames(DAO dao) throws DAOException {
        ArrayList<Game> list = ((DAOFile) dao).internalFindGames();
        return list;
    }

    /**
     * Saves the given Tournament with the given DAO
     *
     * @param tournament Tournament saved
     * @param dao DAO where the Tournament will be saved
     * @throws DAOException
     */
    public static void saveTournament(Tournament tournament, DAO dao) throws DAOException {
        dao.saveTournament(tournament);
    }

    /**
     * Loads a Tournament with the given UUID with the given DAO
     *
     * @param uuid UUID of the Tournament that is loaded
     * @param dao DAO where the Tournament is saved
     * @return Tournament loaded
     * @throws DAOException
     */
    public static Tournament loadTournament(UUID uuid, DAO dao) throws DAOException {
        Tournament t = dao.findTournament(uuid);
        return t;
    }

    /**
     * Loads Tournaments
     *
     * @return Tournaments loaded
     * @throws DAOException
     */
    public static ArrayList<Tournament> loadTournaments(DAO dao) throws DAOException {
        ArrayList<Tournament> list = dao.findTournaments();
        return list;
    }
}
