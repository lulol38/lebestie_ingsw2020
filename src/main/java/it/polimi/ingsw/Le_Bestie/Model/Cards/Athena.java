
package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Athena
 * if one of your Builders moved up on your last turn,
 * opponent Builders cannot move up this turn
 * @author VeronicaRovelli
 */

public class Athena extends GodCard{

    public Athena(String name,String path,String description) {
        super(name,path,description);
    }

    @Override
    public int move(Board b, Builder w, Cell c, boolean usePower) {
        notMoveUp=false;
        int startingLevel=b.getGrid()[w.getPosition().getX()][w.getPosition().getY()].getLevel();
        int x=super.move(b,w,c,usePower);
        if(x==1) {
            //Athena power
            if (c.getLevel() > startingLevel)
              notMoveUp=true;
              return 1;
        }
        else
            return x;

    }


}