
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

public class Artemis extends GodCard{

    private Cell startingCell;
    private boolean firstMoveDone;


    public Artemis(String name) {
        super(name);
    }

   @Override
    public int move(Board b, Builder w, Cell c, boolean usePower) {
            //no power
            if(!usePower)
                return super.move(b,w,c, usePower);

            //first move
            if(!firstMoveDone)
            {
                int x=super.move(b,w,c, usePower);
                if(x==1)
                {
                    firstMoveDone=true;
                    startingCell=c;
                    return 3;
                }
                else
                    return x;
            }
            //second move
            else
            {
               if(c!=startingCell)
               {
                   firstMoveDone=false;
                   return super.move(b,w, c,usePower);
               }
               else
                   return 0;
            }
    }

    @Override
    public int build(Board  b,Builder w, Cell c, boolean usePower) {
        return super.build(b,w,c,usePower);
    }

    @Override
    public boolean HasWon(Cell c,Cell currentCell) {
        return super.HasWon(c,currentCell);
    }

    @Override
    public boolean HasLost(Player player, Board b) {
        return super.HasLost(player, b);
    }
}
