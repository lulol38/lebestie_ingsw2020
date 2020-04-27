package it.polimi.ingsw.Le_Bestie.Model.Board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

/**
 *Class Board implements board of the game
 * @author Davide Carini
 */
public class Board implements Serializable {

    private final int ROW=5;
    private final int COL=5;
    private Cell[][] grid;
    private ArrayList<Integer> remainingPieces;

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

    public Cell[][] getGrid() {
        return this.grid;
    }

    public void removePiece(int level) {
        this.remainingPieces.set(level-1,this.remainingPieces.get(level-1)-1);
    }
    public int getRemainingPieces(int level) {
        return remainingPieces.get(level-1);
    }
}