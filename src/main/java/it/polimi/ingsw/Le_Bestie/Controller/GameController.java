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

        lobby.getClientsWaiting().get(0).sendMessage(new SendBeginTurn());
        ArrayList<String>opponents=new ArrayList<>();
        ArrayList<String>opponentsGods=new ArrayList<>();
        for(Player p:matchState.getPlayers()){
            if(p.getNickname().compareTo(lobby.getClientsWaiting().get(0).getUsername())!=0){
                opponents.add(p.getNickname());
                opponentsGods.add(p.getGodCard().getName());
            }
        }
        lobby.getClientsWaiting().get(0).sendMessage(new SendOpponents(opponents,opponentsGods));
        lobby.getClientsWaiting().get(0).sendMessage(new SendCardToPlayers(matchState.getCurrentPlayer().getGodCard().getName(),matchState.getCurrentPlayer().getColor().toString(),matchState.getCurrentPlayer().getGodCard().getPath(),matchState.getCurrentPlayer().getGodCard().getDescription()));

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
            ArrayList<String>opponents=new ArrayList<>();
            ArrayList<String>opponentsGods=new ArrayList<>();
            for(Player p:matchState.getPlayers()){
                if(p.getNickname().compareTo(lobby.getClientsWaiting().get(0).getUsername())!=0){
                    opponents.add(p.getNickname());
                    opponentsGods.add(p.getGodCard().getName());
                }
            }
            lobby.getClientsWaiting().get(0).sendMessage(new SendOpponents(opponents,opponentsGods));
            lobby.getClientsWaiting().get(0).sendMessage(new SendCardToPlayers(matchState.getCurrentPlayer().getGodCard().getName(),matchState.getCurrentPlayer().getColor().toString(),matchState.getCurrentPlayer().getGodCard().getPath(),matchState.getCurrentPlayer().getGodCard().getDescription()));
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
            //lobby.getClientsWaiting().get(0).sendMessage(new SendHasLost());
            //lobby.getClientsWaiting().get(1).sendMessage(new SendHasWon());
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
     * @param cx
     * @param cy
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
