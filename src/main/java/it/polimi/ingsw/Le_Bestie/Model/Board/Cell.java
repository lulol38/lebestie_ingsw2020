package it.polimi.ingsw.Le_Bestie.Model.Board;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
/**
 *This class implements cell
 * @author Davide Carini
 */
public class Cell {

    private int level;
    private Builder builder;
    private Position position;
    private boolean occupied;
    private boolean disabled;

    public Cell(int level)
    {
        this.level=level;
    }

    //Getters
    public int getLevel(){
        return this.level;
    }
    public Builder getBuilder()
    {
        return this.builder;
    }

    public boolean isOccupied()
    {
        return this.occupied;
    }
    public boolean isDisabled()
    {
        return this.disabled;
    }
    public Position getPosition() {
        return position;
    }

    //Setters
    public void setLevel(int level)
    {
        if(level>3){
            this.disabled=true;
        }
        this.level=level;
    }
    public void setBuilder(Builder builder)
    {
        this.builder=builder;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setOccupied(boolean occupied)
    {
        this.occupied=occupied;
    }
    public void setDisabled(boolean disabled)
    {
        this.disabled=disabled;
    }

    public void addLevel() {
        this.level++;
        if(this.level>3)
            setDisabled(true);
    }

}