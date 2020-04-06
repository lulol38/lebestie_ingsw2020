package it.polimi.ingsw.Le_Bestie.Controller;

import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import java.util.ArrayList;

public interface MatchStateInterface {

    ArrayList<String> removeCard(String s);
    boolean assignCard();
    ArrayList<String> removeCards3Players();
    Player nextPlayer();
    boolean endMatch();
    ArrayList<Player> cleanAndRemovePlayer();
    void addPlayer();
    void endTurn();
    boolean checkPieces(int level);
    void beginTurn();

}
