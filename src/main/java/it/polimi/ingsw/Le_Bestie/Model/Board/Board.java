package it.polimi.ingsw.Le_Bestie.Model.Board;
/**
 *
 */
public class Board {

    final int square=5;
    private final Cell[][] cells=new Cell[square][square];

    public Board() {
    }

    public Cell[][] getBoard() {
        return this.cells;
    }

}