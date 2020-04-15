package it.polimi.ingsw.Le_Bestie.Controller;

import it.polimi.ingsw.Le_Bestie.Model.Game.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.Network.Server.Lobby;

import java.io.IOException;
import java.util.ArrayList;


//Controller for the server, it takes place between the model and the network in the server side
public class GameController {

    private Lobby lobby;
    private MatchState matchState;
    private Player Winner;

    public GameController(Lobby lobby) {
        this.lobby=lobby;
        this.matchState=new MatchState();
    }


    public void initGame(){
        matchState.startGame();
    }

    public MatchState getMatchState() {
        return matchState;
    }



    private void updatePlayersInRound(ArrayList<Player> players) {
        try {
            Player exPlayerInRound = players.get(0);
            players.remove(0);
            players.add( players.size(), exPlayerInRound);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException");
        }
    }
}
