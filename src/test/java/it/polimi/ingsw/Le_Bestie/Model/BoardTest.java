package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void before(){
        board=new Board();
    }

    @Test
    @DisplayName("Number of pieces")
    public void numberPiecesTest()
    {
        board=new Board();
        board.removePiece(1);
        board.removePiece(1);
        board.removePiece(1);

        assertNotNull(board.getGrid());
        assertEquals(19,board.getRemainingPieces(1));

    }


}
