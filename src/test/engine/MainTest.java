package engine;

import java.awt.Color;
import java.util.UUID;

public class MainTest {

    public static void main(String[] args) {
        Player player1 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Player player2 = new HumanPlayer(UUID.randomUUID(), "", "", "");

        Rule rule = new OthelloRule();

        Game game = new Game(player1, player2, rule);

        System.out.println("\nTEST : \n");

        game.play(player1, new Position(5, 3));

        System.out.println(game.getBoard().toString());

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
        }
    }
}
