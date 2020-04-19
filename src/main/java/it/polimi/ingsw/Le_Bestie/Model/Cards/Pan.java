package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Pan
 * you also win if your Builder
 * moves down two or more levels
 * @author VeronicaRovelli
 */

public class Pan extends GodCard{

    public Pan(String name) {
        super(name);
    }

    @Override
    public int move(Board b, Builder w, Cell c, boolean usePower) {
        return super.move(b, w, c, usePower);
    }

    @Override
    public int build(Board b,Builder w, Cell c, boolean usePower) {
        return super.build(b,w,c,usePower);
    }

    @Override
    public boolean HasWon(Cell c,Cell currentCell) {
        return (currentCell.getLevel()<c.getLevel()&&c.getLevel()==3)||currentCell.getLevel()-c.getLevel()>=2;
    }

    @Override
    public boolean HasLost(Player player, Board b) {
        return super.HasLost(player,b);
    }
}