package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class BuilderTest {

    @Test
    public void BuilderTest(){
        Board board = new Board();

        Builder builder1 = new Builder(new Position(1,1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        assertNotNull(board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()]);
        //assertEquals(builder1.);

        Builder builder2 = new Builder(new Position(4,4));
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);
        assertNotNull(board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()]);
    }
}
