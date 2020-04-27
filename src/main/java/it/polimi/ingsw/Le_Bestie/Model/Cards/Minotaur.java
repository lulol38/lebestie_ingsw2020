package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.util.ArrayList;

/**
 * Class Minotaur
 * your Builder may move into an opponent Builder's space,
 * if their Builder can be forced one space straight backwards
 * to an unoccupied space at any level
 * @author VeronicaRovelli
 */

public class Minotaur extends GodCard{

    public Minotaur(String name,String path,String description) {
        super(name,path,description);
    }

    @Override
    public int move(Board b,Builder w, Cell c, boolean usePower) {
        if (w.possibleMoves(b, notMoveUp).contains(c))
            return super.move(b, w, c, usePower);

        if (w.possibleSwitch(b, notMoveUp).contains(c)) {
            Cell currentCell = b.getGrid()[w.getPosition().getX()][w.getPosition().getY()];
            Position nextCellPosition = c.nextCellFree(b, currentCell);
            if (nextCellPosition != null) {
                //winner condition
                if (HasWon(c, currentCell))
                    return 2;

                Cell nextCell = b.getGrid()[nextCellPosition.getX()][nextCellPosition.getY()];
                currentCell.setBuilder(null);

                //change cell to the opponent Builder
                nextCell.setBuilder(c.getBuilder());
                c.getBuilder().setPosition(nextCell.getPosition());

                //change my cell
                c.setBuilder(w);
                w.setPosition(c.getPosition());

                return 1;
            }
        }
        return 0;
     }

    @Override
    public int HasLost(Player player, Board b) {
        if (player.getBuilder1().possibleMoves(b, notMoveUp).size() == 0&&player.getBuilder1().notPossibleSwitchMinotaur(b,notMoveUp))
            player.getBuilder1().setDisabled(true);
        else
            player.getBuilder1().setDisabled(false);

        if (player.getBuilder2().possibleMoves(b, notMoveUp).size() == 0&&player.getBuilder2().notPossibleSwitchMinotaur(b,notMoveUp))
            player.getBuilder2().setDisabled(true);
        else
            player.getBuilder2().setDisabled(false);

        if(player.getBuilder1().getDisabled() && player.getBuilder2().getDisabled())
            return 1;

        return 0;
    }
}

