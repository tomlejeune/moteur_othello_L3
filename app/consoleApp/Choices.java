package consoleApp;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.Calendar;

import engine.EngineBridge;
import engine.Game;
import engine.Player;
import engine.Position;
import engine.Rule;
import engine.OthelloRule;
import engine.EnumRule;
import engine.State;
import engine.PlayException;

import bot.Bot;
import bot.BotDescription;

import dao.DAOException;
import DAOFile.DAOFile;
import daosgbd.DAOSGBD;

import fr.univubs.inf1603.othello.Tournament.*;

public class Choices {

    static void choiceHome(Scanner scanner) throws DAOException {
        System.out.println(Pages.home());
        System.out.print("Votre choix (taper un des numéros) : ");

        int choice = scanner.nextInt();

        while(choice != 0 && choice != 1 && choice != 2) {
            System.out.println("Votre choix est invalide, veuillez choisir un numéro disponible !\n");
            System.out.print("Votre choix : ");
            choice = scanner.nextInt();
        }

        if(choice == 0) {
            scanner.close();
        }

        else if(choice == 1) {
            connect(scanner);
        }

        else {
            choiceOffline(scanner);
        }
    }

    private static void choiceOnline(Scanner scanner) throws DAOException {

        System.out.println(Pages.online());
        System.out.print("Votre choix (taper un des numéros) : ");

        int choice = scanner.nextInt();

        while(choice != 0 && choice != 1&& choice != 2&& choice != 3) {
            System.out.println("Votre choix est invalide, veuillez choisir un numéro disponible !\n");
            System.out.print("Votre choix : ");
            choice = scanner.nextInt();

        }
        if(choice==0){
            choiceHome(scanner);
        } else if(choice==3) {
            choiceTournament(scanner);
        } else if(choice==2){
            createTournament(scanner);
        } else if(choice==1){
            choiceGameOnline(scanner);
        }
    }

    private static void choiceGameOnline(Scanner scanner) throws DAOException{

    }

    private static void connect(Scanner scanner) throws DAOException {
        System.out.println(Pages.connect());
        System.out.println("Veuillez rentrer votre adresse : ");
        String mail = scanner.next();
        System.out.println("Veuillez rentrer votre mot de passe : ");
        String password = scanner.next();

        DAOSGBD dao= new DAOSGBD();
        Player player = dao.findPlayer(mail, password);
        if(player==null){
            System.out.print("Email ou mot de passe incorrect");
            choiceHome(scanner);
        }
        System.out.println("Vous êtes maintenant connecté");
        choiceOnline(scanner);
    }

    private static void choiceTournament(Scanner scanner) throws DAOException {
        ArrayList<Tournament> al=null;
        System.out.println(Pages.listTournament());
        System.out.print("Votre choix (taper un des numéros) : ");
        int choice = scanner.nextInt();
        try {
            DAOSGBD dao= new DAOSGBD();
            al = ConsoleApp.loadTournaments(dao);
        } catch (DAOException e) {
            System.out.println("Aucun tournoi dans la base");
        }
        while(choice > al.size()) {
            System.out.println("Votre choix est invalide, veuillez choisir un numéro disponible !\n");
            System.out.print("Votre choix : ");
            choice = scanner.nextInt();
        }

        PublicSubscription ps = new PublicSubscription();
        //ps.subscribePlayerTournament(p, al.get(choice));
        System.out.print("Vous êtes maintenant inscrit au tournoi : "+al.get(choice).getTournamentName());
    }

    private static void createTournament(Scanner scanner) throws DAOException {
        System.out.println("Veuillez rentrez un nom : ");
        String name = scanner.next();
        System.out.println("Veuillez rentrez un type (Knockout ou Pool) : ");
        String type = scanner.next();
        while(!(type.equals("Pool") || type.equals("Knockout"))){
            System.out.print("Erreur de type \n");
            System.out.println("Veuillez rentrez un type (Knockout ou Pool) : ");
            type = scanner.next();
        }
        System.out.println("Veuillez rentrez une règle (Othello ou Reversi) : ");
        String rule = scanner.next();
        while(!(rule.equals("Othello") || rule.equals("Reversi"))){
            System.out.print("Erreur de règle \n");
            System.out.println("Veuillez rentrez une règle (Othello ou Reversi) : ");
            rule = scanner.next();
        }
        Rule r = null;
        if(rule.equals("Othello")){
            r = EngineBridge.getRule(EnumRule.OTHELLO);
        } else {
            r = EngineBridge.getRule(EnumRule.REVERSI);
        }
        System.out.println("Veuillez rentrez un nombre de participants : ");
        int nb = scanner.nextInt();
        System.out.println("Veuillez rentrez une année : ");
        int year = scanner.nextInt();
        System.out.println("Veuillez rentrez un mois : ");
        int month = scanner.nextInt();
        System.out.println("Veuillez rentrez un jour : ");
        int day = scanner.nextInt();
        System.out.println("Veuillez rentrez une heure : ");
        int hour = scanner.nextInt();
        System.out.println("Veuillez rentrez les minutes : ");
        int minute = scanner.nextInt();
        Calendar startDateGame = Calendar.getInstance();
        startDateGame.set(year, month, day, hour, minute);
        Tournament tournament;
        if(type.equals("Pool")){
            tournament = new KnockoutTournament(UUID.randomUUID(), name, "", nb, startDateGame, r);
        }else {
            tournament =new PoolTournament(UUID.randomUUID(), name, "", nb, startDateGame, r);
        }

        System.out.println("Votre tournoi "+name+" à la date du "+startDateGame.getTime() +"\n");
        System.out.println("Avec un nombre de joueur de "+nb+" joueurs à bien été crée.");

        DAOSGBD dao= new DAOSGBD();
        ConsoleApp.saveTournament(tournament,dao);

        choiceOnline(scanner);
    }

