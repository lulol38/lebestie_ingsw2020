package it.polimi.ingsw.Le_Bestie.Model.Board;
/**
 *Class Board implements board of the game
 * @author Davide Carini
 */
public class Board {

    final int square=5;
    private static Cell[][] grid;

    public Board() {
        grid=new Cell[square][square];
        for (int y = 0; y < square; y++) {
            for (int x = 0; x < square; x++) {
                grid[y][x] = new Cell(0,x,y);
            }
        }

    }
    public static Cell[][] getGrid() {
        return Board.grid;
    }

}