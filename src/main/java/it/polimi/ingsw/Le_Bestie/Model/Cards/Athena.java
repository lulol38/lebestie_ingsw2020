
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

public class Athena extends GodCard{

    public Athena(String name) {

        super(name);
    }

    @Override
    public boolean move(Builder w, Cell c) {
        MatchState.setNotMoveUp(false);
        int startingLevel=c.getLevel();
        boolean x=super.move(w,c);

        //Athena power
        if(w.getCell().getLevel()>startingLevel)
            MatchState.setNotMoveUp(true);

        return x;
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