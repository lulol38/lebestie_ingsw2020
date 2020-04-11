
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

 //   private boolean secondMove;
    private Cell startingCell;


    public Artemis(String name) {
        super(name);
//       secondMove=false;
    }

   @Override
    public int move(Board b, Builder w, Cell c, boolean usePower) {

        //first move
            if(!usePower)
            {
                int x=super.move(b,w,c, usePower);
                if(x==1)
                {
                    startingCell=c;
                    return 3;
                }
                else
                    return x;
            }
            //second optional move
            else
            {
               if(c!=startingCell)
                   return super.move(b,w, c,usePower);
               else
                   return 0;
            }
    }

    @Override
    public boolean build(Board  b,Builder w, Cell c, boolean usePower) {
        return super.build(b,w,c,usePower);
    }

    @Override
    public boolean HasWon(Cell c) {
        return super.HasWon(c);
    }

    @Override
    public boolean HasLost(Player player, Board b) {
        return super.HasLost(player, b);
    }
}
