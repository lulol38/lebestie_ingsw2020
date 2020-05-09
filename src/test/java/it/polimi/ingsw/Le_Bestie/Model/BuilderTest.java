package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class BuilderTest {

    @Test
    public void Moving(){
        Board board = new Board();

        Builder builder1 = new Builder(new Position(1,1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        assertNotNull(board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()]);
        assertEquals(builder1.getPosition().getX(), 1);
        assertEquals(builder1.getPosition().getY(), 1);

        Builder builder2 = new Builder(new Position(4,4));
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);
        assertNotNull(board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()]);
        assertEquals(builder2.getPosition().getX(), 4);
        assertEquals(builder2.getPosition().getY(), 4);

        assertEquals(builder1.possibleMoves(board).size(), 8);
        assertEquals(builder2.possibleMoves(board).size(), 3);
    }

    @Test
    public void NotMoving(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        Builder builder2 = new Builder(new Position(4,4));
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);

        board.getGrid()[0][0].setLevel(4);
        board.getGrid()[0][1].setLevel(4);
        board.getGrid()[0][2].setLevel(4);
        board.getGrid()[1][0].setLevel(4);
        board.getGrid()[1][2].setLevel(4);
        board.getGrid()[2][0].setLevel(4);
        board.getGrid()[2][1].setLevel(4);
        board.getGrid()[2][2].setLevel(4);

        assertEquals(builder1.possibleMoves(board).size(), 0);

        board.getGrid()[2][2].setLevel(0);
        board.getGrid()[2][2].setBuilder(builder2);
        board.getGrid()[4][4].setBuilder(null);

        assertEquals(builder1.possibleMoves(board).size(), 0);

        board.getGrid()[2][2].setLevel(0);
        board.getGrid()[2][2].setBuilder(null);
        board.getGrid()[2][2].setDisabled(false);
        board.getGrid()[4][4].setBuilder(builder2);

        assertEquals(builder1.possibleMoves(board).size(), 1);
    }

    @Test
    public void NotBuilding(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        board.getGrid()[0][0].setLevel(4);
        board.getGrid()[0][1].setLevel(4);
        board.getGrid()[0][2].setLevel(4);
        board.getGrid()[1][0].setLevel(4);
        board.getGrid()[1][2].setLevel(4);
        board.getGrid()[2][0].setLevel(4);
        board.getGrid()[2][1].setLevel(4);
        board.getGrid()[2][2].setLevel(4);

        assertEquals(builder1.possibleBuilds(board).size(), 0);

        board.getGrid()[2][2].setLevel(0);
        board.getGrid()[2][2].setDisabled(false);

        assertEquals(builder1.possibleBuilds(board).size(), 1);

        board.getGrid()[2][2].setLevel(3);

        assertEquals(builder1.possibleBuilds(board).size(), 1);
    }
}
