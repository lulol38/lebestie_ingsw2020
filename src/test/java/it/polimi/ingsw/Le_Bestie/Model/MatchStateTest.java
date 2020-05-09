package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Builder.BuilderColor;
import it.polimi.ingsw.Le_Bestie.Model.Game.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MatchStateTest {

    private MatchState game;

    @BeforeEach
    private void before() {
        game = new MatchState();
    }

    @Test
    public void startMatch(){
        game=new MatchState();
        game.addPlayer("A");
        game.addPlayer("B");
        game.startGame();
        assertNotNull(game.getDeck());
        assertNotNull(game.getPlayers());
        for (Player p: game.getPlayers())
            System.out.println(p.getNickname() +" , "+p.getGodCard().getName());
    }

    @Test
    public void addPlayerInMatch(){
        game=new MatchState();
        game.addPlayer("name");
        assertEquals(1,game.getPlayers().size());
    }

    @Test
    public void playerInTurn(){
        game=new MatchState();
        game.addPlayer("p1");
        game.addPlayer("p2");
        game.addPlayer("p3");
        assertNotNull(game.getCurrentPlayer());
        assertEquals("p1",game.getCurrentPlayer().getNickname());

        game.nextTurn();
        assertNotNull(game.getCurrentPlayer());
        assertEquals("p2",game.getCurrentPlayer().getNickname());
    }

    @Test
    public void colorNotUsed(){
        game=new MatchState();
        game.addPlayer("a");

        for( BuilderColor c: game.builderColorNotUsed()){
            System.out.print(c.toString() +" ");
        }
        game.addPlayer("b");
        System.out.println();
        for( BuilderColor c: game.builderColorNotUsed()){
            System.out.print(c.toString() +" ");
        }
        game.addPlayer("c");
        for( BuilderColor c: game.builderColorNotUsed()){
            System.out.print(c.toString() +" ");
        }
    }
}
