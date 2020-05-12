package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Demeter;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DemeterTest {

    @Test
    public void Message() {
        GodCard demeter = new Demeter("Demeter", "path", "descr");
        String s = demeter.getMessage();
        assertEquals(s, "Do you want to build one additional time? (NOT on the same space!)");
    }

    @Test
    public void BuildWithPower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        GodCard demeter = new Demeter("Demeter", "path", "descr");
        int x = demeter.build(board, builder1, board.getGrid()[4][2], false);
        assertEquals(x, 0);
        x = demeter.build(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x, 3);
        x = demeter.build(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x, 0);
        x = demeter.build(board, builder1, board.getGrid()[4][2], false);
        assertEquals(x, 0);
        x = demeter.build(board, builder1, board.getGrid()[1][0], false);
        assertEquals(x, 1);
    }

    @Test
    public void BuildWithoutPower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        GodCard demeter = new Demeter("Demeter", "path", "descr");
        int x = demeter.build(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x, 3);
        x = demeter.build(board, builder1, board.getGrid()[0][0], true);
        assertEquals(x, 1);
    }

    @Test
    public void CantUsePower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(0, 0));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[1][0].setLevel(4);
        board.getGrid()[0][1].setLevel(4);

        GodCard demeter = new Demeter("Demeter", "path", "descr");
        int x = demeter.build(board, builder1, board.getGrid()[1][1], false);
        assertEquals(x, 1);
    }
}
