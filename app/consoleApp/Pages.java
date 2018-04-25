package consoleApp;

import java.util.ArrayList;

import engine.Game;
import engine.HumanPlayer;
import engine.ReversiRule;
import engine.State;

import dao.DAOException;
import daosgbd.DAOSGBD;

import fr.univubs.inf1603.othello.Tournament.*;

/**
 * Pages contains all the methods necessary to display the pages of the App.
 *
 * @version 1.0.0
 */
public class Pages {

    /**
     * Home Page of the App
     *
     * @return Home Page of the App
     */
    static String home() {
        String retour = "";

        retour += "\n\t\t ________________________________________________________________ \n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|         ___    ___            __                  ___          |\n";
        retour += "\t\t|        / _ \\    |    |   |   |     |      |      / _ \\         |\n";
        retour += "\t\t|       | |_| |   |    |---|   |-    |      |     | |_| |        |\n";
        retour += "\t\t|        \\___/    |    |   |   |__   |___   |___   \\___/         |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t  Bienvenue sur le jeu Othello ! Que voulez-vous faire ?\t |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t1 - Jouer en ligne\t\t\t\t\t\t\t |\n";
        retour += "\t\t|\t\t\t\t\t2 - Jouer hors-ligne\t\t\t\t\t\t |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t0 - Quitter\t\t\t\t\t\t\t\t\t |\n";
        retour += "\t\t|________________________________________________________________|\n";

        return retour;
    }

    static String online() {
        String retour = "";

        retour += "\n\t\t ________________________________________________________________ \n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|         ___    ___            __                  ___          |\n";
        retour += "\t\t|        / _ \\    |    |   |   |     |      |      / _ \\         |\n";
        retour += "\t\t|       | |_| |   |    |---|   |-    |      |     | |_| |        |\n";
        retour += "\t\t|        \\___/    |    |   |   |__   |___   |___   \\___/         |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t  Mode En-Ligne - Que voulez-vous faire ?\t\t\t |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t1 - Rejoindre une partie\t\t\t\t\t |\n";
        retour += "\t\t|\t\t\t\t\t2 - Créer un tournoi\t\t\t\t\t\t |\n";
        retour += "\t\t|\t\t\t\t\t3 - Rejoindre un tournoi\t\t\t\t\t |\n";
        retour += "\t\t|\t\t\t\t\t0 - Retour\t\t\t\t\t\t\t\t\t |\n";
        retour += "\t\t|________________________________________________________________|\n";

        return retour;
    }

    static String listTournament() {
        String retour = "";

        retour += "\n\t\t _____________________________________________________________________ \n";
        retour += "\t\t|                                                                     |\n";
        retour += "\t\t|         ___    ___            __                  ___               |\n";
        retour += "\t\t|        / _ \\    |    |   |   |     |      |      / _ \\              |\n";
        retour += "\t\t|       | |_| |   |    |---|   |-    |      |     | |_| |             |\n";
        retour += "\t\t|        \\___/    |    |   |   |__   |___   |___   \\___/              |\n";
        retour += "\t\t|                                                                     |\n";
        retour += "\t\t|                                                                     |\n";
        retour += "\t\t|\t\t\t  Mode En-Ligne - Choisissez un tournoi\t\t\t      |\n";
        retour += "\t\t|                                                                     |\n";
        try {
            DAOSGBD dao= new DAOSGBD();
            ArrayList<Tournament> al = ConsoleApp.loadTournaments(dao);
            for(int i = 0; i< al.size(); i++){
                retour += "\t\t|"+i+" : Nom : "+al.get(i).getTournamentName()+" - capacité : "+al.get(i).getMaximumCapacity()+" \t\t\t\t\t    |\n";
            }
        } catch (DAOException e) {
            retour += "\t\t|\t\t\t  Aucun tournoi dans la base\t\t\t      |\n";
            retour += "\t\t|\t\t\t\t\t0 - Retour\t\t\t\t\t\t\t\t\t      |\n";
        }
        retour += "\t\t|_____________________________________________________________________|\n";

        return retour;
    }

