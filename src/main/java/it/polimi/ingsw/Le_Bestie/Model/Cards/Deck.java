package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class Deck
 * describes a generic deck(an arraylist of God cards)
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

    public boolean assignCard(Player p){
        if(!deck.isEmpty())
        {
            GodCard card=deck.remove(deck.size()-1);
            p.setGodCard(card);
            return true;
        }
        else
            return false;
    }


    private ArrayList<GodCard> loadsCardFromFile2Players(String godCard2PlayersPath)throws IOException
    {
        ArrayList<GodCard> cards=new ArrayList<GodCard>();
        String currentLine = "";
        FileReader fileReader= new FileReader(godCard2PlayersPath);
        BufferedReader br = new BufferedReader(fileReader);
        int c=1;
        while ((currentLine = br.readLine()) != null) {
            String[] s = currentLine.split(",");
            switch (s[0])
            {
                case "Apollo":cards.add(new Apollo(s[0]));break;
                case "Artemis":cards.add(new Artemis(s[0]));break;
                case "Prometheus":cards.add(new Prometheus(s[0]));break;
                case "Athena":cards.add(new Athena(s[0]));break;
                case "Atlas":cards.add(new Atlas(s[0]));break;
                case "Demeter":cards.add(new Demeter(s[0]));break;
                case "Hephaestus":cards.add(new Hephaestus(s[0]));break;
                case "Minotaur":cards.add(new Minotaur(s[0]));break;
                case "Pan":cards.add(new Pan(s[0]));break;
            }
            c++;
        }
        br.close();
        return cards;
    }

    public ArrayList<GodCard> loadsCardFromFile3Players(String godCard3PlayersPath)throws IOException
    {
        ArrayList<GodCard> cards=new ArrayList<GodCard>();
        String currentLine = "";
        FileReader fileReader= new FileReader(godCard3PlayersPath);
        BufferedReader br = new BufferedReader(fileReader);
        int c=1;
        while ((currentLine = br.readLine()) != null) {
            String[] s = currentLine.split(",");
            switch (s[0])
            {
                case "Apollo":cards.add(new Apollo(s[0]));break;
                case "Artemis":cards.add(new Artemis(s[0]));break;
                case "Athena":cards.add(new Athena(s[0]));break;
                case "Atlas":cards.add(new Atlas(s[0]));break;
                case "Demeter":cards.add(new Demeter(s[0]));break;
                case "Minotaur":cards.add(new Minotaur(s[0]));break;
                case "Pan":cards.add(new Pan(s[0]));break;
            }
            c++;
        }
        br.close();
        return cards;
    }

    public void addCard(GodCard card) {
        if (card == null) throw new NullPointerException("Card cannot be null");
        deck.add(card);
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    private int getRandomNumber(int max) {
        Random nRand = new Random();
        return nRand.nextInt(max);
    }
    public GodCard draw() {
        try {
            return deck.remove(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }


}