    private static void choiceOffline(Scanner scanner) throws DAOException {
        System.out.println(Pages.offline());
        System.out.print("Votre choix (taper un des numéros) : ");

        int choice = scanner.nextInt();

        while(choice != 0 && choice != 1 && choice != 2) {
            System.out.println("Votre choix est invalide, veuillez choisir un numéro disponible !\n");
            System.out.print("Votre choix : ");
            choice = scanner.nextInt();
        }

        if(choice == 0) {
            choiceHome(scanner);
        }

        else if(choice == 1) {
            choiceOfflineGameParameter1(scanner);
        }

        else {
            choiceOfflineGameSaved(scanner);
        }
    }

    private static void choiceOfflineGameSaved(Scanner scanner) throws DAOException {
        DAOFile daoFile = new DAOFile();

        ArrayList<Game> games = ConsoleApp.loadGames(daoFile);

        System.out.println(Pages.offlineGameSaved(games));

        if(games.size() != 0) {
            System.out.print("Votre choix pour jouer à une partie (taper un des numéros correspondant à une partie) : ");

            int choice = scanner.nextInt();

            while(choice < 0 || choice > games.size()) {
                System.out.println("Votre choix est invalide, veuillez choisir un numéro disponible !\n");
                System.out.print("Votre choix : ");
                choice = scanner.nextInt();
            }

            if(choice == 0) {
                choiceOffline(scanner);
            }

            else {
                choiceOfflinePlayGame(scanner, games.get(choice-1));
            }
        }

        else {
            System.out.print("Votre choix (taper un des numéros correspondant) : ");

            int choice = scanner.nextInt();

            while(choice != 0 && choice != 1) {
                System.out.println("Votre choix est invalide, veuillez choisir un numéro disponible !\n");
                System.out.print("Votre choix : ");
                choice = scanner.nextInt();
            }

            if(choice == 0) {
                choiceOffline(scanner);
            }

            else {
                choiceOfflineGameParameter1(scanner);
            }
        }
    }

    private static void choiceOfflineGameParameter1(Scanner scanner) throws DAOException {
        System.out.println(Pages.offlineGameParameter1());
        System.out.print("Votre choix (taper un des numéros) : ");

        int choice = scanner.nextInt();

        while(choice != 0 && choice != 1 && choice != 2 && choice != 3) {
            System.out.println("Votre choix est invalide, veuillez choisir un numéro disponible !\n");
            System.out.print("Votre choix : ");
            choice = scanner.nextInt();
        }

        if(choice == 0) {
            choiceOffline(scanner);
        }

        else if(choice == 1) {
            scanner.nextLine();

            System.out.print("Quel surnom pour le joueur 1 ? ");
            String nicknamePlayer1 = scanner.nextLine();

            System.out.print("Quel surnom pour le joueur 2 ? ");
            String nicknamePlayer2 = scanner.nextLine();

            choiceOfflineGameParameter3(scanner, EngineBridge.createHumanPlayer(nicknamePlayer1), EngineBridge.createHumanPlayer(nicknamePlayer2));
        }

        else {
            choiceOfflineGameParameter2(scanner, choice, null);
        }
    }

