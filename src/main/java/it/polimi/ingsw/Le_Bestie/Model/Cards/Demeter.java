package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Demeter
 * your Builder may build one additional time,
 * but not on the same space
 * @author VeronicaRovelli
 */

public class Demeter extends GodCard{

    private Cell firstBuiltCell;

    public Demeter(String name) {
        super(name);
        firstBuiltCell=null;
    }

    @Override
    public int build(Board b,Builder w, Cell c, boolean usePower) {

        //first built
        if(firstBuiltCell==null)
        {
            int x=super.build(b,w,c,usePower);
            if(x==1)
            {
                firstBuiltCell=c;
                return 3;
            }
            return x;
        }
        //second built
        else {
            if (!usePower) {
                if (c != firstBuiltCell) {
                    int x=super.build(b, w, c, usePower);
                    if(x==1)
                        firstBuiltCell = null;
                    return x;
                } else return 0;
            }
            else
            {
                firstBuiltCell = null;
                return 1;
            }

        }
    }

}

