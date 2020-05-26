package it.polimi.ingsw.Le_Bestie.Model.Builder;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Game.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class implements Builder, that is the pawn which moves on the Board.
 * A builder is property of a player, is located by a position on the board and can be disabled if it hasn't possible moves.
 */

public class Builder implements Serializable {

    private boolean disabled;
    private Position position;
    private Player player;

    public Builder(Position position){
        this.position=position;
        this.disabled=false;
    }

    /**
     * Getters
     */
    public Position getPosition() { return position; }
    public Player getPlayer() {
        return player;
    }
    public boolean getDisabled(){ return this.disabled; }

    /**
     * Setters
     */
    public void setPlayer(Player player) {
        this.player=player;
    }
    public void setPosition(Position position) { this.position = position; }
    public void setDisabled(boolean disabled){
        this.disabled=disabled;
    }


    /**
     * Method that obtains an array of cells in which the builder can move
     *
     * @param b the current board
     * @return an array of cells in which the builder can move
     */
    public ArrayList<Cell> possibleMoves(Board b) {
        boolean notmoveup = b.getNotMoveUp();
        Cell currentCell=b.getGrid()[this.getPosition().getX()][this.getPosition().getY()];
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                Position tryMove = new Position(j,i);
                if (!(i == y && j == x)&&tryMove.onGrid())
                    possibleMoves.add(tryMove);
                }
        }
        ArrayList<Cell> around=new ArrayList<>();
        for(int i=0;i<possibleMoves.size();i++)
            around.add(b.getGrid()[possibleMoves.get(i).getX()][possibleMoves.get(i).getY()]);
        for(Iterator<Cell> i = around.iterator(); i.hasNext();) {
            Cell c1= i.next();
            if(!notmoveup) {
                if (c1.isDisabled() || c1.getBuilder() != null || c1.getLevel() - currentCell.getLevel() > 1) i.remove();
            }
            else {
                if (c1.isDisabled() || c1.getBuilder() != null || c1.getLevel() - currentCell.getLevel() > 0) i.remove();
            }
        }
        return around;
    }


    /**
     * Method that obtains an array of cells in which the builder can build
     *
     * @param b the current board
     * @return an array of cells in which the builder can build
     */
    public ArrayList<Cell> possibleBuilds(Board b) {
        Cell currentCell=b.getGrid()[this.getPosition().getX()][this.getPosition().getY()];
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                Position tryMove = new Position(j,i);
                if (!(i == y && j == x)&&tryMove.onGrid())
                    possibleMoves.add(tryMove);
            }
        }
        ArrayList<Cell> around=new ArrayList<>();
        for(int i=0;i<possibleMoves.size();i++)
            around.add(b.getGrid()[possibleMoves.get(i).getX()][possibleMoves.get(i).getY()]);
        for(Iterator<Cell> i = around.iterator(); i.hasNext();) {
            Cell c1= i.next();
            if(c1.isDisabled()||c1.getBuilder()!=null) i.remove();
        }
        return around;
    }


    /**
     * Method that obtains an array of cells in which the builder can switch with another player's builder
     *
     * @param b the current board
     * @return an array of cells in which the builder can switch
     */
    public ArrayList<Cell> possibleSwitch(Board b) {
        boolean notmoveup = b.getNotMoveUp();
        Cell currentCell=b.getGrid()[this.getPosition().getX()][this.getPosition().getY()];
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                Position tryMove = new Position(j,i);
                if (!(i == y && j == x)&&tryMove.onGrid())
                    possibleMoves.add(tryMove);
            }
        }
        ArrayList<Cell> around=new ArrayList<>();
        for(int i=0;i<possibleMoves.size();i++)
            around.add(b.getGrid()[possibleMoves.get(i).getX()][possibleMoves.get(i).getY()]);
        for(Iterator<Cell> i = around.iterator(); i.hasNext();) {
            Cell c1= i.next();
            if(!notmoveup) {
                if (c1.isDisabled() || c1.getBuilder() == null || c1.getLevel() - currentCell.getLevel() > 1 || c1.getBuilder().getPlayer() == this.getPlayer())
                    i.remove();
            }
            else {
                if (c1.isDisabled() || c1.getBuilder() == null || c1.getLevel() - currentCell.getLevel() > 0 || c1.getBuilder().getPlayer() == this.getPlayer())
                    i.remove();
            }
        }
        return around;
    }

}