
package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Atlas
 * your Builder may build a dome at any level
 * @author VeronicaRovelli
 */

public class Atlas extends GodCard{

    public Atlas(String name) {
        super(name);
    }

    @Override
    public int move(Board b, Builder w, Cell c, boolean usePower) {
        return super.move(b,w,c,usePower);
    }

    @Override
    public boolean build(Board b, Builder w, Cell c, boolean usePower) {

        //build without power
        if(!usePower)
            return super.build(b,w, c,usePower);

        //build with power
        else if (w.possibleBuilds(b).contains(c)) {
                    c.setLevel(4);
                    return true;
                }

            return false;
    }

    @Override
    public boolean HasWon(Cell c) {
        return super.HasWon(c);
    }

    @Override
    public boolean HasLost(Player player,Board b) {
        return super.HasLost(player,b);
    }
}