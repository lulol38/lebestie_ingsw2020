package it.polimi.ingsw.Le_Bestie.jsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.Le_Bestie.Model.Cards.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Class GodCardsParser that creates a deck of cards
 * @author Davide Carini
 */

public class GodCardsParser {

    public static Deck parseCards(int numberOfPlayers) {

        Deck deck = new Deck();
        String path = "json/godCards.json";

        InputStream inputstr = GodCardsParser.class.getClassLoader().getResourceAsStream(path);

        if (inputstr == null) {
            //exception
        }

        JsonParser parser = new JsonParser();
        JsonObject json;
        json = parser.parse(new InputStreamReader(inputstr)).getAsJsonObject();
        JsonArray godcards = json.getAsJsonArray("godcards");

        for (JsonElement je : godcards) {
            JsonObject jo = je.getAsJsonObject();

            ArrayList<GodCard> cards = parseGod(jo, numberOfPlayers);

            for (GodCard card : cards) {
                deck.addCard(card);
            }
        }
        deck.shuffle();
        return deck;

    }

    private static ArrayList<GodCard> parseGod(JsonObject jsonObject, int n) {
        String name = jsonObject.get("name").getAsString();
        String descriptionPower=jsonObject.get("description").getAsString();
        String path = jsonObject.get("path").getAsString();

        JsonArray numberOfPlayersArray = jsonObject.getAsJsonArray("players");

        ArrayList<Integer> players = new ArrayList<>();
        for (JsonElement elem : numberOfPlayersArray) {
            players.add(Integer.valueOf(elem.getAsString()));
        }

        ArrayList<GodCard> cards = new ArrayList<>();

           switch (name) {
                case "Apollo":
                    if(players.contains(n))
                    cards.add(new Apollo(name,path,descriptionPower));
                    break;
                case "Artemis":
                    if(players.contains(n))
                    cards.add(new Artemis(name,path,descriptionPower));
                    break;
                case "Prometheus":
                    if(players.contains(n))
                    cards.add(new Prometheus(name,path,descriptionPower));
                    break;
                case "Athena":
                    if(players.contains(n))
                    cards.add(new Athena(name,path,descriptionPower));
                    break;
                case "Atlas":
                    if(players.contains(n))
                    cards.add(new Atlas(name,path,descriptionPower));
                    break;
                case "Demeter":
                    if(players.contains(n))
                    cards.add(new Demeter(name,path,descriptionPower));
                    break;
                case "Hephaestus":
                    if(players.contains(n))
                    cards.add(new Hephaestus(name,path,descriptionPower));
                    break;
                case "Minotaur":
                    if(players.contains(n))
                    cards.add(new Minotaur(name,path,descriptionPower));
                    break;
                case "Pan":
                    if(players.contains(n))
                    cards.add(new Pan(name,path,descriptionPower));
                    break;
               case "Ares":
                   if(players.contains(n))
                       cards.add(new Ares(name,path,descriptionPower));
                   break;
               case "Chronus":
                   if(players.contains(n))
                       cards.add(new Chronus(name,path,descriptionPower));
                   break;
               case "Hestia":
                   if(players.contains(n))
                       cards.add(new Hestia(name,path,descriptionPower));
                   break;
               case "Triton":
                   if(players.contains(n))
                       cards.add(new Triton(name,path,descriptionPower));
                   break;
               case "Zeus":
                   if(players.contains(n))
                       cards.add(new Zeus(name,path,descriptionPower));
                   break;
            }

        return cards;
    }

}
