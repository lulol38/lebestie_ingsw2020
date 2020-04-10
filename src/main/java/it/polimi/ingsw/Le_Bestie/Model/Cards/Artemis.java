/**
 * Class Card
 * describes a generic card
 * @Bob
 */

package it.polimi.ingsw.Le_Bestie.Model.Cards;


import it.polimi.ingsw.Le_Bestie.Controller.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;

public class Artemis extends GodCard{

    private boolean secondMove;
    private Cell startingCell;


    public Artemis(String name) {
        super(name);
        secondMove=false;
    }

    @Override
    public boolean move(Builder w, Cell c) {

        //first move
        if(!MatchState.getHasMoved())
            if(super.move(w,c))
            {
                startingCell=c;
                return true;
            }

        //second optional move
        if(MatchState.getHasMoved()&&!secondMove&&c!=startingCell) {
            super.move(w, c);
            secondMove = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean build(Builder w, Cell c) {
        return super.build(w,c);
    }

    @Override
    public boolean HasWon() {
        return super.HasWon();
    }

    @Override
    public boolean HasLost() {
        return super.HasLost();
    }
}
