package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private static ArrayList<GodCard> deck;
    private static String godCard2PlayersPath="DatabaseGodCard/Deck2Players";
    private static String godCard3PlayersPath="DatabaseGodCard/Deck3Players";

    public Deck(){

    }

    public void setDeck(ArrayList<GodCard> deck) {
        this.deck = deck;
    }

    public void createDeck(ArrayList<GodCard> list){ }




    public ArrayList<GodCard> loadsCardFromFile(String godCard2PlayersPath)throws IOException
    {
        ArrayList<GodCard> gc=new ArrayList<GodCard>();
        String currentLine = "";
        FileReader fileReader= new FileReader(godCard2PlayersPath);
        BufferedReader br = new BufferedReader(fileReader);
        int c=1;
        while ((currentLine = br.readLine()) != null) {
            String[] s = currentLine.split(",");
            switch (s[0])
            {
                case "Apollo":gc.add(new Apollo(s[0]));break;
                case "Artemis":gc.add(new Artemis(s[0]));break;
                case "Prometheus":gc.add(new Prometheus(s[0]));break;
                case "Athena":gc.add(new Athena(s[0]));break;
                case "Atlas":gc.add(new Atlas(s[0]));break;
                case "Demeter":gc.add(new Demeter(s[0]));break;
                case "Hephaestus":gc.add(new Hephaestus(s[0]));break;
                case "Minotaur":gc.add(new Minotaur(s[0]));break;
                case "Pan":gc.add(new Pan(s[0]));break;

            }
            c++;
        }
        br.close();
        return gc;
    }


    public boolean assignCard(Player p){
        if(!deck.isEmpty())
        {
            GodCard gc=deck.remove(deck.size()-1);
            p.setGodCard(gc);
            return true;
        }
        else
            return false;
    }

    private void shuffleDeck(){
        Collections.shuffle(deck);
    }
}
