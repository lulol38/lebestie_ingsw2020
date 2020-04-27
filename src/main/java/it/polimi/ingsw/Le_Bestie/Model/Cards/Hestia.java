package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;

/**
 * Class Hestia
 * your worker may build on additional time,
 * but this cannot be on a perimeter space
 * @author VeronicaRovelli
 */

public class Hestia extends GodCard{

    private boolean firstBuilt;
    private String Message;

    public Hestia(String name,String path) {
        super(name,path);
        firstBuilt=false;
        Message="Do you want to build one additional time? (NOT on a perimeter space!)";
    }

    @Override
    public String getMessage() {
        return Message;
    }

    @Override
    public int build(Board b, Builder w, Cell c, boolean usePower) {

        //first built
        if(!firstBuilt)
        {
            int x=super.build(b,w,c,usePower);
            if(x==1)
            {
                if(w.possibleBuilds(b).size()==0)
                    return 1;

                for(int i=0;i<w.possibleBuilds(b).size();i++) {
                    if (w.possibleBuilds(b).get(i).getPosition().getY() != 0 && w.possibleBuilds(b).get(i).getPosition().getY() != 4 && w.possibleBuilds(b).get(i).getPosition().getX() != 0 && w.possibleBuilds(b).get(i).getPosition().getX() != 4)
                    {
                        firstBuilt=true;
                        return 3;
                    }
                }

                return 1;
            }
            return x;
        }
        //second built
        else {
            if (!usePower) {
                if(c.getPosition().getY()==0||c.getPosition().getY()==4||c.getPosition().getX()==0||c.getPosition().getX()==4)
                    return 0;

                int x=super.build(b, w, c, usePower);
                if(x==1)
                    firstBuilt=false;
                return x;
            }

            else
            {
                firstBuilt = false;
                return 1;
            }

        }
    }
}
