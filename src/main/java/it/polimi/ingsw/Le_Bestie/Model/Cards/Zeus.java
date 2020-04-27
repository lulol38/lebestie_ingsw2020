package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;

/**
 * Class Zeus
 * your worker may build a block under itself
 * @author VeronicaRovelli
 */

public class Zeus extends GodCard{

    public Zeus(String name) {
        super(name);
    }

    @Override
    public int build(Board b, Builder w, Cell c, boolean usePower) {

        Cell currentCell=b.getGrid()[w.getPosition().getX()][w.getPosition().getY()];

        if(w.possibleBuilds(b).contains(c)||c==currentCell)
        {
            if(b.getRemainingPieces(c.getLevel()+1)==0)
                return 2;
            c.addLevel();
            b.removePiece(c.getLevel());
            return 1;
        }
        return 0;
    }
}
