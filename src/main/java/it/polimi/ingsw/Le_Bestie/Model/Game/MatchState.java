package it.polimi.ingsw.Le_Bestie.Model.Game;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Builder.BuilderColor;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Deck;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.jsonParser.GodCardsParser;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is used to describe the state of the current game. In few words, it represents the model outside.
 * MatchState is the intermediate class between the controller and the model.
 * @authors Davide Carini, Luca Ferrari, Veronica Rovelli
 */

public class MatchState{

    private Board board;
    private Deck deck;
    private boolean usePower;
    private boolean hasMoved;
    private static boolean notMoveUp;
    private ArrayList<Player> players = new ArrayList<Player>();

    //CONSTRUCTOR
    public MatchState(){
        this.board=new Board();
        this.deck=new Deck();
        usePower=false;
        hasMoved=false;
        deck=null;
        players=new ArrayList<Player>();
    }

    //GETTERS
    public Deck getDeck() { return deck; }
    public Board getBoard() { return board; }
    public boolean getUsePower() { return usePower; }
    public boolean getHasMoved() { return hasMoved; }
    public ArrayList<Player> getPlayers(){ return players;}


    //SETTERS
    public void setUsePower(boolean usePower) {
        this.usePower = usePower;
    }
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved=hasMoved;
    }
    public static void setNotMoveUp(boolean notmoveup) {
        notMoveUp=notmoveup;
    }
    public static boolean getNotMoveUp() {
        return notMoveUp;
    }

    /**
     * This method is called by Controller for start a new match.
     */
    public void startGame(){
        initializeDeck();
        assignCardToPlayers();
    }

    /**
     *
     * @return the number of players in the match
     */
    public int numberOfPlayers(){
        int n=players.size();
        return n;
    }

    /**
     *This method is used to initialize a new deck to associate a the current match
     */
    private void initializeDeck(){
        this.deck= GodCardsParser.parseCards(numberOfPlayers());
    }

    /**
     * This method is used to distribute cards to players (one card for each player)
     */
    private void assignCardToPlayers(){
        for(Player player:players)
            player.setGodCard((GodCard)deck.draw());
    }

    /**
     * This method is invocated to add players in the current game.
     * @param nickname is the name of the user to add at the arraylist of players in the game
     */
    public void addPlayer(String nickname){
        Player p= new Player(nickname, builderColorNotUsed().get(0));
        players.add(p);
    }

    /**
     * @return the player in turn( player in the first position of the arraylist)
     */
    public Player getCurrentPlayer(){
        return players.get(0);
    }

    /**
     * Update the arraylist (left switch) and the ex current player is put in the last position.
     */
    public void nextTurn(){
        //rotate list
        Collections.rotate(players, -1);
        hasMoved=false;
        usePower=false;
    }

    /**
     *
     * @return an arraylist of color that have not yet been assigned to players.
     */
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