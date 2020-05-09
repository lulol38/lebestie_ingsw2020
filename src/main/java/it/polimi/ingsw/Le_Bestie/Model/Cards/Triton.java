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

    /**
     * A message to send to the client to decide if use Triton' power
     */
    private String Message;
    private boolean firstMove;

    public Triton(String name,String path,String description) {
        super(name,path,description);
        firstMove=false;
        Message="Do you want to move one additional time?";
    }

    @Override
    public String getMessage() {
        return Message;
    }

    /**
     * Method that overrides the move of GodCard.
     * Each time the builder moves into a perimeter space,
     * it may immediately move again.
     * If the client wants to use Triton power,
     * the move method is called twice.
     */
    @Override
    public int move(Board b, Builder w, Cell c, boolean usePower) {

        //first move
        if (!firstMove) {
            int x = super.move(b, w, c, usePower);
            if (x == 1) {
                if(c.getPosition().getY()==0||c.getPosition().getY()==4||c.getPosition().getX()==0||c.getPosition().getX()==4)
                {
                    if(w.possibleMoves(b).size()==0)
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
            if (w.possibleMoves(b).contains(c))
                return super.move(b, w, c, usePower);
            else
                return 0;
        }

    }


    /**
     * Method that initializes firstMove for the next turn and calls the usual build of GodCard.
     */
    @Override
    public int build(Board b, Builder w, Cell c, boolean usePower) {
       firstMove=false;
        return super.build(b, w, c, usePower);
    }
}
