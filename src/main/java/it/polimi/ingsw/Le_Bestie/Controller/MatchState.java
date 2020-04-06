package it.polimi.ingsw.Le_Bestie.Controller;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;

import java.util.*;

public class MatchState{

    int numPlayers;
    boolean hasMoved;
    ArrayList<String> deck[];
    ArrayList<Integer> remainingPieces[];
    Player currentTurnPlayer;
    Player Winner;
    boolean notMoveUp;
    Builder chosenBuilder;


 //costruttore
    public MatchState() {

    }


//get e set
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

    public void setDeck(ArrayList<String> deck[]) {
        for(int i=0;i<deck.length;i++)
            this.deck[i]=deck[i];
    }

    public ArrayList<String>[] getDeck() {
        return deck;
    }

    public void setRemainingPieces(ArrayList<Integer> remainingPieces[]) {
        for(int i=0;i<remainingPieces.length;i++)
          this.remainingPieces[i]=remainingPieces[i];
    }

    public ArrayList<Integer>[] getRemainingPieces() {
            return remainingPieces;
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


//metodi
    public ArrayList<String> removeCard(String s) {
        // TODO implement here
        //


        return null;
    }

    public boolean assignCard() {
        // TODO implement here
        return false;
    }

    public ArrayList<String> removeCards3Players() {
        // TODO implement here
        return null;
    }

    public Player nextPlayer() {
        // TODO implement here
        return null;
    }

    public boolean endMatch() {
        // TODO implement here
        return false;
    }

    public ArrayList<PlayerModel> cleanAndRemovePlayer() {
        // TODO implement here
        return null;
    }

    public void addPlayer() {
        // TODO implement here
        return null;
    }

    public void endTurn() {
        // TODO implement here
        return null;
    }

    public boolean checkPieces(int level) {
        // TODO implement here
        return false;
    }

    public void beginTurn() {
        // TODO implement here
        return null;
    }

}