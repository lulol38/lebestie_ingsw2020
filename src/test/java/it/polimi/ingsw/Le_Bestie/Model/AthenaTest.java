package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Athena;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AthenaTest {

    @Test
    public void Move() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[3][2].setLevel(1);

        GodCard athena = new Athena("Athena", "path", "descr");
        int x = athena.move(board, builder1, board.getGrid()[4][2], false);
        assertEquals(x, 0);
        x = athena.move(board, builder1, board.getGrid()[2][2], false);
        assertEquals(x, 1);
        x = athena.move(board, builder1, board.getGrid()[3][2], false);
        assertEquals(x, 1);
        x = athena.move(board, builder1, board.getGrid()[2][2], false);
        assertEquals(x, 1);

    }
}

