package it.polimi.ingsw.Le_Bestie.Model.Player;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;

/**
 * Class Player describes a generic player that play on the board
 * @author Davide Carini
 */

public class Player {

    private static final Position startPosition = new Position(0, 0);
    private String nickname;
    private GodCard godCardPlayer;
    private Builder builder1;
    private Builder builder2;
    private boolean disabled;

    public Player(String nickname) {
        this.nickname=nickname;
        this.disabled=false;
        this.builder1=null;
        this.builder2=null;
    }

    //Getters
    public String getNickname(){
        return this.nickname;
   }
    public GodCard getGodCard() {
        return this.godCardPlayer;
    }
    public boolean isDisabled(){
        return this.disabled;
    }
    public Builder getBuilder1(){
        return this.builder1;
    }
    public Builder getBuilder2(){
        return this.builder2;
    }

    //Setters
    public void setNickname(String nickname)
    {
        this.nickname=nickname;
    }
    public void setGodCard(GodCard godCardPlayer)
    {
        this.godCardPlayer=godCardPlayer;
    }
    public void setBuilder1(Builder builder1)
    {
        this.builder1= builder1;
    }
    public void setBuilder2(Builder builder2)
    {
        this.builder2=builder2;
    }

    //Method that returns the God Card name(string) associated to the player
    public String toString() {
        return this.godCardPlayer.getName();
    }

}