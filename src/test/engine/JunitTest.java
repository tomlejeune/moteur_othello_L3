package engine;

import java.awt.Color;
import java.util.UUID;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class JunitTest {

    @Test
    public void testCounter() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), Color.BLACK);
        Player player2 = new HumanPlayer(UUID.randomUUID(), Color.WHITE);
        Rule rule = new OthelloRule();
        Game game = new Game(player1, player2, rule);

        assertEquals(2, Counter.getNbPoint(game, player1));
        assertEquals(2, Counter.getNbPoint(game, player2));
    }

    @Test
    public void testPosition() {
        Position p = new Position(2, 6);

        assertEquals(2, p.getXCoordinate());
        assertEquals(6, p.getYCoordinate());
    }

    @Test
    public void testState() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), Color.BLACK);
        Player player2 = new HumanPlayer(UUID.randomUUID(), Color.WHITE);
        Rule rule = new OthelloRule();
        Game game = new Game(player1, player2, rule);

        assertEquals(State.INIT, game.getState());
    }

    @Test
    public void testDisk() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), Color.BLACK);

        Disk[] disksPlayer = player1.getDisks();

        for(int i = 0 ; i < disksPlayer.length ; i++) {
            assertEquals(player1, disksPlayer[i].getPlayer());
            assertEquals(Color.BLACK, disksPlayer[i].getColor());
        }
    }

    @Test
    public void testBoardOthello() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), Color.BLACK);
        Player player2 = new HumanPlayer(UUID.randomUUID(), Color.WHITE);
        Rule rule = new OthelloRule();
        Game game = new Game(player1, player2, rule);
        Board b = game.getBoard();

        Position[] positionsPlayer1 = b.getPositions(game, player1);
        Position[] positionsPlayer2 = b.getPositions(game, player2);

        assertEquals(2, positionsPlayer1.length);
        assertEquals(2, positionsPlayer2.length);

        assertEquals(3, positionsPlayer1[0].getXCoordinate());
        assertEquals(3, positionsPlayer1[0].getYCoordinate());
        assertEquals(4, positionsPlayer1[1].getXCoordinate());
        assertEquals(4, positionsPlayer1[1].getXCoordinate());

        assertEquals(3, positionsPlayer2[0].getXCoordinate());
        assertEquals(4, positionsPlayer2[0].getYCoordinate());
        assertEquals(4, positionsPlayer2[1].getXCoordinate());
        assertEquals(3, positionsPlayer2[1].getYCoordinate());

        b.placeDisk(player1, new Position(5, 3));

        positionsPlayer1 = b.getPositions(game, player1);
        positionsPlayer2 = b.getPositions(game, player2);

        assertEquals(3, positionsPlayer1[0].getXCoordinate());
        assertEquals(3, positionsPlayer1[0].getYCoordinate());
        assertEquals(4, positionsPlayer1[1].getXCoordinate());
        assertEquals(4, positionsPlayer1[1].getXCoordinate());
        assertEquals(5, positionsPlayer1[2].getXCoordinate());
        assertEquals(3, positionsPlayer1[2].getYCoordinate());

        assertEquals(3, positionsPlayer2[0].getXCoordinate());
        assertEquals(4, positionsPlayer2[0].getYCoordinate());
        assertEquals(4, positionsPlayer2[1].getXCoordinate());
        assertEquals(3, positionsPlayer2[1].getYCoordinate());
    }

    @Test
    public void testBoardReversi() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), Color.BLACK);
        Player player2 = new HumanPlayer(UUID.randomUUID(), Color.WHITE);
        Rule rule = new ReversiRule();
        Game game = new Game(player1, player2, rule);
        Board b = game.getBoard();

        Position[] positionsPlayer1 = b.getPositions(game, player1);
        Position[] positionsPlayer2 = b.getPositions(game, player2);

        assertEquals(0, positionsPlayer1.length);
        assertEquals(0, positionsPlayer2.length);

        b.placeDisk(player1, new Position(5, 3));

        positionsPlayer1 = b.getPositions(game, player1);
        positionsPlayer2 = b.getPositions(game, player2);

        assertEquals(5, positionsPlayer1[0].getXCoordinate());
        assertEquals(3, positionsPlayer1[0].getYCoordinate());

        assertEquals(0, positionsPlayer2.length);
    }

    @Test
    public void testRuleOthello() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), Color.BLACK);
        Player player2 = new HumanPlayer(UUID.randomUUID(), Color.WHITE);
        Rule rule = new OthelloRule();
        Game game = new Game(player1, player2, rule);

        assertEquals(rule, game.getRule());

        Position[] possiblePositionsPlayer1 = game.getPlayablePositions(player1);

        assertEquals(3, possiblePositionsPlayer1[0].getXCoordinate());
        assertEquals(5, possiblePositionsPlayer1[0].getYCoordinate());
        assertEquals(5, possiblePositionsPlayer1[1].getXCoordinate());
        assertEquals(3, possiblePositionsPlayer1[1].getYCoordinate());
        assertEquals(4, possiblePositionsPlayer1[2].getXCoordinate());
        assertEquals(2, possiblePositionsPlayer1[2].getYCoordinate());
        assertEquals(2, possiblePositionsPlayer1[3].getXCoordinate());
        assertEquals(4, possiblePositionsPlayer1[3].getYCoordinate());

        Position[] possiblePositionsPlayer2 = game.getPlayablePositions(player2);

        assertEquals(3, possiblePositionsPlayer2[0].getXCoordinate());
        assertEquals(2, possiblePositionsPlayer2[0].getYCoordinate());
        assertEquals(5, possiblePositionsPlayer2[1].getXCoordinate());
        assertEquals(4, possiblePositionsPlayer2[1].getYCoordinate());
        assertEquals(4, possiblePositionsPlayer2[2].getXCoordinate());
        assertEquals(5, possiblePositionsPlayer2[2].getYCoordinate());
        assertEquals(2, possiblePositionsPlayer2[3].getXCoordinate());
        assertEquals(3, possiblePositionsPlayer2[3].getYCoordinate());

        Position positionPlayed = new Position(5, 3);

        game.getBoard().placeDisk(player1, positionPlayed);
        rule.turnDisks(game, positionPlayed);

        Position[] positionsPlayer1 = game.getPositions(player1);
        Position[] positionsPlayer2 = game.getPositions(player2);

        assertEquals(3, positionsPlayer1[0].getXCoordinate());
        assertEquals(3, positionsPlayer1[0].getYCoordinate());
        assertEquals(4, positionsPlayer1[1].getXCoordinate());
        assertEquals(3, positionsPlayer1[1].getYCoordinate());
        assertEquals(4, positionsPlayer1[2].getXCoordinate());
        assertEquals(4, positionsPlayer1[2].getXCoordinate());
        assertEquals(5, positionsPlayer1[3].getXCoordinate());
        assertEquals(3, positionsPlayer1[3].getYCoordinate());

        assertEquals(3, positionsPlayer2[0].getXCoordinate());
        assertEquals(4, positionsPlayer2[0].getYCoordinate());

        assertEquals(player1, rule.getWinner(game));
        assertEquals(player2, rule.getLoser(game));
        assertEquals(false, rule.isDraw(game));
    }

    @Test
    public void testRuleReversi() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), Color.BLACK);
        Player player2 = new HumanPlayer(UUID.randomUUID(), Color.WHITE);
        Rule rule = new ReversiRule();
        Game game = new Game(player1, player2, rule);

        assertEquals(rule, game.getRule());

        Position[] possiblePositionsPlayer1 = game.getPlayablePositions(player1);

        assertEquals(3, possiblePositionsPlayer1[0].getXCoordinate());
        assertEquals(3, possiblePositionsPlayer1[0].getYCoordinate());
        assertEquals(3, possiblePositionsPlayer1[1].getXCoordinate());
        assertEquals(4, possiblePositionsPlayer1[1].getYCoordinate());
        assertEquals(4, possiblePositionsPlayer1[2].getXCoordinate());
        assertEquals(4, possiblePositionsPlayer1[2].getYCoordinate());
        assertEquals(4, possiblePositionsPlayer1[3].getXCoordinate());
        assertEquals(3, possiblePositionsPlayer1[3].getYCoordinate());

        Position[] possiblePositionsPlayer2 = game.getPlayablePositions(player2);

        assertEquals(3, possiblePositionsPlayer2[0].getXCoordinate());
        assertEquals(3, possiblePositionsPlayer2[0].getYCoordinate());
        assertEquals(3, possiblePositionsPlayer2[1].getXCoordinate());
        assertEquals(4, possiblePositionsPlayer2[1].getYCoordinate());
        assertEquals(4, possiblePositionsPlayer2[2].getXCoordinate());
        assertEquals(4, possiblePositionsPlayer2[2].getYCoordinate());
        assertEquals(4, possiblePositionsPlayer2[3].getXCoordinate());
        assertEquals(3, possiblePositionsPlayer2[3].getYCoordinate());

        Position positionPlayed = new Position(3, 3);

        game.getBoard().placeDisk(player1, positionPlayed);
        rule.turnDisks(game, positionPlayed);

        Position[] positionsPlayer1 = game.getPositions(player1);
        Position[] positionsPlayer2 = game.getPositions(player2);

        assertEquals(3, positionsPlayer1[0].getXCoordinate());
        assertEquals(3, positionsPlayer1[0].getYCoordinate());

        assertEquals(0, positionsPlayer2.length);

        assertEquals(player1, rule.getWinner(game));
        assertEquals(player2, rule.getLoser(game));
        assertEquals(false, rule.isDraw(game));
    }
}