    static String connect() {
        String retour = "";

        retour += "\n\t\t ________________________________________________________________ \n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|         ___    ___            __                  ___          |\n";
        retour += "\t\t|        / _ \\    |    |   |   |     |      |      / _ \\         |\n";
        retour += "\t\t|       | |_| |   |    |---|   |-    |      |     | |_| |        |\n";
        retour += "\t\t|        \\___/    |    |   |   |__   |___   |___   \\___/         |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t Mode En-Ligne - Veuillez rentrer votre adresse \t\t |\n";
        retour += "\t\t|\t\t\t\t\t et votre mot de passe \t\t\t\t\t\t |\n";

        retour += "\t\t|                                                                |\n";
        retour += "\t\t|________________________________________________________________|\n";

        return retour;
    }

    static String offline() {
        String retour = "";

        retour += "\n\t\t ________________________________________________________________ \n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|         ___    ___            __                  ___          |\n";
        retour += "\t\t|        / _ \\    |    |   |   |     |      |      / _ \\         |\n";
        retour += "\t\t|       | |_| |   |    |---|   |-    |      |     | |_| |        |\n";
        retour += "\t\t|        \\___/    |    |   |   |__   |___   |___   \\___/         |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t  Mode hors-ligne - Que voulez-vous faire ?\t\t\t |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t1 - Jouer une nouvelle partie\t\t\t\t |\n";
        retour += "\t\t|\t\t\t\t\t2 - Jouer une partie sauvegardée\t\t\t |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t0 - Retour\t\t\t\t\t\t\t\t\t |\n";
        retour += "\t\t|________________________________________________________________|\n";

        return retour;
    }

    static String offlineGameSaved(ArrayList<Game> games) {
        String retour = "";

        retour += "\n\t\t ________________________________________________________________ \n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t Parties sauvegardées\t\t\t\t\t\t |\n";
        retour += "\t\t|                                                                |\n";

        int i = 1;

        if(games.size() != 0) {
            for(Game game : games) {
                String ruleString = "OTHELLO";

                if(game.getRule() instanceof ReversiRule) {
                    ruleString = "REVERSI";
                }

                retour += "\t\t|\t\t\t"+i+" - "+game.getPlayer1().getNickname()+" ("+game.getNbPoints(game.getPlayer1())+" points) VS "+game.getPlayer2().getNickname()+" ("+game.getNbPoints(game.getPlayer2())+" points)\t\t |\n";
                retour += "\t\t|\t\t\t\t"+ruleString+"\t\t\t\t"+game.getState()+"\t\t\t\t\t\t |\n";
            }

            retour += "\t\t|                                                                |\n";
            retour += "\t\t|\t\t\t0 - Retour\t\t\t\t\t\t\t\t\t\t\t |\n";
        }

        else {
            retour += "\t\t|\t\t  Aucune partie sauvegardée, que voulez-vous faire ?\t |\n";
            retour += "\t\t|                                                                |\n";
            retour += "\t\t|\t\t\t\t\t1 - Jouer une nouvelle partie\t\t\t\t |\n";
            retour += "\t\t|                                                                |\n";
            retour += "\t\t|\t\t\t\t\t0 - Retour\t\t\t\t\t\t\t\t\t |\n";
        }

        retour += "\t\t|________________________________________________________________|\n";

        return retour;
    }

    static String offlineGameParameter1() {
        String retour = "";

        retour += "\n\t\t ________________________________________________________________ \n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t  Quel type de partie voulez-vous jouer ?\t\t\t |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t1 - Humain vs Humain\t\t\t\t\t\t |\n";
        retour += "\t\t|\t\t\t\t\t2 - Humain vs Bot\t\t\t\t\t\t\t |\n";
        retour += "\t\t|\t\t\t\t\t3 - Bot vs Bot\t\t\t\t\t\t\t\t |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t0 - Retour\t\t\t\t\t\t\t\t\t |\n";
        retour += "\t\t|________________________________________________________________|\n";

        return retour;
    }

