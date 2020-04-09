package it.polimi.ingsw.Le_Bestie.Controller;

import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.io.IOException;
import java.util.ArrayList;

public interface MatchStateInterface {

    Player nextPlayer();
    boolean endMatch();
    ArrayList<Player> cleanAndRemovePlayer();
    void addPlayer();
    void endTurn();
    boolean checkPieces(int level);
    void beginTurn();
    ArrayList<GodCard> getDeck(int numPlayers) throws IOException;

}
