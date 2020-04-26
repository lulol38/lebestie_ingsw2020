package it.polimi.ingsw.Le_Bestie.Model.Game;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Builder.BuilderColor;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Deck;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.jsonParser.GodCardsParser;
import java.util.ArrayList;
import java.util.Collections;

public class MatchState {

    private Board board;
    private Deck deck;
    private boolean usePower;
    private boolean hasMoved;
    private boolean gameStarted;
    private ArrayList<Player> players = new ArrayList<Player>();

    public MatchState(){
        this.board=new Board();
        this.deck=new Deck();
        usePower=false;
        hasMoved=false;
        gameStarted=false;
        deck=null;
        players=new ArrayList<Player>();
    }

    public Deck getDeck() { return deck; }
    public Board getBoard() {
        return board;
    }
    public boolean getUsePower() {
        return usePower;
    }
    public boolean getHasMoved() {
        return hasMoved;
    }
    public ArrayList<Player> getPlayers(){ return players;}

    public void setUsePower(boolean usePower) {
        this.usePower = usePower;
    }
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved=hasMoved;
    }

    public void startGame(){

        if(gameStarted)
            return;
        gameStarted=true;
        initializeDeck();

        assignCardToPlayers();
    }

    public int numberOfPlayers(){
        int n=players.size();
        return n;
    }

    private void initializeDeck(){
        this.deck= GodCardsParser.parseCards(numberOfPlayers());
    }


    private void assignCardToPlayers(){
        for(Player player:players)
            player.setGodCard((GodCard)deck.draw());
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void addPlayer(String nickname){
        Player p= new Player(nickname, builderColorNotUsed().get(0));
        players.add(p);
    }

    public Player getCurrentPlayer(){
        return players.get(0);
    }

    public void nextTurn(){
        //rotate list
        Collections.rotate(players, -1);
        hasMoved=false;
        usePower=false;
    }

    private ArrayList<BuilderColor> builderColorNotUsed() {
        ArrayList<BuilderColor> builderColorsUsed = new ArrayList<>();
        ArrayList<BuilderColor> builderColors = new ArrayList<>();
        ArrayList<BuilderColor> helpBuilderColors = new ArrayList<>();
        for (Player player : players) {
            builderColorsUsed.add(player.getColor());
        }

        for(int x=0; x< BuilderColor.values().length; x++)
            helpBuilderColors.add(BuilderColor.values()[x]);

        if(builderColorsUsed.size()!=0) {
            for (int i = 0; i < BuilderColor.values().length; i++) {
                for (int y = 0; y < builderColorsUsed.size(); y++) {
                    if (builderColorsUsed.get(y).name() == BuilderColor.values()[i].name()){
                        for(int z=0; z<helpBuilderColors.size();z++){
                            if(helpBuilderColors.get(z).name()==BuilderColor.values()[i].name())
                                helpBuilderColors.remove(z);
                        }
                    }

                }
            /*if (!builderColorsUsed.contains(BuilderColor.getColor(BuilderColor.values()[i].toString())));
                 builderColors.add(BuilderColor.values()[i]) ;*/
            }
            return helpBuilderColors;
        }
        else{
            for(int x=0; x< BuilderColor.values().length; x++)
                builderColors.add(BuilderColor.values()[x]);
            return builderColors;
        }
    }


}