
package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Controller.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.util.*;

/**
 * Class Card
 * describes a generic card
 * @VeronicaRovelli
 */

public class Apollo extends GodCard{

    public Apollo(String name) {
        super(name);
    }

    @Override
    public boolean move(Builder w, Cell c) {
        if(!MatchState.getHasMoved()&&(w.possibleMoves().contains(c)||w.possibleSwitch().contains(c)))
        {
            //winner condition
            if(c.getLevel()==3)
                HasWon();

            if(w.possibleMoves().contains(c)) {
                //free the previous builder's cell
                w.getCell().setBuilder(null);
                w.getCell().setOccupied(false);

                //new cell
                w.setCell(c);
                c.setBuilder(w);
                c.setOccupied(true);
            }

            if(w.possibleSwitch().contains(c))
            {
                //change cell to the enemy builder
                w.getCell().setBuilder(c.getBuilder());
                c.getBuilder().setCell(w.getCell());

                //change my cell
                w.setCell(c);
                c.setBuilder(w);
            }

            MatchState.setHasMoved(true);
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
        if(player.getBuilder2().possibleMoves().size()==0&&player.getBuilder2().possibleSwitch().size()==0)
            player.getBuilder2().setDisabled(true);
        if(player.getBuilder1().possibleMoves().size()==0&&player.getBuilder1().possibleSwitch().size()==0)
            player.getBuilder1().setDisabled(true);
        if(player.getBuilder1().getDisabled()&&player.getBuilder2().getDisabled())
            return true;
        return false;}
    }