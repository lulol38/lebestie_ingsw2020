package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Builder.BuilderColor;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Poseidon;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Prometheus;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrometheusTest {

    @Test
    public void Message() {
        GodCard prom = new Prometheus("Prometheus", "path", "descr");
        String s = prom.getMessage();
        assertEquals(s, "Do you want to build on this cell before moving? (during the move you can't move up!!)");
    }

    @Test
    public void MoveWithoutPower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player pl1 = new Player("white", BuilderColor.WHITE);
        builder1.setPlayer(pl1);
        pl1.setBuilder1(builder1);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        GodCard prom = new Prometheus("Prometheus", "path", "descr");

        int x = prom.move(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x, 4);
        x = prom.move(board, builder1, board.getGrid()[4][4], true);
        assertEquals(x, 0);
        x = prom.move(board, builder1, board.getGrid()[0][0], true);
        assertEquals(x, 1);

    }

    @Test
    public void MoveWithPower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player pl1 = new Player("white", BuilderColor.WHITE);
        builder1.setPlayer(pl1);
        pl1.setBuilder1(builder1);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        GodCard prom = new Prometheus("Prometheus", "path", "descr");

        int x = prom.move(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x, 4);
        x = prom.move(board, builder1, board.getGrid()[4][0], false);
        assertEquals(x, 0);
        assertEquals(board.getGrid()[4][0].getLevel(),0);
        x = prom.move(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x, 0);
        assertEquals(board.getGrid()[0][0].getLevel(),1);
        x = prom.move(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x, 0);
        x = prom.move(board, builder1, board.getGrid()[3][3], false);
        assertEquals(x, 0);
        x = prom.move(board, builder1, board.getGrid()[0][1], false);
        assertEquals(x, 1);
    }

}
