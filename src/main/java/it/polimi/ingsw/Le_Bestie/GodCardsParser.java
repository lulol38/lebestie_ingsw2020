package it.polimi.ingsw.Le_Bestie;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.Le_Bestie.Model.Cards.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GodCardsParser {

    public static Deck parseCards(){
        Deck deck =new Deck();
        String path = "json/GodCardsParser.java";
        InputStream is = GodCardsParser.class.getClassLoader().getResourceAsStream(path);

       /* if (is == null) {
            throw new JsonFileNotFoundException("File " + path + " not found");
        }*/
        JsonParser parser = new JsonParser();
        JsonObject json;
        json = parser.parse(new InputStreamReader(is)).getAsJsonObject();
        JsonArray godcards = json.getAsJsonArray("godcards");

        for (JsonElement je : godcards) {
            JsonObject jo = je.getAsJsonObject();

            ArrayList<GodCard> cards = parseGod(jo);

            for (GodCard card : cards) {
                deck.addCard(card);
            }
        }

        deck.shuffle();
        return deck;

    }
    private static ArrayList<GodCard> parseGod(JsonObject jsonObject) {
        String name = jsonObject.get("name").getAsString();
        String title = jsonObject.get("title").getAsString();
        String description = jsonObject.get("description").getAsString();
        String active = jsonObject.get("active").getAsString();
        Boolean numplayers = jsonObject.get("3players").getAsBoolean();

        ArrayList<GodCard> cards=new ArrayList<>();
        switch (name)
        {
            case "Apollo":cards.add(new Apollo(name));break;
            case "Artemis":cards.add(new Artemis(name));break;
            case "Prometheus":cards.add(new Prometheus(name));break;
            case "Athena":cards.add(new Athena(name));break;
            case "Atlas":cards.add(new Atlas(name));break;
            case "Demeter":cards.add(new Demeter(name));break;
            case "Hephaestus":cards.add(new Hephaestus(name));break;
            case "Minotaur":cards.add(new Minotaur(name));break;
            case "Pan":cards.add(new Pan(name));break;
        }
        return cards;
    }

}
