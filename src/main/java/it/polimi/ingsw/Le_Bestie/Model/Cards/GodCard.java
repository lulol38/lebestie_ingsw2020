package it.polimi.ingsw.Le_Bestie.Model.Cards;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;

public abstract class GodCard {

    private String name;

    public GodCard(String name){
        this.name=name;
    }

    public boolean move(Builder w, Cell c){return false;}
    public boolean build(Builder w, Cell c){return false;}
    public boolean HasWon(){return false;}
    public boolean HasLost(){return false;}

}