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
import it.polimi.ingsw.Le_Bestie.Network.Server.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Controller for the server, it takes place between the model and the network in the server side
 * @author Davide Carini, Luca Ferrari, Veronica Rovelli
 */
public class GameController {

    private Lobby lobby;
    private MatchState matchState;
    private int Winner;
    private Player currentTurnPlayer;
    private int idGame;

    public GameController(Lobby lobby, int idGame) {
        this.lobby=lobby;
        this.matchState=new MatchState();
        this.idGame=idGame;
        for (ClientHandler c: lobby.getClientsWaiting()) {
            matchState.addPlayer(c.getUsername());
        }
    }

    //Getters
    public Lobby getLobby() { return lobby; }
    public MatchState getMatchState() { return matchState; }
    public int getIdGame() { return idGame; }

    //Setters
    public void setWinner(int winner) { Winner = winner; }
    public void setIdGame(int idGame) { this.idGame = idGame; }

    /**
     * This method initializes the game
     * It sends the message to the first player to setup the builders and sends him his opponents and their GodCards
     */
    public void initGame(){
        matchState.startGame();

        ArrayList<String>opponents=new ArrayList<>();
        ArrayList<String>opponentsGods=new ArrayList<>();
        Player current=null;
        int i=0;
        for(ClientHandler c : lobby.getClientsWaiting()) {
            opponents=new ArrayList<>();
            opponentsGods=new ArrayList<>();
            for (Player p : matchState.getPlayers()) {
                if (p.getNickname().compareTo(lobby.getClientsWaiting().get(i).getUsername()) != 0) {
                    opponents.add(p.getNickname());
                    opponentsGods.add(p.getGodCard().getName());
                }
                if (p.getNickname().compareTo(lobby.getClientsWaiting().get(i).getUsername()) == 0){
                    current = p;
                }
            }
            lobby.getClientsWaiting().get(i).sendMessage(new SendOpponents(opponents, opponentsGods));
            lobby.getClientsWaiting().get(i).sendMessage(new SendCardToPlayers(current.getGodCard().getName(), current.getColor().toString(),current.getGodCard().getPath(),current.getGodCard().getDescription()));
            i++;
        }
        lobby.getClientsWaiting().get(0).sendMessage(new SendBeginTurn());
        lobby.getClientsWaiting().get(0).sendMessage(new AskPositionBuilders());
    }

    /**
     * This method sets the builders to the players
     * @param posx is the x coordinate of the builder to set
     * @param posy is the y coordinate of the builder to set
     * @return
     */
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

    /**
     * This method handles the rotation of the turns for the players in the game
     * (If someone hasn't already setup their builders, it sends the message to the player in the turn
     * to setup the builders and sends him his opponents and their GodCards)
     * At the beginning of the turn the methos controls if the player has lost or won, if not, asks an action to the player
     * that begins with asking the builder to move
     */
    public void nextTurn(){
        lobby.getClientsWaiting().get(0).sendMessage(new SendEndTurn());
        matchState.nextTurn();
        Collections.rotate(lobby.getClientsWaiting(), -1);
        lobby.getClientsWaiting().get(0).sendMessage(new SendBeginTurn());
        if(matchState.getCurrentPlayer().getBuilder1()==null||matchState.getCurrentPlayer().getBuilder2()==null) {
            lobby.getClientsWaiting().get(0).sendMessage(new AskPositionBuilders());
        }
        else{ //BEGIN TURN
            int result=matchState.getCurrentPlayer().getGodCard().HasLost(matchState.getCurrentPlayer(), matchState.getBoard());
            if(result==2)
            {
                setWinner(0);
                endMatch();
            }
            if(result==1)
            { //CHECK IF THE PLAYER LOSES
                manageHasLost();
            }
            lobby.getClientsWaiting().get(0).sendMessage(new AskBuilderChosen());
        }
    }

