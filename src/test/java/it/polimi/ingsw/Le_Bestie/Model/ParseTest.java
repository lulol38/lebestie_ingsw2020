package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Cards.Deck;
import it.polimi.ingsw.Le_Bestie.jsonParser.GodCardsParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParseTest {
    private Deck godCards;

    @BeforeEach
    public void before() {
        godCards = new Deck();
    }
    @Test
    public void parse2Players(){
        godCards= GodCardsParser.parseCards(2);
    }
    @Test
    public void parse3Players(){
        godCards= GodCardsParser.parseCards(3);
    }



}
