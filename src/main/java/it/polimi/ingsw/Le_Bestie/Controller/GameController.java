package it.polimi.ingsw.Le_Bestie.Controller;

import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Game.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.AskPositionBuilders;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.SendBeginTurn;
import it.polimi.ingsw.Le_Bestie.Network.Server.ClientHandler;
import it.polimi.ingsw.Le_Bestie.Network.Server.Lobby;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//Controller for the server, it takes place between the model and the network in the server side
public class GameController {

    private static GameController instance;
    private Lobby lobby;
    private MatchState matchState;
    private Player Winner;

  
    private Player currentTurnPlayer;

    public GameController(Lobby lobby) {
        this.lobby=lobby;
        this.matchState=new MatchState();
        this.instance=this;

        for (ClientHandler c: lobby.getClientsWaiting()) {
            matchState.addPlayer(c.getUsername());
        }
    }

    public Lobby getLobby() {
        return lobby;
    }

    public static GameController getInstance() {
        return instance;
    }

    public MatchState getMatchState() {
        return matchState;
    }

    public void initGame(){
        matchState.startGame();
        lobby.getClientsWaiting().get(0).sendMessage(new AskPositionBuilders());
    }

    public void setPlayerBuilders(int pos1x, int pos1y, int pos2x, int pos2y){
        matchState.getCurrentPlayer().setBuilder1(new Builder(new Position(pos1x,pos1y)));
        matchState.getBoard().getGrid()[pos1x][pos1y].setBuilder(matchState.getCurrentPlayer().getBuilder1());
        matchState.getCurrentPlayer().setBuilder2(new Builder(new Position(pos2x,pos2y)));
        matchState.getBoard().getGrid()[pos2x][pos2y].setBuilder(matchState.getCurrentPlayer().getBuilder2());
    }

    public void nextTurn(){
        matchState.nextTurn();
        Collections.rotate(lobby.getClientsWaiting(), -1);
        if(matchState.getCurrentPlayer().getBuilder1()==null||matchState.getCurrentPlayer().getBuilder2()==null)
            lobby.getClientsWaiting().get(0).sendMessage(new AskPositionBuilders());
        else lobby.getClientsWaiting().get(0).sendMessage(new SendBeginTurn());
    }

}
