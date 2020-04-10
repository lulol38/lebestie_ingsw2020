package it.polimi.ingsw.Le_Bestie.Model.Builder;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.util.ArrayList;

public class Builder {

    private Color color;
    private String idBuilder;
    private boolean disabled;
    private Cell cell;
    private Player player;

    public Builder() {

    }

 //setter e getter
    public void setPlayer(Player player) {
        this.player=player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setColor(Color color){
        this.color=color;
    }

    public Color getColor(){
        return this.color;
    }

    public void setIdBuilder(String idBuilder){
        this.idBuilder=idBuilder;
    }

    public String getIdBuilder(){
        return this.idBuilder;
    }

    public void setDisabled(boolean disabled){
        this.disabled=disabled;
    }

    public boolean getDisabled(){
        return this.disabled;
    }

    public void setCell(Cell cell)
    {
        this.cell=cell;
    }

    public Cell getCell()
    {
        return this.cell;
    }
    
/*
    public void checkDisabled() {   }
 */

    public ArrayList<Cell> possibleMoves() {
        ArrayList<Cell> temp=new ArrayList<Cell>();


        temp.add(Board.getGrid()[cell.getPositionX()][1]);


        return null;
    }

    public ArrayList<Cell> possibleBuilds() {
        return null;
    }

    public ArrayList<Cell> possibleSwitch() {
        return null;
    }

    public ArrayList<Cell> possibleLevelUp() {
        return null;
    }

}