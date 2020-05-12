package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Pan;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PanTest {

    @Test
    public void Message() {
        GodCard pan = new Pan("Pan", "path", "descr");
        String s = pan.getMessage();
        assertNull(s);
    }

    @Test
    public void Winner(){
        Board board = new Board();
        board.getGrid()[1][1].setLevel(2);
        Builder builder1 = new Builder(new Position(1,1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        GodCard pan =new Pan("pan", "path", "description");
        int x =pan.move(board,builder1,board.getGrid()[1][0],false);
        assertEquals(x,2);
    }

    @Test
    public void Winner2(){
        Board board = new Board();
        board.getGrid()[1][1].setLevel(2);
        board.getGrid()[1][0].setLevel(3);
        Builder builder1 = new Builder(new Position(1,1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        GodCard pan =new Pan("pan", "path", "description");
        int x =pan.move(board,builder1,board.getGrid()[1][0],false);
        assertEquals(x,2);
    }

    @Test
    public void NotWinner(){
        Board board = new Board();
        board.getGrid()[1][1].setLevel(1);
        Builder builder1 = new Builder(new Position(1,1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        GodCard pan =new Pan("pan", "path", "description");
        int x =pan.move(board,builder1,board.getGrid()[1][0],false);
        assertEquals(x,1);
    }
}
