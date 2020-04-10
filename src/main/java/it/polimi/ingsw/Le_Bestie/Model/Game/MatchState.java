package it.polimi.ingsw.Le_Bestie.Model.Game;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Deck;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import java.io.IOException;
import java.util.ArrayList;

public class MatchState {

    private int numPlayers;
    private static boolean hasMoved;
    private static ArrayList<Integer> remainingPieces;
    private Player Winner;
    private static boolean notMoveUp;
    private Builder chosenBuilder;
    private Board board;
    private Deck deck;
    private static boolean usePower;

    public MatchState() throws IOException {
        this.board=new Board();
        this.deck=new Deck(numPlayers);

    }

    public static boolean getUsePower() {
        return usePower;
    }

    public static void setUsePower(boolean usePower) {
        MatchState.usePower = usePower;
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
        this.board= board;
    }

    public Player nextPlayer() {
        return null;
    }


    public boolean endMatch() {
        return false;
    }


    public ArrayList<Player> cleanAndRemovePlayer() {
        return null;
    }


    public void addPlayer() { }


    public void endTurn() { }


    public void beginTurn() { }


    public Deck getDeck() throws IOException {
        return deck;
    }

    public static void checkPieces(int level) {
        remainingPieces.set(level-1,remainingPieces.get(level-1)-1);
    }
}