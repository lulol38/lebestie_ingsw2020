package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Builder.BuilderColor;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Minotaur;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MinotaurTest {

    @Test
    public void UsualMove(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        GodCard mino=new Minotaur("Minotaur", "path", "descr");
        int x=mino.move(board, builder1,board.getGrid()[3][3],false);
        assertEquals(x,0);
        x=mino.move(board, builder1,board.getGrid()[1][0],false);
        assertEquals(x,1);

    }

    @Test
    public void Move(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));
        Builder builder2=new Builder (new Position(2,2));
        Player pl1=new Player("white", BuilderColor.WHITE);
        Player pl2=new Player("blue", BuilderColor.BLUE);
        builder1.setPlayer(pl1);
        builder2.setPlayer(pl2);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);

        GodCard mino=new Minotaur("Minotaur", "path", "descr");
        int x=mino.move(board, builder1,board.getGrid()[2][2],false);
        assertEquals(x,1);
        assertEquals(builder2.getPosition().getX(),3);
        assertEquals(builder2.getPosition().getY(),3);
    }

    @Test
    public void Move2(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));
        Builder builder2=new Builder (new Position(0,0));
        Player pl1=new Player("white", BuilderColor.WHITE);
        Player pl2=new Player("blue", BuilderColor.BLUE);
        builder1.setPlayer(pl1);
        builder2.setPlayer(pl2);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);

        GodCard mino=new Minotaur("Minotaur", "path", "descr");
        int x=mino.move(board, builder1,board.getGrid()[0][0],false);
        assertEquals(x,0);
        assertEquals(builder2.getPosition().getX(),0);
        assertEquals(builder2.getPosition().getY(),0);
    }

    @Test
    public void Winner(){
        Board board = new Board();
        board.getGrid()[1][1].setLevel(2);
        board.getGrid()[2][2].setLevel(3);
        Builder builder1 = new Builder(new Position(1,1));
        Builder builder2=new Builder (new Position(2,2));
        Player pl1=new Player("white", BuilderColor.WHITE);
        Player pl2=new Player("blue", BuilderColor.BLUE);
        builder1.setPlayer(pl1);
        builder2.setPlayer(pl2);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);

        GodCard mino=new Minotaur("Minotaur", "path", "descr");
        int x=mino.move(board, builder1,board.getGrid()[2][2],false);
        assertEquals(x,2);
    }

    @Test
    public void HasLost(){
        Board board = new Board();
        board.getGrid()[1][1].setLevel(2);
        board.getGrid()[0][1].setLevel(2);
        board.getGrid()[2][0].setLevel(2);
        board.getGrid()[2][1].setLevel(2);
        Builder builder1 = new Builder(new Position(0,0));
        Builder builder2=new Builder (new Position(1,0));
        Player pl1=new Player("white", BuilderColor.WHITE);
        builder1.setPlayer(pl1);
        builder2.setPlayer(pl1);
        pl1.setBuilder1(builder1);
        pl1.setBuilder2(builder2);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);


        GodCard mino=new Minotaur("Minotaur", "path", "descr");
        int x=mino.HasLost(pl1, board);
        assertEquals(x,1);
    }

    @Test
    public void HasLost2(){
        Board board = new Board();
        board.getGrid()[1][1].setLevel(2);
        board.getGrid()[2][0].setLevel(2);
        board.getGrid()[2][1].setLevel(2);
        Builder builder1 = new Builder(new Position(0,0));
        Builder builder2=new Builder (new Position(1,0));
        Builder builder3=new Builder (new Position(0,1));
        Player pl1=new Player("white", BuilderColor.WHITE);
        Player pl2=new Player("blue", BuilderColor.BLUE);
        builder1.setPlayer(pl1);
        builder2.setPlayer(pl1);
        pl1.setBuilder1(builder1);
        pl1.setBuilder2(builder2);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);


        GodCard mino=new Minotaur("Minotaur", "path", "descr");
        int x=mino.HasLost(pl1, board);
        assertEquals(x,0);
    }

}



