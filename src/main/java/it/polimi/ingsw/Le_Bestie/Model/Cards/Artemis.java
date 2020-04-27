package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.Network.Messages.Message;

/**
 * Class Artemis
 * your Builder may move one additional time,
 * but not back to its initial space
 * @author VeronicaRovelli
 */

public class Artemis extends GodCard {

    private Cell startingCell;
    private String Message;

    public Artemis(String name,String path) {
        super(name,path);
        startingCell = null;
        Message="Do you want to move one additional time? (NOT back!!)";
    }

    @Override
    public String getMessage() {
        return Message;
    }

    @Override
    public int move(Board b, Builder w, Cell c, boolean usePower) {

        //first move
        if (startingCell == null) {
            Cell currentCell = b.getGrid()[w.getPosition().getX()][w.getPosition().getY()];
            int x = super.move(b, w, c, usePower);
            if (x == 1) {
                startingCell = currentCell;
                if(w.possibleMoves(b, notMoveUp).size()==1&&w.possibleMoves(b, notMoveUp).get(0)==startingCell)
                    return 1;
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

