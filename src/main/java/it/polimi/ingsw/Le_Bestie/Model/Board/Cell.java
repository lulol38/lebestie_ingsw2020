package it.polimi.ingsw.Le_Bestie.Model.Board;

import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import java.io.Serializable;

/**
 * This class implements cell.
 * A cell is located by a position on the board, has a building level and can be occupied by a builder.
 * There is 5 different levels, from 0 (ground) to 4. Level 4 is the dome: in this case the cell is disabled, that means any builder can't move on that.
 * A tower with 3 blocks and a dome is a Complete Tower. Pay attention: a cell could be disabled with a dome, but not be a complete tower!
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

    /**
     * Getters
     */
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


    /**
     * Setters
     */
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
    public void setLevel(int level)
    {
        if(level>3){
            this.disabled=true;
        }
        this.level=level;
    }


    /**
     * Method to add a level to this cell. After the third level there is the dome, that disables the cell and makes up a complete tower.
     */
    public void addLevel() {
        this.level++;
        if(this.level>3) {
            setDisabled(true);
            setCompleteTower(true);
        }
    }

    // !!!!! "currentCell" is the cell in which is OUR Builder and "this" is the cell in which is OPPONENT builder !!!!!!!!!!

    /**
     * Method that check if the builder in this cell can switch in the cell straight backwards than the currentCell.
     *
     * @param b the current board
     * @param currentCell the cell in which is the builder of the current player that wants to move in this cell
     * @return the position of the cell straight backwards if it is unoccupied, otherwise null
     */
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