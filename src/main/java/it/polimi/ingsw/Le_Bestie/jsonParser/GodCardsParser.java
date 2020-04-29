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

        try {
            Class<?> clazz = Class.forName("it.polimi.ingsw.Le_Bestie.Model.Cards."+name);
            Constructor<?> constructor = clazz.getConstructor(String.class,String.class,String.class);
            Object object = constructor.newInstance(new Object[]{name,path,descriptionPower});
            if(players.contains(n))
                cards.add((GodCard)object);
        }
        catch (Exception e){

        }


        return cards;
    }

}
