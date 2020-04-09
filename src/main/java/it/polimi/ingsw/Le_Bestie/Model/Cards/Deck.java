package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private static ArrayList<GodCard> deck;
    private static String godCard2PlayersPath="DatabaseGodCard/Deck2Players.csv";
    private static String godCard3PlayersPath="DatabaseGodCard/Deck3Players.csv";

    public Deck(int numplayers) throws IOException{
        if(numplayers==2)
        {
            deck=loadsCardFromFile2Players(godCard2PlayersPath);
        }
        else
        {
            deck=loadsCardFromFile2Players(godCard3PlayersPath);

        }
        this.shuffleDeck();

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

    public ArrayList<GodCard> loadsCardFromFile2Players(String godCard2PlayersPath)throws IOException
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
        FileReader fileReader= new FileReader(godCard2PlayersPath);
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

    private void shuffleDeck(){
        Collections.shuffle(deck);
    }

}
