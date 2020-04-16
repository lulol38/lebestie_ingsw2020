package it.polimi.ingsw.Le_Bestie.Model.Game;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Builder.BuilderColor;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Deck;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.jsonParser.GodCardsParser;
import java.util.ArrayList;

public class MatchState {

    private Board board;
    private Deck deck;
    private boolean usePower;
    private boolean hasMoved;
    private boolean gameStarted;
    private ArrayList<Player> players;
    private Builder chosenBuilder;

    public MatchState(){
        this.board=new Board();
        this.deck=new Deck();
        usePower=false;
        hasMoved=false;
        gameStarted=false;
        deck=null;

    }

    public Deck getDeck() { return this.deck; }
    public Board getBoard() {
        return board;
    }
    public boolean getUsePower() {
        return usePower;
    }
    public boolean getHasMoved() {
        return hasMoved;
    }
    public Builder getChosenBuilder() {
        return chosenBuilder;
    }
    public ArrayList<Player> getPlayers(){ return players;}


    public void setUsePower(boolean usePower) {
        this.usePower = usePower;
    }
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved=hasMoved;
    }
    public void setChosenBuilder(Builder chosenBuilder) {
        this.chosenBuilder=chosenBuilder;
    }

    public void startGame(){
        gameStarted=true;
        initializeDeck();
        assignCardToPlayers();
    }


    public int numberOfPlayers(){
        int n=players.size();
        return n;
    }

    private void initializeDeck(){ this.deck= GodCardsParser.parseCards(numberOfPlayers());}


    private void assignCardToPlayers(){
        for(Player player:players)
            player.setGodCard((GodCard)deck.draw());
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public boolean searchExistingNickname(String nickname) {
        for (Player p : players) {
            if (p.getNickname().equals(nickname))
                return true;
        }
        return false;
    }

    public Player addPlayer(String nickname){
        Player p= new Player(nickname);
        players.add(p);
        return p;
    }

    public Player getFirstPlayer(){
       return players.get(0);
    }

    private ArrayList <BuilderColor> builderColorNotUsed() {
        ArrayList<BuilderColor> builderColorsUsed = new ArrayList<>();
        ArrayList<BuilderColor> builderColors = new ArrayList<>();
        for (Player player : players) {
            builderColorsUsed.add(player.getBuilder1().getColor());
        }
        for (int i = 0; i < BuilderColor.values().length; i++) {
            if (!builderColorsUsed.contains(BuilderColor.values()[i]));
                 builderColors.add(BuilderColor.values()[i]) ;
        }
        return builderColors;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }


}