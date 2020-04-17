package it.polimi.ingsw.Le_Bestie.Model;

import static org.junit.Assert.*;

import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Builder.BuilderColor;
import it.polimi.ingsw.Le_Bestie.Model.Cards.*;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;

public class PlayerTest {

    @BeforeEach
    public void setup(){
        Player player=new Player("");
    }

    @Test
    public void setPlayer(){
        String nickname="Davide";
        Player p=new Player(nickname);
        p.setGodCard(mock(GodCard.class));

        assertNotNull(p.getGodCard());
        assertEquals(nickname,p.getNickname());

        p.setBuilder1(mock(Builder.class));
        p.setBuilder2(mock(Builder.class));
        assertNotNull(p.getBuilder1());
        assertNotNull(p.getBuilder2());

        assertEquals(false,p.isDisabled());

        System.out.println(p.toString());
    }

    @Test
    public void setBuilder(){

        Position initialPosition=new Position(0,0);
        Builder b=new Builder(initialPosition);
        Player player=new Player("Davide");
        player.setBuilder1(b);

        player.getBuilder1().setColor(BuilderColor.BLUE);

        assertSame(BuilderColor.BLUE,player.getBuilder1().getColor());

    }

}
