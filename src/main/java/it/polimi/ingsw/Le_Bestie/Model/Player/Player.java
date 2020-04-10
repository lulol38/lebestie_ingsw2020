package it.polimi.ingsw.Le_Bestie.Model.Player;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Cards.*;

/**
 * Class Player describes a generic player
 * @author Davide Carini
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
        this.builder2=builder1;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();

        if (this.getGodCard() instanceof Apollo)
            res.append("Apollo");

        if (this.getGodCard() instanceof Artemis)
            res.append("Artemis");

        if (this.getGodCard() instanceof Prometheus)
            res.append("Prometheus");

        if (this.getGodCard() instanceof Athena)
            res.append("Athena");

        if (this.getGodCard() instanceof Atlas)
            res.append("Atlas");

        if (this.getGodCard() instanceof Demeter)
            res.append("Demeter");
        if (this.getGodCard() instanceof Hephaestus)
            res.append("Hephaestus");

        if (this.getGodCard() instanceof Minotaur)
            res.append("Minotaur");

        if (this.getGodCard() instanceof Pan)
            res.append("Pan");

        return res.toString();

    }


}