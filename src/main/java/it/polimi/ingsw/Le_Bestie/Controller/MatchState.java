package it.polimi.ingsw.Le_Bestie.Controller;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Deck;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import java.io.IOException;
import java.util.ArrayList;

public class MatchState implements MatchStateInterface{

    private int numPlayers;
    private static boolean hasMoved;
    private static ArrayList<Integer> remainingPieces;
    private Player currentTurnPlayer;
    private Player Winner;
    private static boolean notMoveUp;
    private Builder chosenBuilder;
    private ArrayList<Player> playerList;
    private Board board;
    private Deck deck;
    private static boolean usePower;

    public static boolean getUsePower() {
        return usePower;
    }

    public static void setUsePower(boolean usePower) {
        MatchState.usePower = usePower;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public MatchState() throws IOException {
        this.deck=new Deck(numPlayers);
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

    public static void setNotMoveUp(boolean notMoveUp) {
        MatchState.notMoveUp=notMoveUp;
    }

    public static boolean getNotMoveUp() {
        return notMoveUp;
    }

    public void setChosenBuilder(Builder chosenBuilder) {
        this.chosenBuilder=chosenBuilder;
    }

    public Builder getChosenBuilder() {
        return chosenBuilder;
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = new Board();
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
    public void beginTurn() { }

    @Override
    public Deck getDeck() throws IOException {
        return deck;
    }

    public static boolean checkPieces(int level) {
        return false;
    }
}