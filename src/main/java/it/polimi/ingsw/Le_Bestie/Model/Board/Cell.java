package it.polimi.ingsw.Le_Bestie.Model.Board;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;

import java.io.Serializable;

/**
 *This class implements cell
 * @author Davide Carini
 */
public class Cell implements Serializable {

    private int level;
    private Builder builder;
    private Position position;
    private boolean disabled;
    private boolean completeTower;


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
    public boolean isDisabled()
    {
        return this.disabled;
    }
    public Position getPosition() {
        return position;
    }
    public boolean isCompleteTower() {
        return completeTower;
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
    public void setDisabled(boolean disabled)
    {
        this.disabled=disabled;
    }
    public void setCompleteTower(boolean completeTower) {
        this.completeTower = completeTower;
    }



    public void addLevel() {
        this.level++;
        if(this.level>3) {
            setDisabled(true);
            setCompleteTower(true);
        }
    }

    // !!!!! "currentCell" is the cell in which is OUR Builder and "this" is the cell in which is OPPONENT builder !!!!!!!!!!
    public Position nextCellFree(Board b,Cell currentCell)
    {
        Position nextCell;
        int ccY=currentCell.getPosition().getY(),ccX=currentCell.getPosition().getX();
        if(this.position.getY()==ccY)
            nextCell=new Position(this.position.getX()+(this.position.getX()-ccX),ccY);
        else
            if(this.position.getX()==ccX)
            nextCell=new Position(ccX,this.position.getY()+(this.position.getY()-ccY));
            else
                nextCell=new Position(this.position.getX()+(this.position.getX()-ccX),this.position.getY()+(this.position.getY()-ccY));
       if(!nextCell.onGrid())
           return null;
       if(b.getGrid()[nextCell.getX()][nextCell.getY()].isDisabled()||b.getGrid()[nextCell.getX()][nextCell.getY()].getBuilder()!=null)
              return null;
       return nextCell;
    }
}