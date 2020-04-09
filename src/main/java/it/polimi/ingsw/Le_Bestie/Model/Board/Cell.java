package it.polimi.ingsw.Le_Bestie.Model.Board;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
/**
 *This class implements cell
 */
public class Cell {

    private int level;
    private Builder builder;
    private int positionX;
    private int positionY;
    private boolean occupied;
    private boolean disabled;

    public Cell(int level, int x,int y)
    {
        this.level=level;
        this.positionX=x;
        this.positionY=y;
    }

    //Getters
    public int getLevel(){
        return this.level;
    }
    public Builder getBuilder()
    {
        return this.builder;
    }
    public int getPositionX()
    {
        return this.positionX;
    }
    public int getPositionY()
    {
        return this.positionY;
    }
    public boolean isOccupied()
    {
        return this.occupied;
    }
    public boolean isDisabled()
    {
        return this.disabled;
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
    public void setPositionX(int positionX)
    {
        this.positionX=positionX;
    }
    public void setPositionY(int positionY)
    {
        this.positionY=positionY;
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
    public String toString() {
        return  "X  " + this.positionX +",Y  " + this.positionY ;
    }

}