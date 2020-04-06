package it.polimi.ingsw.Le_Bestie.Model.Board;
/**
 *
 */
public class Board {

    private final Cell[][] cells=new Cell[5][5];

    public Board() {
    }

    public Cell[][] getBoard() {
        return this.cells;
    }

}