package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;

/**
 * Class Triton
 * Each time your Worker moves into a perimeter space,
 * it may immediately move again
 * @author VeronicaRovelli
 */

public class Triton extends GodCard {

    private String Message;
    private boolean firstMove;

    public Triton(String name,String path) {
        super(name,path);
        firstMove=false;
        Message="Do you want to move one additional time?";
    }

    @Override
    public String getMessage() {
        return Message;
    }

    @Override
    public int move(Board b, Builder w, Cell c, boolean usePower) {

        //first move
        if (!firstMove) {
            int x = super.move(b, w, c, usePower);
            if (x == 1) {
                if(c.getPosition().getY()==0||c.getPosition().getY()==4||c.getPosition().getX()==0||c.getPosition().getX()==4)
                {
                    if(w.possibleMoves(b,notMoveUp).size()==0)
                        return 1;
                    else {
                        firstMove = true;
                        return 3;
                    }
                }
                return 1;
            } else
                return x;
        }
        //second move
        else {
            if (w.possibleMoves(b,notMoveUp).contains(c))
                return super.move(b, w, c, usePower);
            else
                return 0;
        }

    }

    @Override
    public int build(Board b, Builder w, Cell c, boolean usePower) {
       firstMove=false;
        return super.build(b, w, c, usePower);
    }
}
