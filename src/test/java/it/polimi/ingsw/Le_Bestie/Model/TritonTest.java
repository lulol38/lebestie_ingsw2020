package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Builder.BuilderColor;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Triton;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TritonTest {

    @Test
    public void Message() {
        GodCard triton = new Triton("Triton", "path", "descr");
        String s = triton.getMessage();
        assertEquals(s, "Do you want to move one additional time?");
    }

    @Test
    public void Path() {
        GodCard triton = new Triton("Triton", "path", "descr");
        String s = triton.getPath();
        assertEquals(s, "path");
    }

    @Test
    public void Description() {
        GodCard triton = new Triton("Triton", "path", "descr");
        String s = triton.getDescription();
        assertEquals(s, "descr");
    }

    @Test
    public void MoveWithPower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player pl1 = new Player("white", BuilderColor.WHITE);
        builder1.setPlayer(pl1);
        pl1.setBuilder1(builder1);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        GodCard triton = new Triton("Triton", "path", "descr");
        int x = triton.move(board, builder1, board.getGrid()[4][4], false);
        assertEquals(x,0);
        x = triton.move(board, builder1, board.getGrid()[2][1], false);
        assertEquals(x,1);
        x = triton.move(board, builder1, board.getGrid()[2][0], false);
        assertEquals(x,3);
        x = triton.move(board, builder1, board.getGrid()[4][3], false);
        assertEquals(x,0);
        x = triton.move(board, builder1, board.getGrid()[1][0], false);
        assertEquals(x,1);
    }

    @Test
    public void MoveCantUsePowerAndBuild() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player pl1 = new Player("white", BuilderColor.WHITE);
        builder1.setPlayer(pl1);
        pl1.setBuilder1(builder1);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[1][0].setLevel(4);
        board.getGrid()[1][1].setLevel(3);
        board.getGrid()[0][1].setLevel(4);

        GodCard triton = new Triton("Triton", "path", "descr");
        int x = triton.move(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x,1);

        x=triton.build(board,builder1,board.getGrid()[1][1],false);
        assertEquals(x,1);
    }

    @Test
    public void HasLost(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(0, 1));
        Builder builder2 = new Builder(new Position(0, 0));
        Player pl1 = new Player("white", BuilderColor.WHITE);
        builder1.setPlayer(pl1);
        builder2.setPlayer(pl1);
        pl1.setBuilder1(builder1);
        pl1.setBuilder2(builder2);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);
        board.getGrid()[1][0].setLevel(4);
        board.getGrid()[1][1].setLevel(3);
        board.getGrid()[0][2].setLevel(4);
        board.getGrid()[1][2].setLevel(4);

        GodCard triton = new Triton("Triton", "path", "descr");
        int x = triton.HasLost(pl1,board);
        assertEquals(x,1);
    }

    @Test
    public void NotHasLost(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(0, 1));
        Builder builder2 = new Builder(new Position(0, 0));
        Player pl1 = new Player("white", BuilderColor.WHITE);
        builder1.setPlayer(pl1);
        builder2.setPlayer(pl1);
        pl1.setBuilder1(builder1);
        pl1.setBuilder2(builder2);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);
        board.getGrid()[1][1].setLevel(3);
        board.getGrid()[0][2].setLevel(4);
        board.getGrid()[1][2].setLevel(4);

        GodCard triton = new Triton("Triton", "path", "descr");
        int x = triton.HasLost(pl1,board);
        assertEquals(x,0);
    }




}
