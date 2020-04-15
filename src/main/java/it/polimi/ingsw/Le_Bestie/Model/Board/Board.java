package it.polimi.ingsw.Le_Bestie.Model.Board;
/**
 *Class Board implements board of the game
 * @author Davide Carini
 */
public class Board {

    private static final int ROW=5;
    private static final int COL=5;
    private static Cell[][] grid;

    public Board() {
        grid=new Cell[ROW][COL];
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                grid[x][y] = new Cell(1);
                grid[x][y].setPosition(new Position(x, y));
            }
        }
    }

    public Cell[][] getGrid() {
        return this.grid;
    }

}