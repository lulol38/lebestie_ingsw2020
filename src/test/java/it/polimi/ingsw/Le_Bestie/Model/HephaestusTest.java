package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Hephaestus;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HephaestusTest {


    @Test
    public void Message() {
        GodCard heph = new Hephaestus("Hephaestus", "path", "descr");
        String s = heph.getMessage();
        assertEquals(s, "Do you want to build one additional block\non top of your first block?");
    }

    @Test
    public void BuildWithPower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        GodCard heph = new Hephaestus("Hephaestus", "path", "descr");
        board.getGrid()[1][0].setLevel(4);

        int x=heph.build(board,builder1,board.getGrid()[0][4], false);
        assertEquals(x,0);
        x=heph.build(board,builder1,board.getGrid()[1][0], false);
        assertEquals(x,0);
        x=heph.build(board,builder1,board.getGrid()[0][0], false);
        assertEquals(x,4);
        x=heph.build(board,builder1,board.getGrid()[0][0], false);
        assertEquals(x,1);
    }

    @Test
    public void BuildCantUsePower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        GodCard heph = new Hephaestus("Demeter", "path", "descr");
        board.getGrid()[1][0].setLevel(3);

        int x=heph.build(board,builder1,board.getGrid()[1][0], false);
        assertEquals(x,1);
    }

    @Test
    public void BuildWithoutPower() {
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1, 1));
        Player player = new Player("player");
        player.setBuilder1(builder1);
        builder1.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        GodCard heph = new Hephaestus("Hephaestus", "path", "descr");


        int x=heph.build(board,builder1,board.getGrid()[1][0], false);
        assertEquals(x,4);
        x=heph.build(board,builder1,board.getGrid()[1][0], true);
        assertEquals(x,1);
    }


    }
