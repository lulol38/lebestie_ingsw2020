package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Ares;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class AresTest {

    @Test
    public void Message(){
        GodCard ares=new Ares("Ares", "path", "descr");
        String s = ares.getMessage();
        assertEquals(s, "Do tou want to remove an unoccupied block\n(not dome) neighboring your unmoved Worker?");
    }

    @Test
    public void BuildCantPower(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));
        Builder builder2 = new Builder(new Position(4,4));
        Player player=new Player("player");
        player.setBuilder2(builder1);
        player.setBuilder1(builder2);
        builder1.setPlayer(player);
        builder2.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);

        GodCard ares=new Ares("Ares", "path", "descr");
        int x=ares.build(board, builder1,board.getGrid()[2][2],false);
        assertEquals(x,1);
    }

    @Test
    public void BuildCantPower2(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));
        Builder builder2 = new Builder(new Position(4,4));
        Player player=new Player("player");
        player.setBuilder1(builder1);
        player.setBuilder2(builder2);
        builder1.setPlayer(player);
        builder2.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);
        board.getGrid()[3][4].setLevel(4);
        board.getGrid()[3][3].setLevel(4);
        board.getGrid()[4][3].setLevel(4);

        GodCard ares=new Ares("Ares", "path", "descr");
        int x=ares.build(board, builder1,board.getGrid()[3][2],false);
        assertEquals(x,0);
        x=ares.build(board, builder1,board.getGrid()[2][2],false);
        assertEquals(x,1);
    }


    @Test
    public void BuildWithPower(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));
        Builder builder2 = new Builder(new Position(4,4));
        Player player=new Player("player");
        player.setBuilder1(builder1);
        player.setBuilder2(builder2);
        builder1.setPlayer(player);
        builder2.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);
        board.getGrid()[4][3].setLevel(2);

        GodCard ares=new Ares("Ares", "path", "descr");
        int x=ares.build(board, builder1,board.getGrid()[2][2],false);
        assertEquals(x,3);
        x=ares.build(board, builder1,board.getGrid()[2][2],false);
        assertEquals(x,0);
        x=ares.build(board, builder1,board.getGrid()[3][4],false);
        assertEquals(x,0);
        x=ares.build(board, builder1,board.getGrid()[4][3],false);
        assertEquals(x,1);
    }


    @Test
    public void BuildWithoutPower(){
        Board board = new Board();
        Builder builder1 = new Builder(new Position(1,1));
        Builder builder2 = new Builder(new Position(4,4));
        Player player=new Player("player");
        player.setBuilder1(builder1);
        player.setBuilder2(builder2);
        builder1.setPlayer(player);
        builder2.setPlayer(player);
        board.getGrid()[builder1.getPosition().getX()][builder1.getPosition().getY()].setBuilder(builder1);
        board.getGrid()[builder2.getPosition().getX()][builder2.getPosition().getY()].setBuilder(builder2);
        board.getGrid()[4][3].setLevel(2);

        GodCard ares=new Ares("Ares", "path", "descr");
        int x=ares.build(board, builder1,board.getGrid()[2][2],false);
        assertEquals(x,3);
        x=ares.build(board, builder1,board.getGrid()[2][2],true);
        assertEquals(x,1);
    }

}
