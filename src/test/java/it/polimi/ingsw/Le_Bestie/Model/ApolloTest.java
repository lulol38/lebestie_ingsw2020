package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Builder.BuilderColor;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Apollo;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApolloTest {

    @Test
    public void Move(){
        Board board = new Board();

        Cell cell= board.getGrid()[1][2];
        Builder builder1 = new Builder(new Position(1,1));
        Builder builder2=new Builder (new Position(1,3));
        Player pl1=new Player("luca", BuilderColor.WHITE);
        Player pl2=new Player("davo", BuilderColor.BLUE);
        builder1.setPlayer(pl1);
        builder2.setPlayer(pl2);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);

        GodCard apollo=new Apollo("Apollo", "path", "descr");
        int x=apollo.move(board, builder1,cell,false);
        assertEquals(x,1);

        x=apollo.move(board, builder1,board.getGrid()[4][2],false);
        assertEquals(x,0);

        x=apollo.move(board, builder1,board.getGrid()[1][3],false);
        assertEquals(x,1);

    }

    @Test
    public void Winner(){
        Board board = new Board();
        Cell c= board.getGrid()[1][1];
        Cell c2= board.getGrid()[1][2];
        c.setLevel(2);
        c2.setLevel(3);

        Builder builder1 = new Builder(new Position(1,1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        GodCard apollo=new Apollo("Apollo", "path", "descr");
        int x=apollo.move(board, builder1,c2,false);
        assertEquals(x,2);

    }

    @Test
    public void Build(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));


        GodCard apollo=new Apollo("Apollo", "path", "descr");
        int x=apollo.build(board, builder1,board.getGrid()[4][2],false);
        assertEquals(x,0);

        x=apollo.build(board, builder1,board.getGrid()[2][2],false);
        assertEquals(x,1);
    }

    @Test
    public void HasLost(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(0,0));
        Builder builder2 = new Builder(new Position(0,1));
        Player pl1=new Player("luca", BuilderColor.WHITE);
        pl1.setBuilder1(builder1);
        pl1.setBuilder2(builder2);
        GodCard apollo=new Apollo("Apollo", "path", "descr");
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);

        board.getGrid()[1][0].setLevel(3);
        board.getGrid()[1][1].setLevel(3);
        board.getGrid()[1][2].setLevel(3);
        board.getGrid()[0][2].setLevel(3);

        int x=apollo.HasLost(pl1,board);
        assertEquals(x,1);
    }

    @Test
    public void HasLostPower(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(0,0));
        Builder builder2 = new Builder(new Position(0,1));
        Builder builder3 = new Builder(new Position(4,4));
        Player pl1=new Player("luca", BuilderColor.WHITE);
        Player pl2=new Player("davo", BuilderColor.BLUE);

        pl1.setBuilder1(builder1);
        builder1.setPlayer(pl1);
        pl2.setBuilder1(builder2);
        builder2.setPlayer(pl2);
        pl1.setBuilder2(builder3);
        builder3.setPlayer(pl1);
        GodCard apollo=new Apollo("Apollo", "path", "descr");
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);

        board.getGrid()[1][0].setLevel(3);
        board.getGrid()[1][1].setLevel(3);
        board.getGrid()[1][2].setLevel(3);
        board.getGrid()[0][2].setLevel(3);
        board.getGrid()[4][3].setLevel(3);
        board.getGrid()[3][3].setLevel(3);
        board.getGrid()[3][4].setLevel(3);

        int x=apollo.HasLost(pl1,board);
        assertEquals(x,0);
    }

}
