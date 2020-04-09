package it.polimi.ingsw.Le_Bestie.Controller;
import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Deck;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;

import java.io.IOException;
import java.util.*;


public class MatchState implements MatchStateInterface{

    private int numPlayers;
    private static boolean hasMoved;
    private static ArrayList<Integer> remainingPieces;
    private Player currentTurnPlayer;
    private Player Winner;
    private boolean notMoveUp;
    private Builder chosenBuilder;
    private ArrayList<Player> playerList;
    private Board board;
    private ArrayList<GodCard> deck;


    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public MatchState() {

    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers=numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public static void setHasMoved(boolean hasMoved) {
        MatchState.hasMoved=hasMoved;
    }

    public static boolean getHasMoved() {
        return hasMoved;
    }

    public static Integer getRemainingPieces(int level) {
        return remainingPieces.get(level-1);
    }

    public void setRemainingPieces(ArrayList<Integer> remainingPieces) {
        this.remainingPieces = remainingPieces;
    }

    public void SetCurrentTurnPlayer(Player currentTurnPlayer) {
        this.currentTurnPlayer=currentTurnPlayer;
    }

    public Player getCurrentTurnPlayer() {
        return currentTurnPlayer;
    }

    public void setWinner(Player Winner) {
        this.Winner=Winner;
    }

    public Player getWinner() {
        return Winner;
    }

    public void setNotMoveUp(boolean notMoveUp) {
        this.notMoveUp=notMoveUp;
    }

    public boolean getNotMoveUp() {
        return notMoveUp;
    }

    public void setChosenBuilder(Builder chosenBuilder) {
        this.chosenBuilder=chosenBuilder;
    }

    public Builder getChosenBuilder() {
        return chosenBuilder;
    }

    @Override
    public Player nextPlayer() {
        return null;
    }

    @Override
    public boolean endMatch() {
        return false;
    }

    @Override
    public ArrayList<Player> cleanAndRemovePlayer() {
        return null;
    }

    @Override
    public void addPlayer() { }

    @Override
    public void endTurn() { }

    @Override
    public static boolean checkPieces(int level) {
        return false;
    }

    @Override
    public void beginTurn() { }

    @Override
    public ArrayList<GodCard> getDeck(int numPlayers) throws IOException {
        Deck deck=new Deck(2);
        return deck.getDeck();
    }
}