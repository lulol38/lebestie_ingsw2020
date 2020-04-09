package it.polimi.ingsw.Le_Bestie.Controller;
import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Deck;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import java.util.*;


public class MatchState implements MatchStateInterface{

    private int numPlayers;
    private boolean hasMoved;
    private ArrayList<Integer> remainingPieces[];
    private Player currentTurnPlayer;
    private Player Winner;
    private boolean notMoveUp;
    private Builder chosenBuilder;
    private ArrayList<Player> playerList;
    private Board board;
    private Deck deck;


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

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved=hasMoved;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public ArrayList<Integer>[] getRemainingPieces() {
        return remainingPieces;
    }

    public void setRemainingPieces(ArrayList<Integer>[] remainingPieces) {
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
    public boolean checkPieces(int level) {
        return false;
    }

    @Override
    public void beginTurn() { }

    @Override
    public Deck getDeck(int numPlayers) {
        return deck;
    }
}