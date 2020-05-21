package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Hestia;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HestiaTest {

    @Test
    public void Message() {
        GodCard hestia = new Hestia("Hestia", "path", "descr");
        String s = hestia.getMessage();
        assertEquals(s, "Do you want to build one additional time? \n(NOT on a perimeter space!)");
    }

    @Test
    public void BuildWIthoutPower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        GodCard hestia = new Hestia("Hestia", "path", "descr");

        int x = hestia.build(board, builder1, board.getGrid()[0][4], false);
        assertEquals(x, 0);
        x = hestia.build(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x, 3);
        x = hestia.build(board, builder1, board.getGrid()[0][0], true);
        assertEquals(x, 1);
    }

    @Test
    public void BuildWIthPower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        GodCard hestia = new Hestia("Hestia", "path", "descr");

        int x = hestia.build(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x, 3);
        x = hestia.build(board, builder1, board.getGrid()[0][1], false);
        assertEquals(x, 0);
        x = hestia.build(board, builder1, board.getGrid()[3][3], false);
        assertEquals(x, 0);
        x = hestia.build(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x, 0);
        x = hestia.build(board, builder1, board.getGrid()[2][2], false);
        assertEquals(x, 1);
    }

    @Test
    public void BuildCantUsePower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(0, 0));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        GodCard hestia = new Hestia("Hestia", "path", "descr");
        board.getGrid()[1][0].setLevel(4);
        board.getGrid()[1][1].setLevel(4);
        board.getGrid()[0][1].setLevel(3);

        int x = hestia.build(board, builder1, board.getGrid()[0][1], false);
        assertEquals(x, 1);

    }

    @Test
    public void BuildCantUsePower2() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(0, 0));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        GodCard hestia = new Hestia("Hestia", "path", "descr");
        board.getGrid()[1][1].setLevel(4);

        int x = hestia.build(board, builder1, board.getGrid()[0][1], false);
        assertEquals(x, 1);

    }

}
