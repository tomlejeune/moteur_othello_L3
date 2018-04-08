package engine;

import java.awt.Color;
import java.util.UUID;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class JunitTest {

    @Test
    public void testCounter() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Player player2 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Rule rule = new OthelloRule();
        Game game = new Game(UUID.randomUUID(), player1, player2, rule);

        assertEquals(2, Counter.getNbPoints(game, player1));
        assertEquals(2, Counter.getNbPoints(game, player2));
    }

    @Test
    public void testPosition() {
        Position p = new Position(2, 6);

        assertEquals(2, p.getXCoordinate());
        assertEquals(6, p.getYCoordinate());
    }

    @Test
    public void testStateOthello() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Player player2 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Rule rule = new OthelloRule();
        Game game = new Game(UUID.randomUUID(), player1, player2, rule);

        assertEquals(State.PLAY, game.getState());
    }

    @Test
    public void testStateReversi() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Player player2 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Rule rule = new ReversiRule();
        Game game = new Game(UUID.randomUUID(), player1, player2, rule);

        assertEquals(State.INIT, game.getState());
    }

    @Test
    public void testDisk() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Player player2 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Rule rule = new OthelloRule();
        Game game = new Game(UUID.randomUUID(), player1, player2, rule);

        Disk[] disksPlayer1 = player1.getDisks();

        for(int i = 0 ; i < disksPlayer1.length ; i++) {
            assertEquals(player1, disksPlayer1[i].getPlayer());
            assertEquals(Color.BLACK, disksPlayer1[i].getColor());
        }

        Disk[] disksPlayer2 = player2.getDisks();

        for(int i = 0 ; i < disksPlayer2.length ; i++) {
            assertEquals(player2, disksPlayer2[i].getPlayer());
            assertEquals(Color.WHITE, disksPlayer2[i].getColor());
        }
    }

    @Test
    public void testBoardOthello() {
        Player player1 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Player player2 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Rule rule = new OthelloRule();
        Game game = new Game(UUID.randomUUID(), player1, player2, rule);
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
        Player player1 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Player player2 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Rule rule = new ReversiRule();
        Game game = new Game(UUID.randomUUID(), player1, player2, rule);
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
        Player player1 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Player player2 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Rule rule = new OthelloRule();
        Game game = new Game(UUID.randomUUID(), player1, player2, rule);

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
        Player player1 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Player player2 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Rule rule = new ReversiRule();
        Game game = new Game(UUID.randomUUID(), player1, player2, rule);

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

    @Test
    public void testGetNbPointIfPlayed() {
        HumanPlayer player1 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        HumanPlayer player2 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Rule rule = new OthelloRule();
        Game game = new Game(UUID.randomUUID(), player1, player2, rule);
        Board b = game.getBoard();

        Position pos1 = new Position(4,2);
        Position pos2 = new Position (2,4);
        Position pos3 = new Position(3,5);
        Position pos4 = new Position (5,3);

        Position[] possiblePositionsPlayer1 = game.getPlayablePositions(player1);

        assertEquals(4, game.getPlayablePositions(player1).length);
        assertEquals(3, possiblePositionsPlayer1[0].getXCoordinate());
        assertEquals(5, possiblePositionsPlayer1[0].getYCoordinate());

        assertEquals(2, game.getNbPointsIfPlayed(pos1, player1));
        assertEquals(2, game.getNbPointsIfPlayed(pos2, player1));
        assertEquals(2, game.getNbPointsIfPlayed(pos3, player1));
        assertEquals(2, game.getNbPointsIfPlayed(pos4, player1));

        assertTrue(b.placeDisk(player1, new Position(5, 3)));
        rule.turnDisks(game, new Position(5, 3));

        assertTrue(b.placeDisk(player2, new Position(3, 2)));
        rule.turnDisks(game, new Position(3,2));

        assertTrue(b.placeDisk(player1, new Position(2,3)));
        rule.turnDisks(game, new Position(2, 3));

        assertTrue(b.placeDisk(player2, new Position(5, 4)));
        rule.turnDisks(game, new Position(5, 4));

        Position pos5 = new Position(3,1);
        Position pos6 = new Position (4,1);
        Position pos7 = new Position(3,5);
        Position pos8 = new Position (4,5);
        Position pos9 = new Position (5,5);

        assertEquals (2, game.getNbPointsIfPlayed(pos5, player1));
        assertEquals (2, game.getNbPointsIfPlayed(pos6, player1));
        assertEquals (3, game.getNbPointsIfPlayed(pos7, player1));
        assertEquals (2, game.getNbPointsIfPlayed(pos8, player1));
        assertEquals (3, game.getNbPointsIfPlayed(pos9, player1));

        assertTrue(b.placeDisk(player1,pos6));
        rule.turnDisks(game, pos6);

        assertTrue(b.placeDisk(player2, new Position(1,3)));
        rule.turnDisks(game, new Position(1, 3));

        Position pos10 = new Position(1,4);

        assertEquals(2,game.getNbPointsIfPlayed(pos10, player1));

        assertTrue(b.placeDisk(player1,pos9));
        rule.turnDisks(game, pos9);

        assertTrue(b.placeDisk(player2, new Position(5,0)));
        rule.turnDisks(game, new Position(5, 0));

        assertEquals(3, game.getNbPointsIfPlayed(new Position(2,2), player1));
    }

    @Test
    public void testPlayOthello() throws PlayException {
        HumanPlayer player1 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        HumanPlayer player2 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Rule rule = new OthelloRule();
        Game game = new Game(UUID.randomUUID(), player1, player2, rule);
        Board b = game.getBoard();

        assertEquals(player1, game.getCurrentPlayer());
        assertEquals(player2, game.getOtherPlayer());

        Position pos1 = new Position(5,3);
        game.play(player1, pos1);

        assertEquals(3, game.getPlayablePositions(player1).length);
        assertEquals(3, game.getPlayablePositions(player2).length);
    }

    @Test
    public void testPlayReversi() throws PlayException {
        HumanPlayer player1 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        HumanPlayer player2 = new HumanPlayer(UUID.randomUUID(), "", "", "");
        Rule rule = new ReversiRule();
        Game game = new Game(UUID.randomUUID(), player1, player2, rule);
        Board b = game.getBoard();

        assertEquals(player1, game.getCurrentPlayer());
        assertEquals(player2, game.getOtherPlayer());

        assertEquals(State.INIT, game.getState());

        game.play(player1, new Position(3,4));
        game.play(player2, new Position(3,3));
        game.play(player1, new Position(4,3));
        game.play(player2, new Position(4,4));

        assertEquals(State.PLAY, game.getState());

        game.play(player1, new Position(5,4));

        assertEquals(3, game.getPlayablePositions(player1).length);
        assertEquals(3, game.getPlayablePositions(player2).length);
    }
}
