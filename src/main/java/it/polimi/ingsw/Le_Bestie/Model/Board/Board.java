package it.polimi.ingsw.Le_Bestie.Model.Board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class represents the Board used to play the game. It is a 5x5 matrix of cells.
 * @author Davide Carini
 */

public class Board implements Serializable {

    /**
     * Number of board lines: 5
     */
    private final int ROW=5;
    /**
     * Number of board columns: 5
     */
    private final int COL=5;
    /**
     * Matrix of cells
     */
    private Cell[][] grid;
    /**
     * This array counts how many building pieces are available for every level
     */
    private ArrayList<Integer> remainingPieces;

    private boolean notMoveUp;

    /**
     * Builds the new board with every cell at level zero
     */
    public Board() {
        remainingPieces=new ArrayList<>(Arrays.asList(22,18,14,18));
        grid=new Cell[ROW][COL];
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                grid[x][y] = new Cell(0);
                grid[x][y].setPosition(new Position(x, y));
            }
        }
    }

    /**
     * Getters
     */
    public int getROW(){return this.ROW;}
    public int getCOL(){return this.COL;}
    public int getRemainingPieces(int level) {
        return remainingPieces.get(level-1);
    }
    public Cell[][] getGrid() {
        return this.grid;
    }
    public boolean getNotMoveUp() { return notMoveUp; }

    /**
     * Setter
     */
    public void setNotMoveUp(boolean notmoveup) { notMoveUp=notmoveup; }

    /**
     * Method to remove building piece just used in the build, from the array that counts available pieces
     *
     * @param level the level of the build just done
     */
    public void removePiece(int level) {
        this.remainingPieces.set(level-1,this.remainingPieces.get(level-1)-1);
    }
}