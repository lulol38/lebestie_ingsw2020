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

    private Cell startingCell;
    private boolean firstBuiltDone;

    public Demeter(String name) {
        super(name);
    }

    @Override
    public int move(Board b, Builder w, Cell c, boolean usePower) {
        return super.move(b,w,c,usePower);
    }

    @Override
    public int build(Board b,Builder w, Cell c, boolean usePower) {
        //no power
        if(!usePower)
            return super.build(b,w,c,usePower);

           //first built
            if(!firstBuiltDone)
            {
                if(super.build(b,w,c,usePower)==1)
                {
                    firstBuiltDone=true;
                    startingCell=c;
                    return 2;
                }
                return 0;
            }
            //second built
            else
            {
                if(c!=startingCell) {
                    firstBuiltDone=false;
                    return super.build(b, w, c, usePower);
                }
                else return 0;
            }
    }

    @Override
    public boolean HasWon(Cell c,Cell currentCell) {
        return super.HasWon(c,currentCell);
    }


    @Override
    public boolean HasLost(Player player, Board b) {
        return super.HasLost(player,b);
    }
}