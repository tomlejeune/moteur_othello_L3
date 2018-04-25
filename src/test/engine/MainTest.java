package engine;

import bot.EasyBot;
import bot.HardBot;
import bot.IntelligentBot;

import java.util.UUID;

public class MainTest {

    public static void main(String[] args) {
        Rule rule = new OthelloRule();

        Game game = new Game(UUID.randomUUID(), new HumanPlayer(UUID.randomUUID(), "oui", "", ""), new HumanPlayer(UUID.randomUUID(), "non", "", ""), rule);

        System.out.println(game.getBoard().toString(game));

        /*for(int i = 0 ; i < 1 ; i++) {
            Game game = new Game(UUID.randomUUID(), player1, player2, rule);

            System.out.println("\nTEST "+i+" :\n");

            System.out.println(game.getBoard().toString());

            for(int j = 0 ; j < game.getPositions().length ; j++) {
                System.out.println("Coup "+j+" : "+game.getPositions()[j]);
            }
        }*/

        /*try {
            game.play(player1, new Position(4, 5));
        }

        catch(PlayException exception) {
            System.out.println(exception.getMessage());
        }

        Position[] positionsPlayer1 = game.getPositions(player1);
        Position[] positionsPlayer2 = game.getPositions(player2);

        System.out.println("\nPositions du joueur 1 : \n");

        for(int i = 0 ; i < positionsPlayer1.length ; i++) {
            System.out.println(positionsPlayer1[i].toString());
        }

        System.out.println("\nPositions du joueur 2 : \n");

        for(int i = 0 ; i < positionsPlayer2.length ; i++) {
            System.out.println(positionsPlayer2[i].toString());
        }

        Position[] possiblePositionsPlayer1 = game.getPlayablePositions(player1);
        Position[] possiblePositionsPlayer2 = game.getPlayablePositions(player2);

        System.out.println("\nMouvements possibles joueur 1 : \n");

        for(int i = 0 ; i < possiblePositionsPlayer1.length ; i++) {
            System.out.println(possiblePositionsPlayer1[i].toString());
        }

        System.out.println("\nMouvements possibles joueur 2 : \n");

        for(int i = 0 ; i < possiblePositionsPlayer2.length ; i++) {
            System.out.println(possiblePositionsPlayer2[i].toString());
        }*/
    }
}
