package engine;

import java.awt.Color;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        testBoard();
    }

    public static void testBoard() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), Color.BLACK);
        Player player2 = new HumanPlayer(UUID.randomUUID(), Color.WHITE);
        Rule rule = new OthelloRule();
        Game game = new Game(player1, player2, rule);
        Board b = game.getBoard();

        System.out.println(b.toString());

        System.out.println("\nPlacement d'un pion du joueur 1 aux coordonnées (X = 5 ; Y = 3)\n");
        game.play(player1, new Position(5, 3));

        System.out.println(b.toString());

        Position[] positionsPlayer1 = b.getPositions(game, player1);
        Position[] positionsPlayer2 = b.getPositions(game, player2);

        System.out.println("\nPositions du joueur 1 : \n");

        for(int i = 0 ; i < positionsPlayer1.length ; i++) {
            System.out.println(positionsPlayer1[i].toString());
        }

        System.out.println("\nPositions du joueur 2 : \n");

        for(int i = 0 ; i < positionsPlayer2.length ; i++) {
            System.out.println(positionsPlayer2[i].toString());
        }
    }
}
