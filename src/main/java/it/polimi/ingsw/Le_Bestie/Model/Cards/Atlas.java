/**
 * Class Card
 * describes a generic card
 * @Bob
 */

package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Controller.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;

import java.util.*;

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
        return false;
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