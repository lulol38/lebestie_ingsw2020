package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Game.MatchState;
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


    /**
     * Method that overrides the move of GodCard.
     * The builder can move normally, but he can also move in an opponent Builder's cell
     * and force him one space straight backwards if the cell is unoccupied.
     */
    @Override
    public int move(Board b,Builder w, Cell c, boolean usePower) {
        if (w.possibleMoves(b).contains(c))
            return super.move(b, w, c, usePower);

        if (w.possibleSwitch(b).contains(c)) {
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


    /**
     * Method that overrides the HasLost of GodCard.
     * A builder can move in usual possible moves but also in the neighboring opponent Builder's cell
     * if the next cell in the same direction is unoccupied.
     */
    @Override
    public int HasLost(Player player, Board b) {
        if (player.getBuilder1().possibleMoves(b).size() == 0&&notPossibleSwitchMinotaur(b, player.getBuilder1()))
            player.getBuilder1().setDisabled(true);
        else
            player.getBuilder1().setDisabled(false);

        if (player.getBuilder2().possibleMoves(b).size() == 0&&notPossibleSwitchMinotaur(b, player.getBuilder2()))
            player.getBuilder2().setDisabled(true);
        else
            player.getBuilder2().setDisabled(false);

        if(player.getBuilder1().getDisabled() && player.getBuilder2().getDisabled())
            return 1;

        return 0;
    }

    /**
     * Method that checks if the builder of a player who has Minotaur GodCard, can switch with another player's builder
     *
     * @param b the current board
     * @return if the builder of a player who has Minotaur GodCard, can switch with another player's builder
     */
    public boolean notPossibleSwitchMinotaur(Board b, Builder builder) {
        boolean notMoveUp = MatchState.getNotMoveUp();
        if (builder.possibleSwitch(b).size() == 0)
            return true;
        else {
            Cell currentCell = b.getGrid()[builder.getPosition().getX()][builder.getPosition().getY()];
            ArrayList<Cell> possibleSwitch = builder.possibleSwitch(b);

            for (int i = 0; i < possibleSwitch.size(); i++) {
                if (possibleSwitch.get(i).nextCellFree(b, currentCell) != null)
                    return false;
            }
            return true;
        }
    }
}

