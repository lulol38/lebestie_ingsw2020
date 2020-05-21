package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;

/**
 * Class Artemis
 * your Builder may move one additional time,
 * but not back to its initial space
 * @author VeronicaRovelli
 */

public class Artemis extends GodCard {

    /**
     * The initial space of the builder before the move
     */
    private Cell startingCell;
    /**
     * A message to send to the client to decide if use Artemis' power
     */
    private String Message;

    public Artemis(String name,String path,String description) {
        super(name,path,description);
        startingCell = null;
        Message="Do you want to move one additional time?\n(NOT back!!)";
    }

    @Override
    public String getMessage() {
        return Message;
    }


    /**
     * Method that overrides the move of GodCard.
     * The builder may move one additional time, but not back to its initial space.
     * If the client wants to use Artemis power,
     * the move method is called twice.
     */
    @Override
    public int move(Board b, Builder w, Cell c, boolean usePower) {

        //first move
        if (startingCell == null) {
            Cell currentCell = b.getGrid()[w.getPosition().getX()][w.getPosition().getY()];
            int x = super.move(b, w, c, usePower);
            if (x == 1) {
                startingCell = currentCell;
                if(w.possibleMoves(b).size()==1&&w.possibleMoves(b).get(0)==startingCell)
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

    /**
     * Method that initializes startingCell for the next turn and calls the usual build of GodCard.
     */
    @Override
    public int build(Board b, Builder w, Cell c, boolean usePower) {
        startingCell = null;
        return super.build(b, w, c, usePower);
    }
}

