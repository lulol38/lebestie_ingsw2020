package it.polimi.ingsw.Le_Bestie.Model.Cards;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class Deck describes a generic deck(an arraylist of God cards)
 * @author Davide Carini
 */

public class Deck {

    private ArrayList<GodCard> deck;

    //CONSTRUCTOR
    public Deck(){
        deck=new ArrayList<>();
    }

    //GETTERS
    public ArrayList<GodCard> getDeck() {
        return deck;
    }

    /**
     * This method add a card if is not null to the deck
     * @param card is the god card read from the json
     */
    public void addCard(GodCard card) {
        if (card == null) throw new NullPointerException("Card cannot be null");
        deck.add(card);
    }

    /**
     * This method returns a card to the player
     * @return god card draw in the arraylist
     */
    public GodCard draw() {
        try {
            return deck.remove(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * This method shuffle in random order the deck
     */
    public void shuffle(){ Collections.shuffle(deck); }

    /**
     * This method clear the deck
     */
    public void flush() {
        deck.clear();
    }

    /**
     *
     * @return the number of cards in the deck
     */
    public int numberOfCards(){
        return deck.size();
    }

    /*private int getRandomNumber(int max) {
        Random nRand = new Random();
        return nRand.nextInt(max);
    }
    */
}