    /**
     * This method handles the case when someone has lost
     * if 2 players -> end match
     * if 3 players -> remove the player and all his builders and continue the game
     */
    public void manageHasLost(){
        if(matchState.getPlayers().size()==2){ //IF ONLY 2 PLAYERS
            setWinner(1);
            endMatch();
        }
        else{ //3 PLAYERS
            matchState.getBoard().getGrid()[matchState.getCurrentPlayer().getBuilder1().getPosition().getX()][matchState.getCurrentPlayer().getBuilder1().getPosition().getY()].setBuilder(null);
            matchState.getBoard().getGrid()[matchState.getCurrentPlayer().getBuilder2().getPosition().getX()][matchState.getCurrentPlayer().getBuilder2().getPosition().getY()].setBuilder(null);
            updateClients();
            matchState.getPlayers().remove(matchState.getCurrentPlayer());
            lobby.getClientsWaiting().get(0).sendMessage(new SendHasLost());
            lobby.getClientsWaiting().remove(0);
            lobby.getClientsWaiting().get(0).sendMessage(new SendBeginTurn());
        }
    }

    /**
     * Check if the builder selected for an action is correct
     * @param bx coordinate x of the builder
     * @param by coordinate y of the builder
     */
    public void checkBuilder(int bx, int by){
        if(matchState.getBoard().getGrid()[bx][by].getBuilder()!=null && matchState.getBoard().getGrid()[bx][by].getBuilder().getPlayer()==matchState.getCurrentPlayer()&&matchState.getBoard().getGrid()[bx][by].getBuilder().getDisabled()==false){
            matchState.getCurrentPlayer().setBuilderChosen(matchState.getBoard().getGrid()[bx][by].getBuilder());

            lobby.getClientsWaiting().get(0).sendMessage(new AskCell());
        }
        else{
            lobby.getClientsWaiting().get(0).sendMessage(new AskBuilderChosen());
        }
    }

    /**
     * This method handles the request of an action, move or build
     * @param cx coordinate x of the cell for the action
     * @param cy coordinate y of the cell for the action
     *
     * MOVE
     *
     * 0 if the client must select another cell
     * 1 if the builder correctly moves in the selected cell
     * 2 if the builder's player won
     * 3 if the client must choose to use his GodCard's power (if the client chooses "yes" recall the move method with a new selected cell, otherwise go on with the build)
     * 4 if the client must choose to use his GodCard's power (then recall the move method with the same cell, but if the client chooses "yes" usePower=false, otherwise usePower=true)
     *
     * BUILD
     *
     * 0 if the client can't build in the selected cell
     * 1 if the builder correctly builds in the selected cell
     * 2 if the builder can't build because there aren't the right level building pieces
     * 3 if the client must choose to use his GodCard's power (if the client chooses "yes" recall the build method with a new selected cell and usePower=false, otherwise recall the build method with the same cell and usePower=true)
     * 4 if the client must choose to use his GodCard's power (then recall the build method with the same cell, but if the client chooses "yes" usePower=false, otherwise usePower=true)
     * 5 if the builder's player won
     */
    public void requestAction(int cx, int cy){
        if(!matchState.getHasMoved()) {
            int moveResult = matchState.getCurrentPlayer().getGodCard().move(matchState.getBoard(), matchState.getCurrentPlayer().getBuilderChosen(), matchState.getBoard().getGrid()[cx][cy], matchState.getUsePower());

            switch (moveResult) {
                case 0:
                    if(matchState.getCurrentPlayer().getBuilderChosen().getDisabled())
                        manageHasLost();
                    lobby.getClientsWaiting().get(0).sendMessage(new AskCell());
                    break;
                case 1:
                    matchState.setHasMoved(true);
                    updateClients();
                    lobby.getClientsWaiting().get(0).sendMessage(new AskCell());
                    break;
                case 2:
                    setWinner(0);
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
                case 5:
                    setWinner(0);
                    endMatch();
                    break;
            }
        }

    }

    /**
     * This method sends the board to the client after every change
     */
    public void updateClients(){
        for (ClientHandler c : lobby.getClientsWaiting()) {
            c.sendMessage(new SendUpdatedBoard(matchState.getBoard()));
        }
    }

    /**
     * This method ends the match when there is a winner and removes the game from the game list
     */
    public void endMatch(){
        lobby.getClientsWaiting().get(Winner).sendMessage(new SendHasWon());
        lobby.getClientsWaiting().remove(Winner);
        for(int i=0; i<lobby.getClientsWaiting().size();i++){
            lobby.getClientsWaiting().get(i).sendMessage(new SendHasLost());
        }

        Server.removeGame(this);
    }

}
