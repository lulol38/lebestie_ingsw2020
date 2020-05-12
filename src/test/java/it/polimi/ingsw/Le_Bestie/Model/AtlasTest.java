package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Apollo;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Atlas;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class AtlasTest {

    @Test
    public void Message(){
        GodCard atlas=new Atlas("Atlas", "path", "descr");
        String s = atlas.getMessage();
        assertEquals(s, "Do you want to build a dome on this cell?");
    }

    @Test
    public void BuildDome(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        GodCard atlas=new Atlas("Atlas", "path", "descr");
        int x=atlas.build(board, builder1,board.getGrid()[4][2],false);
        assertEquals(x,0);

        x=atlas.build(board, builder1,board.getGrid()[2][2],false);
        assertEquals(x,4);
        x=atlas.build(board, builder1,board.getGrid()[2][2],false);
        assertEquals(x,1);
        assertEquals(board.getGrid()[2][2].getLevel(),4);
    }

    @Test
    public void BuildNotPower(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        GodCard atlas=new Atlas("Atlas", "path", "descr");
        int x=atlas.build(board, builder1,board.getGrid()[2][2],false);
        assertEquals(x,4);
        x=atlas.build(board, builder1,board.getGrid()[2][2],true);
        assertEquals(x,1);
        assertEquals(board.getGrid()[2][2].getLevel(),1);
    }

    @Test
    public void BuildNoChoice(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        Cell c=board.getGrid()[2][2];
        c.setLevel(3);

        GodCard atlas=new Atlas("Atlas", "path", "descr");
        int x=atlas.build(board, builder1,c,false);
        assertEquals(x,1);
        assertEquals(board.getGrid()[2][2].getLevel(),4);
    }


}
