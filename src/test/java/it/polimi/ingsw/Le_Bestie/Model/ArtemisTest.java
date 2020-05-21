package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Artemis;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArtemisTest {

    @Test
    public void Message() {
        GodCard artemis = new Artemis("Artemis", "path", "descr");
        String s = artemis.getMessage();
        assertEquals(s, "Do you want to move one additional time?\n(NOT back!!)");
    }

    @Test
    public void Move() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        GodCard artemis = new Artemis("Artemis", "path", "descr");
        int x = artemis.move(board, builder1, board.getGrid()[4][2], false);
        assertEquals(x, 0);
        x = artemis.move(board, builder1, board.getGrid()[2][2], false);
        assertEquals(x, 3);
        x = artemis.move(board, builder1, board.getGrid()[1][1], false);
        assertEquals(x, 0);
        x = artemis.move(board, builder1, board.getGrid()[1][2], false);
        assertEquals(x, 1);

    }

    @Test
    public void Move2() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[0][1].setLevel(3);
        board.getGrid()[1][0].setLevel(3);

        GodCard artemis = new Artemis("Artemis", "path", "descr");
        int x = artemis.move(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x, 1);
    }

    @Test
    public void Build(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);

        GodCard artemis = new Artemis("Artemis", "path", "descr");
        int x = artemis.build(board, builder1, board.getGrid()[0][0], false);
        assertEquals(x, 1);
    }

}