package it.polimi.ingsw.Le_Bestie.Model.Cards;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class Deck describes a generic deck(an arraylist of God cards)
 * @author Davide Carini
 */

public class Deck {

    private ArrayList<GodCard> deck;

    public Deck(){
        deck=new ArrayList<>();
    }

    public ArrayList<GodCard> getDeck() {
        return deck;
    }

    public void addCard(GodCard card) {
        if (card == null) throw new NullPointerException("Card cannot be null");
        deck.add(card);
    }

    public void shuffle(){

        Collections.shuffle(deck);
    }


    public GodCard draw() {
        try {
            return deck.remove(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public void flush() {
        deck.clear();
    }

    /*private int getRandomNumber(int max) {
        Random nRand = new Random();
        return nRand.nextInt(max);
    }
    */

    public int numberOfCards(){
        return deck.size();
    }


}
