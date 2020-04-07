package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.util.ArrayList;

public class Deck {

    private ArrayList<GodCard> deck[];

    public Deck(){

    }

    public ArrayList<GodCard>[] getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<GodCard>[] deck) {
        this.deck = deck;
    }

    public void createDeck(ArrayList<GodCard> list){

    }

    public boolean assignCard(Player p){
        return false;
    }

    public void removeCard(GodCard gc){

    }

    public ArrayList<GodCard> loadCardsFromFile(String url){
        return null;
    }

    public void shuffleDeck(){

    }
}
