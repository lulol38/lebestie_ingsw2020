
package it.polimi.ingsw.Le_Bestie.Model.Cards;


import it.polimi.ingsw.Le_Bestie.Controller.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Artemis
 * your Builder may move one additional time,
 * but not back to its initial space
 * @VeronicaRovelli
 */

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
        if(MatchState.getHasMoved()&&!secondMove&&c!=startingCell&&MatchState.getUsePower()) {
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
    public boolean HasLost(Player player) {
        return super.HasLost(player);
    }
}
