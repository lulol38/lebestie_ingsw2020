package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Prometheus
 * if your Builder does not move up,
 * it may build both before and after moving
 * @author VeronicaRovelli
 */

public class Prometheus extends GodCard{

    private boolean firstBuild;

    public Prometheus(String name) {
        super(name);
    }

    @Override
    public int move(Board b,Builder w, Cell c, boolean usePower) {
        if(!usePower)
            return super.move(b, w, c, usePower);
        else
        {
            if(!firstBuild)
            {
                if(build(b, w, c, usePower)==1) {
                    firstBuild = true;
                    return 3;
                }
                return 0;
            }
            else
            {
                Cell currentCell=b.getGrid()[w.getPosition().getX()][w.getPosition().getX()];
                if(c.getLevel()-currentCell.getLevel()<1) {
                    firstBuild=false;
                    return super.move(b, w, c, usePower);
                }
                else
                    return 0;
            }
        }
    }

    @Override
    public int build(Board b,Builder w, Cell c, boolean usePower) {
        return super.build(b,w,c,usePower);
    }

    @Override
    public boolean HasWon(Cell c, Cell currentCell) {
        return super.HasWon(c,currentCell);
    }


    @Override
    public boolean HasLost(Player player, Board b) {
        return super.HasLost(player,b);
    }
}