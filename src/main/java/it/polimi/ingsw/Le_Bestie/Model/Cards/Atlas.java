
package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Game.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Card
 * describes a generic card
 * @VeronicaRovelli
 */

public class Atlas extends GodCard{

    public Atlas(String name) {
        super(name);
    }

    @Override
    public boolean move(Builder w, Cell c) {
        return super.move(w,c);
    }

    @Override
    public boolean build(Builder w, Cell c) {

        //move without power
        if(!MatchState.getUsePower())
            return super.build(w, c);

        //move with power
        else {
            if (MatchState.getHasMoved() && w.possibleBuilds().contains(c)) {
                //is dome available?
                if (MatchState.getRemainingPieces(4) > 0) {
                    c.setLevel(4);
                    MatchState.checkPieces(4);

                    //is the builder locked, after his build?
                    if (w.possibleMoves().size() == 0)
                        w.setDisabled(true);
                    return true;
                }
            }
            HasLost(w.getPlayer());
            return false;
        }
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