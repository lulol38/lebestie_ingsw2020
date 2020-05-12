package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Builder.BuilderColor;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Poseidon;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PoseidonTest {

    @Test
    public void Message() {
        GodCard poseidon = new Poseidon("Poseidon", "path", "descr");
        String s = poseidon.getMessage();
        assertEquals(s, "Do you want to build another time with the not moved builder?");
    }

    @Test
    public void MoveWithoutPower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Builder builder2 = new Builder(new Position(2, 2));
        Player pl1 = new Player("white", BuilderColor.WHITE);
        builder1.setPlayer(pl1);
        builder2.setPlayer(pl1);
        pl1.setBuilder1(builder1);
        pl1.setBuilder2(builder2);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);

        GodCard poseidon = new Poseidon("Poseidon", "path", "descr");
        int x = poseidon.build(board, builder2, board.getGrid()[4][4], false);
        assertEquals(x, 0);
        x = poseidon.build(board, builder2, board.getGrid()[3][3], false);
        assertEquals(x, 3);
        x = poseidon.build(board, builder2, board.getGrid()[3][3], true);
        assertEquals(x, 1);
    }

    @Test
    public void MoveWithPower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Builder builder2 = new Builder(new Position(2, 2));
        Player pl1 = new Player("white", BuilderColor.WHITE);
        builder1.setPlayer(pl1);
        builder2.setPlayer(pl1);
        pl1.setBuilder1(builder1);
        pl1.setBuilder2(builder2);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);
        board.getGrid()[0][0].setLevel(2);

        GodCard poseidon = new Poseidon("Poseidon", "path", "descr");
        int x = poseidon.build(board, builder2, board.getGrid()[3][2], false);
        assertEquals(x, 3);
        x = poseidon.build(board, builder2, board.getGrid()[0][0], false);
        assertEquals(x, 3);
        x = poseidon.build(board, builder2, board.getGrid()[4][0], false);
        assertEquals(x, 0);
        x = poseidon.build(board, builder2, board.getGrid()[0][0], false);
        assertEquals(x, 3);
        x = poseidon.build(board, builder2, board.getGrid()[0][0], false);
        assertEquals(x, 0);
        x = poseidon.build(board, builder2, board.getGrid()[0][1], false);
        assertEquals(x, 1);
    }

    @Test
    public void MoveWithPower2() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(0, 0));
        Builder builder2 = new Builder(new Position(2, 2));
        Player pl1 = new Player("white", BuilderColor.WHITE);
        builder1.setPlayer(pl1);
        builder2.setPlayer(pl1);
        pl1.setBuilder1(builder1);
        pl1.setBuilder2(builder2);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);
        board.getGrid()[0][1].setLevel(4);
        board.getGrid()[1][1].setLevel(4);
        board.getGrid()[1][0].setLevel(3);

        GodCard poseidon = new Poseidon("Poseidon", "path", "descr");
        int x = poseidon.build(board, builder2, board.getGrid()[3][2], false);
        assertEquals(x, 3);
        x = poseidon.build(board, builder2, board.getGrid()[1][0], false);
        assertEquals(x, 1);
    }

    @Test
    public void MoveCantUsePower() {
        Board board = new Board();
        board.getGrid()[1][1].setLevel(2);
        Builder builder1 = new Builder(new Position(1, 1));
        Builder builder2 = new Builder(new Position(2, 2));
        Player pl1 = new Player("white", BuilderColor.WHITE);
        builder1.setPlayer(pl1);
        builder2.setPlayer(pl1);
        pl1.setBuilder1(builder1);
        pl1.setBuilder2(builder2);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);

        GodCard poseidon = new Poseidon("Poseidon", "path", "descr");
        int x = poseidon.build(board, builder2, board.getGrid()[3][2], false);
        assertEquals(x, 1);
    }
}

