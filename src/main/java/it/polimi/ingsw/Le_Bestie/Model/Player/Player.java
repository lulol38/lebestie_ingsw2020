package it.polimi.ingsw.Le_Bestie.Model.Player;
import it.polimi.ingsw.Le_Bestie.Model.Cards.GodCard;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;

/**
 *This class implements the player
 */

public class Player {

    private String nickname;
    private GodCard godCardPlayer;
    private Builder builder1;
    private Builder builder2;
    private boolean disabled;

    public Player(String nickname) {
        this.nickname=nickname;
        this.disabled=false;
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
    public Builder getWorker1(){
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
        this.builder2=builder1;
    }

}