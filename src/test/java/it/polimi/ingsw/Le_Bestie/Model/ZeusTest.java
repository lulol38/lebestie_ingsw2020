package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Zeus;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ZeusTest {
    @Test
    public void Build() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        GodCard zeus = new Zeus("Zeus", "path", "descr");

        int x = zeus.build(board, builder1, board.getGrid()[1][1], false);
        assertEquals(x, 1);
        x = zeus.build(board, builder1, board.getGrid()[1][0], false);
        assertEquals(x, 1);
        x = zeus.build(board, builder1, board.getGrid()[3][3], false);
        assertEquals(x, 0);
    }
}