    static String offlineGameParameter2() {
        String retour = "";

        String[] botNames = ConsoleApp.getBotsName();

        retour += "\n\t\t ________________________________________________________________ \n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t  Quel Bot voulez-vous choisir ?\t\t\t\t |\n";
        retour += "\t\t|                                                                |\n";

        for(int i = 0 ; i < botNames.length ; i++) {
            if(botNames[i].length() < 12) {
                retour += "\t\t|\t\t\t\t\t"+(i+1)+" - "+botNames[i]+"\t\t\t\t\t\t\t\t |\n";
            }

            else {
                retour += "\t\t|\t\t\t\t\t"+(i+1)+" - "+botNames[i]+"\t\t\t\t\t\t\t |\n";
            }

        }

        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t0 - Retour\t\t\t\t\t\t\t\t\t |\n";
        retour += "\t\t|________________________________________________________________|\n";

        return retour;
    }

    static String offlineGameParameter3() {
        String retour = "";

        retour += "\n\t\t ________________________________________________________________ \n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t  Avec quelle règle voulez-vous jouer ?\t\t\t\t |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t1 - Othello\t\t\t\t\t\t\t\t\t |\n";
        retour += "\t\t|\t\t\t\t\t2 - Reversi\t\t\t\t\t\t\t\t\t |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t0 - Retour\t\t\t\t\t\t\t\t\t |\n";
        retour += "\t\t|________________________________________________________________|\n";

        return retour;
    }

    static String offlineGamePlayGame(Game game) {
        String retour = "";

        retour += "\n\t\t ________________________________________________________________ \n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t\t"+game.getPlayer1().getNickname()+" (1) VS "+game.getPlayer2().getNickname()+" (2)\t\t\t\t |\n";
        retour += "\t\t|                                                                |\n";

        retour += game.getBoard().toString(game);

        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t"+game.getPlayer1().getNickname()+" : "+game.getNbPoints(game.getPlayer1())+" points\t\t"+game.getPlayer2().getNickname()+" : "+game.getNbPoints(game.getPlayer2())+" points\t\t\t |\n";
        retour += "\t\t|                                                                |\n";

        if(game.getState() != State.END && (game.getCurrentPlayer() instanceof HumanPlayer)) {
            retour += "\t\t|                                                                |\n";
            retour += "\t\t|\t\t\t\t  Que veux-tu faire "+game.getCurrentPlayer().getNickname()+" ?\t\t\t\t\t |\n";
            retour += "\t\t|                                                                |\n";
            retour += "\t\t|\t\t\t\t\t1 - Placer un pion\t\t\t\t\t\t\t |\n";
            retour += "\t\t|                                                                |\n";
            retour += "\t\t|\t\t\t\t\t0 - Quitter et sauvegarder\t\t\t\t\t |\n";
        }

        retour += "\t\t|________________________________________________________________|\n";

        return retour;
    }

    static String offlineGameEndGame(Game game) {
        String retour = "";

        retour += "\n\t\t ________________________________________________________________ \n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t"+game.getPlayer1().getNickname()+" (1) VS "+game.getPlayer2().getNickname()+" (2)\t\t\t\t\t |\n";
        retour += "\t\t|                                                                |\n";

        if(game.isDraw()) {
            retour += "\t\t|                           EGALITE                              |\n";
        }

        else {
            retour += "\t\t|\t\t\t\t\tGAGNANT : "+game.getWinner()+"\t\t\t\t |\n";
            retour += "\t\t|\t\t\t\t\tPERDANT : "+game.getLoser()+"\t\t\t\t |\n";
        }

        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t"+game.getPlayer1().getNickname()+" : "+game.getNbPoints(game.getPlayer1())+" points\t\t"+game.getPlayer2().getNickname()+" : "+game.getNbPoints(game.getPlayer2())+" points\t\t\t |\n";
        retour += "\t\t|                                                                |\n";

        retour += "\t\t|\t\t\t\t  Que voulez-vous faire ?\t\t\t\t\t\t |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t1 - Rejouer\t\t\t\t\t\t\t\t\t |\n";
        retour += "\t\t|                                                                |\n";
        retour += "\t\t|\t\t\t\t\t0 - Retourner à la page d'accueil\t\t\t |\n";


        retour += "\t\t|________________________________________________________________|\n";

        return retour;
    }
}
