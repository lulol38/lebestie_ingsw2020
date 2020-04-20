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
    public boolean HasWon(Cell c,Cell currentCell) {
        return (currentCell.getLevel()<c.getLevel()&&c.getLevel()==3)||currentCell.getLevel()-c.getLevel()>=2;
    }

}