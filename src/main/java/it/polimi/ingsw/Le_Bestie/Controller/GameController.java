package it.polimi.ingsw.Le_Bestie.Controller;

import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Game.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.*;
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

    public void setWinner(Player winner) {
        Winner = winner;
    }

    public void initGame(){
        matchState.startGame();

        lobby.getClientsWaiting().get(0).sendMessage(new SendBeginTurn());
        lobby.getClientsWaiting().get(0).sendMessage(new SendCardToPlayers(matchState.getCurrentPlayer().getGodCard().getName()));
        lobby.getClientsWaiting().get(0).sendMessage(new AskPositionBuilders());
    }

    public int setPlayerBuilder(int posx, int posy){
        if(matchState.getCurrentPlayer().getBuilder1()==null) {
            if(matchState.getBoard().getGrid()[posx][posy].getBuilder()==null) {
                matchState.getCurrentPlayer().setBuilder1(new Builder(new Position(posx, posy)));
                matchState.getBoard().getGrid()[posx][posy].setBuilder(matchState.getCurrentPlayer().getBuilder1());
                matchState.getCurrentPlayer().getBuilder1().setPlayer(matchState.getCurrentPlayer());
                return 1;
            }
        }
        else {
            if(matchState.getBoard().getGrid()[posx][posy].getBuilder()==null) {
                matchState.getCurrentPlayer().setBuilder2(new Builder(new Position(posx, posy)));
                matchState.getBoard().getGrid()[posx][posy].setBuilder(matchState.getCurrentPlayer().getBuilder2());
                matchState.getCurrentPlayer().getBuilder2().setPlayer(matchState.getCurrentPlayer());
                return 2;
            }
        }
        return 0; //The cell was occupied
    }

    public void nextTurn(){
        lobby.getClientsWaiting().get(0).sendMessage(new SendEndTurn());
        matchState.nextTurn();
        Collections.rotate(lobby.getClientsWaiting(), -1);
        lobby.getClientsWaiting().get(0).sendMessage(new SendBeginTurn());
        if(matchState.getCurrentPlayer().getBuilder1()==null||matchState.getCurrentPlayer().getBuilder2()==null) {
            lobby.getClientsWaiting().get(0).sendMessage(new SendCardToPlayers(matchState.getCurrentPlayer().getGodCard().getName()));
            lobby.getClientsWaiting().get(0).sendMessage(new AskPositionBuilders());
        }
        else{
            lobby.getClientsWaiting().get(0).sendMessage(new AskBuilderChosen());
        }
    }

    public void checkBuilder(int bx, int by){
        if(matchState.getBoard().getGrid()[bx][by].getBuilder().getPlayer()==matchState.getCurrentPlayer()&&matchState.getBoard().getGrid()[bx][by].getBuilder().getDisabled()==false){
            matchState.getCurrentPlayer().setBuilderChosen(matchState.getBoard().getGrid()[bx][by].getBuilder());

            lobby.getClientsWaiting().get(0).sendMessage(new AskCell());
        }
        else{
            lobby.getClientsWaiting().get(0).sendMessage(new AskBuilderChosen());
        }
    }

    public void requestAction(int cx, int cy){
        if(!matchState.getHasMoved()) {
            int moveResult = matchState.getCurrentPlayer().getGodCard().move(matchState.getBoard(), matchState.getCurrentPlayer().getBuilderChosen(), matchState.getBoard().getGrid()[cx][cy], matchState.getUsePower());

            switch (moveResult) {
                case 0:
                    lobby.getClientsWaiting().get(0).sendMessage(new AskCell());
                    break;
                case 1:
                    matchState.setHasMoved(true);
                    updateClients();
                    lobby.getClientsWaiting().get(0).sendMessage(new AskCell());
                    break;
                case 2:
                    setWinner(matchState.getCurrentPlayer());
                    endMatch();
                    break;
                case 3:
                    updateClients();
                    lobby.getClientsWaiting().get(0).sendMessage(new SendPowerMessage(matchState.getCurrentPlayer().getGodCard().getMessage()));
                    break;
                case 4:
                    lobby.getClientsWaiting().get(0).sendMessage(new AskUsePower(matchState.getCurrentPlayer().getGodCard().getMessage()));
                    break;
            }
        }
        else{
            int buildResult = matchState.getCurrentPlayer().getGodCard().build(matchState.getBoard(), matchState.getCurrentPlayer().getBuilderChosen(), matchState.getBoard().getGrid()[cx][cy], matchState.getUsePower());

            switch(buildResult){
                case 0:
                    lobby.getClientsWaiting().get(0).sendMessage(new AskCell());
                    break;
                case 1:
                    updateClients();
                    nextTurn();
                    break;
                case 2:
                    lobby.getClientsWaiting().get(0).sendMessage(new AskCellError());
                    break;
                case 3:
                    updateClients();
                    lobby.getClientsWaiting().get(0).sendMessage(new SendPowerMessage(matchState.getCurrentPlayer().getGodCard().getMessage()));
                    break;
                case 4:
                    lobby.getClientsWaiting().get(0).sendMessage(new AskUsePower(matchState.getCurrentPlayer().getGodCard().getMessage()));
                    break;
            }
        }

    }

    public void updateClients(){
        for (ClientHandler c : lobby.getClientsWaiting()) {
            c.sendMessage(new SendUpdatedBoard(matchState.getBoard()));
        }
    }

    public void endMatch(){

        //(todo)


    }

}
