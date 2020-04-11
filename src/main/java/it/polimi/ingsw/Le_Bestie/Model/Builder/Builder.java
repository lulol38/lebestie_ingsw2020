package it.polimi.ingsw.Le_Bestie.Model.Builder;

import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import java.util.ArrayList;

public class Builder {

    private Color color;
    private String idBuilder;
    private boolean disabled;
    private Position position;
    private Player player;

    public Builder(Position position){
        this.position=position;

    }

 //setter e getter
    public void setPlayer(Player player) {
        this.player=player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
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

    public boolean getDisabled(){ return this.disabled; }

    
/*
    public void checkDisabled() {   }
 */

    public ArrayList<Position> possibleMoves() {

        ArrayList<Position> possibleMoves = new ArrayList<>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                Position tryMove = new Position(i,j);
                if (!(i == y && j == x)&&tryMove.onGrid())
                    possibleMoves.add(tryMove);
                }
        }
        return possibleMoves;
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

    public void setCell(int i, int i1, int i2) {
    }
}