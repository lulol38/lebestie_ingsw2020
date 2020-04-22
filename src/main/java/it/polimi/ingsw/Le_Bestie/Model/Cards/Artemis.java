package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Artemis
 * your Builder may move one additional time,
 * but not back to its initial space
 * @author VeronicaRovelli
 */

public class Artemis extends GodCard {

    private Cell startingCell;
    private String Message;

    public Artemis(String name) {
        super(name);
        startingCell = null;
        Message="Do you want to move one additional time? (NOT back!!)";
    }

    @Override
    public int move(Board b, Builder w, Cell c, boolean usePower) {

        //first move
        if (startingCell == null) {
            Cell currentCell = b.getGrid()[w.getPosition().getX()][w.getPosition().getX()];
            int x = super.move(b, w, c, usePower);
            if (x == 1) {
                startingCell = currentCell;
                return 3;
            } else
                return x;
        }
        //second move
        else {
            if (c != startingCell)
                return super.move(b, w, c, usePower);
            else
                return 0;
        }
    }

    @Override
    public int build(Board b, Builder w, Cell c, boolean usePower) {
        startingCell = null;
        return super.build(b, w, c, usePower);
    }
}

