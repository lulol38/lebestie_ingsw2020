package it.polimi.ingsw.Le_Bestie.Model.Player;


import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Builder.BuilderColor;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import java.io.Serializable;

/**
 * Class Player describes a generic player that play on the board
 * @author Davide Carini
 */

public class Player implements Serializable {

    private String nickname;
    private GodCard godCardPlayer;
    private Builder builder1;
    private Builder builder2;
    private boolean disabled;
    private BuilderColor color;
    private Builder builderChosen;

    public Player(String nickname) {
        this.nickname=nickname;
        this.disabled=false;
        this.builder1=null;
        this.builder2=null;
    }

    public Player(String nickname, BuilderColor color) {
        this.nickname=nickname;
        this.disabled=false;
        this.builder1=null;
        this.builder2=null;
        this.color=color;
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
    public BuilderColor getColor() {
        return color;
    }
    public Builder getBuilderChosen() { return builderChosen; }

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
    public void setColor(BuilderColor color) {
        this.color = color;
    }
    public void setBuilderChosen(Builder builderChosen) { this.builderChosen = builderChosen; }

    //Method that returns the God Card name(string) associated to the player
    public String toString() {
        return this.godCardPlayer.getName();
    }

}