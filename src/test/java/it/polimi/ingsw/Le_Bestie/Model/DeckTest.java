package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Cards.*;
import it.polimi.ingsw.Le_Bestie.jsonParser.GodCardsParser;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    public void before() {
        deck = new Deck();
    }

    @Test
    public void deck(){

        deck=new Deck();
        deck.addCard(mock(Apollo.class));
        deck.addCard(mock(Artemis.class));
        deck.addCard(mock(Atlas.class));
        deck.shuffle();
        assertEquals(3, deck.numberOfCards());

        GodCard card1=deck.draw();
        GodCard card2=deck.draw();
        GodCard card3=deck.draw();
        assertNotNull(card1);
        assertNotNull(card2);
        assertNotNull(card3);

        assertEquals(0, deck.numberOfCards());
        deck.addCard(mock(Apollo.class));
        assertEquals(1, deck.numberOfCards());
        deck.flush();
        assertEquals(0, deck.numberOfCards());

    }

    @Test
    public void GodCardDeck2Players() {
        Deck deck2Players= GodCardsParser.parseCards(2);
        assertEquals(14,deck2Players.numberOfCards());
    }

    @Test
    public void GodCardDeck3Players(){
        Deck deck3Players= GodCardsParser.parseCards(3);
        assertEquals(13,deck3Players.numberOfCards());
    }

}
