package it.polimi.ingsw.Le_Bestie.Model.Builder;

import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;

import java.awt.*;
import java.util.*;

public class Builder {

    private Color color;
    private String idBuilder;
    private boolean disabled;

    public Builder() {

    }

 //set e get
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



    public void checkDisabled() {

    }

    public ArrayList<Cell> possibleMoves() {
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