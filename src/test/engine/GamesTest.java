package engine;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GamesTest {

    @Test
    public void testGames() throws PlayException {

        try {
            //FileReader fReader = new FileReader(new File("~/IdeaProjects/othelloengine/src/test/java/engine/parties.txt"));
            //BufferedReader bReader = new BufferedReader(fReader);
            //String line = bReader.readLine();
            //bReader.close();

            String line = "player2:15:49:23-22-21-24-25-14-03-04-05-13-12-02-01-15-35-26-27-37-47-32-41-20-36-42-31-50-06-30-55-53-16-07-17-00-45-57-63-65-52-62-54-10-75-66-51-56-73-46-74-61-72-71-60-70-40-76-67-77/";
            line += "player2:3:3:54-35-surrend/";

            String[] games = line.split("/");

            for (int i = 0; i < games.length; i++) {
                String[] substr = games[i].split(":");
                String[] plays = substr[3].split("-");
                System.out.println(" - Test de la partie "+(i+1) + " sur " + (games.length));

                HumanPlayer player1 = new HumanPlayer(UUID.randomUUID(), "player1", "", "");
                HumanPlayer player2 = new HumanPlayer(UUID.randomUUID(), "player2", "", "");
                Rule rule = new OthelloRule();
                Game game = new Game(UUID.randomUUID(), player1, player2, rule);

                for (int j = 0; j < plays.length; j++) {
                    if (plays[j].equals("surrend")) {
                        game.getCurrentPlayer().forfeit(game);
                    }
                    else {
                        int x = Integer.parseInt(Character.toString(plays[j].charAt(0)));
                        int y = Integer.parseInt(Character.toString(plays[j].charAt(1)));
                        game.play(game.getCurrentPlayer(), new Position(x,y));
                    }
                }
                //System.out.println(game.getBoard().toString());
                //System.out.println("Gagnant : "+game.getWinner().getNickname());
                assertEquals(Integer.parseInt(substr[1]),game.getNbPoints(player1));
                assertEquals(Integer.parseInt(substr[2]),game.getNbPoints(player2));
                if (substr[0].equals("player1")) assertEquals(game.getWinner(),player1);
                else if (substr[0].equals("player2")) assertEquals(game.getWinner(),player2);
                else assertTrue(game.isDraw());

                System.out.println("réussi");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
