package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.util.ArrayList;


public class Deck {

    private static ArrayList<GodCard> deck[];
    private static String godCard2PlayersPath="DatabaseGodCard/Deck2Players";
    private static String godCard3PlayersPath="DatabaseGodCard/Deck3Players";

    public Deck(){

    }

    public void loadsCardFromFile(String godCard2PlayersPath)
    {


    }

    public void setDeck(ArrayList<GodCard>[] deck) {
        this.deck = deck;
    }

    public void createDeck(ArrayList<GodCard> list){ }

    public boolean assignCard(Player p){

        //p.setGodCard();
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
