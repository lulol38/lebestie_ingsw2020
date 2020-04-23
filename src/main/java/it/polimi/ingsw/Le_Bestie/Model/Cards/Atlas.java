
package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Atlas
 * your Builder may build a dome at any level
 * @author VeronicaRovelli
 */

public class Atlas extends GodCard{

    private boolean checkUsePower;
    private String Message;

    public Atlas(String name) {
        super(name);
        checkUsePower=false;
        Message="Do you want to build a dome on this cell?";
    }

    @Override
    public String getMessage() {
        return Message;
    }

    @Override
    public int build(Board b, Builder w, Cell c, boolean usePower) {

        if (c.getLevel() == 3)
            return build(b, w, c, usePower);

        if (!checkUsePower)
        //ask client if usePower or not
        {
            if(w.possibleBuilds(b).contains(c))
            {
                checkUsePower = true;
                return 4;
            }
            else
                return 0;
        } else {
            //it's the second method's call, so client already chooses if use power
            //build without power
            if (usePower) {
                checkUsePower=false;
                return super.build(b, w, c, usePower);
                //here can return only 1 or 2
            }

            //build with power
            else
            { checkUsePower=false;
                if (b.getRemainingPieces(4) == 0)
                    return 2;
                c.setLevel(4);
                b.removePiece(4);
                return 1;
            }
        }
    }

}