    private static void choiceOfflineGameParameter2(Scanner scanner, int previousChoice, Player player1) throws DAOException {
        System.out.println(Pages.offlineGameParameter2());
        System.out.print("Votre choix (taper un des numéros) : ");

        int choice = scanner.nextInt();

        while(choice != 0 && choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5) {
            System.out.println("Votre choix est invalide, veuillez choisir un numéro disponible !\n");
            System.out.print("Votre choix : ");
            choice = scanner.nextInt();
        }

        Bot bot = null;
        BotDescription[] botDescriptions = EngineBridge.getBots();

        if(choice == 0) {
            choiceOfflineGameParameter1(scanner);
        }

        else {
            for(int i = 0 ; i < botDescriptions.length ; i++) {
                if(botDescriptions[i].getDifficulty() == (choice-1)) {
                    bot = EngineBridge.chooseBot(botDescriptions[i]);

                    break;
                }
            }

            if(previousChoice == 2) {
                scanner.nextLine();
                System.out.print("Quel surnom pour le joueur 1 ? ");
                String nicknamePlayer1 = scanner.nextLine();

                choiceOfflineGameParameter3(scanner, EngineBridge.createHumanPlayer(nicknamePlayer1), bot);
            }

            else {
                if(player1 == null) {
                    choiceOfflineGameParameter2(scanner, previousChoice, bot);
                }

                else {
                    choiceOfflineGameParameter3(scanner, player1, bot);
                }
            }
        }
    }

    private static void choiceOfflineGameParameter3(Scanner scanner, Player player1, Player player2) throws DAOException {
        System.out.println(Pages.offlineGameParameter3());
        System.out.print("Votre choix (taper un des numéros) : ");

        int choice = scanner.nextInt();

        while(choice != 0 && choice != 1 && choice != 2) {
            System.out.println("Votre choix est invalide, veuillez choisir un numéro disponible !\n");
            System.out.print("Votre choix : ");
            choice = scanner.nextInt();
        }

        if(choice == 0) {
            choiceOfflineGameParameter1(scanner);
        }

        else if(choice == 1) {
            choiceOfflinePlayGame(scanner, ConsoleApp.createGame(player1, player2, EnumRule.OTHELLO));
        }

        else {
            choiceOfflinePlayGame(scanner, ConsoleApp.createGame(player1, player2, EnumRule.REVERSI));
        }
    }

    private static void choiceOfflinePlayGame(Scanner scanner, Game game) throws DAOException {
        System.out.println(Pages.offlineGamePlayGame(game));

        if(game.getState() != State.END) {
            System.out.print("Votre choix (taper un des numéros) : ");
            int choice = scanner.nextInt();

            while(choice != 0 && choice != 1) {
                System.out.println("Votre choix est invalide, veuillez choisir un numéro disponible !\n");
                System.out.print("Votre choix : ");
                choice = scanner.nextInt();
            }

            if(choice == 0) {
                DAOFile daoFile = new DAOFile();
                ConsoleApp.saveGame(game, daoFile);

                choiceHome(scanner);
            }

            else {
                System.out.println("Veuillez choisir une coordonnée (X,Y) pour placer un pion : ");

                System.out.print("Coordonnée X (horizontal) : ");
                int coordonneeX = scanner.nextInt();

                while((coordonneeX < 0) || (coordonneeX > 7)) {
                    System.out.println("Votre coordonée X est invalide, veuillez choisir un numéro entre 0 et 7 !\n");
                    System.out.print("Coordonnée X (horizontal) : ");
                    coordonneeX = scanner.nextInt();
                }

                System.out.print("Coordonnée Y (vertical) : ");
                int coordonneeY = scanner.nextInt();

                while((coordonneeY < 0) || (coordonneeY > 7)) {
                    System.out.println("Votre coordonée Y est invalide, veuillez choisir un numéro entre 0 et 7 !\n");
                    System.out.print("Coordonnée Y (horizontal) : ");
                    coordonneeX = scanner.nextInt();
                }

                Position position = EngineBridge.getPosition(coordonneeX, coordonneeY);

                try {
                    game.play(game.getCurrentPlayer(), position);
                }

                catch(PlayException e) {
                    System.out.println(e.getMessage());
                }

                choiceOfflinePlayGame(scanner, game);
            }
        }

        else {
            choiceOfflineEndGame(scanner, game);
        }
    }

    private static void choiceOfflineEndGame(Scanner scanner, Game game) throws DAOException {
        System.out.println(Pages.offlineGameEndGame(game));
        System.out.print("Votre choix (taper un des numéros) : ");

        int choice = scanner.nextInt();

        while(choice != 0 && choice != 1) {
            System.out.println("Votre choix est invalide, veuillez choisir un numéro disponible !\n");
            System.out.print("Votre choix : ");
            choice = scanner.nextInt();
        }

        if(choice == 0) {
            choiceHome(scanner);
        }

        else {
            if(game.getRule() instanceof OthelloRule) {
                choiceOfflinePlayGame(scanner, ConsoleApp.createGame(game.getPlayer1(), game.getPlayer2(), EnumRule.OTHELLO));
            }

            else {
                choiceOfflinePlayGame(scanner, ConsoleApp.createGame(game.getPlayer1(), game.getPlayer2(), EnumRule.REVERSI));
            }
        }
    }
}
