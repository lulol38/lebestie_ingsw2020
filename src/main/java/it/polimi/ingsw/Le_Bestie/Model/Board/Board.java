package it.polimi.ingsw.Le_Bestie.Model.Board;
/**
 *
 */
public class Board {

    final int square=5;
    private final Cell[][] grid=new Cell[square][square];

    public Board() {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                grid[y][x] = new Cell(0,x,y);
            }
        }

    }

    public Cell[][] getGrid() {
        return this.grid;
    }

}