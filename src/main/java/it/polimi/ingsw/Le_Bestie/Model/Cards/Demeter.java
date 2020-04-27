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
    private String Message;

    public Demeter(String name,String path,String description) {
        super(name,path,description);
        firstBuiltCell=null;
        Message="Do you want to build one additional time? (NOT on the same space!)";
    }

    @Override
    public String getMessage() {
        return Message;
    }

    @Override
    public int build(Board b,Builder w, Cell c, boolean usePower) {

        //first built
        if(firstBuiltCell==null)
        {
            int x=super.build(b,w,c,usePower);
            if(x==1)
            {
                if((w.possibleBuilds(b).size()==1&&w.possibleBuilds(b).get(0)==c)||w.possibleBuilds(b).size()==0)
                    return 1;
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

