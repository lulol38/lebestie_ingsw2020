package it.polimi.ingsw.Le_Bestie.Model.Board;
/**
 *Class Board implements board of the game
 * @author Davide Carini
 */
public class Board {

    private static final int square=5;
    private static Cell[][] grid;

    public Board() {
        grid=new Cell[square][square];
        for (int x = 0; x < square; x++) {
            for (int y = 0; y < square; y++) {
                grid[x][y] = new Cell(0);
                grid[x][y].setPosition(new Position(x, y));
            }
        }
    }

    public Cell[][] getGrid() {
        return this.grid;
    }

}