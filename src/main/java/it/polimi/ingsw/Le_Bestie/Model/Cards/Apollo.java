package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Apollo
 * your Builder may move into an opponent Builder's
 * space by forcing their Builder to the space
 * yours just vacated
 * @author VeronicaRovelli
 */

public class Apollo extends GodCard {

    public Apollo(String name,String path,String description) {
        super(name,path,description);
    }


    /**
     * Method that override the move of GodCard.
     * The builder can move normally, but he can also move in an opponent Builder's cell and switch with him.
     */
    @Override
    public int move(Board b, Builder w, Cell c,boolean usePower) {
        if (w.possibleMoves(b).contains(c))
            return super.move(b, w, c,usePower);

        if (w.possibleSwitch(b).contains(c)) {
            Cell currentCell=b.getGrid()[w.getPosition().getX()][w.getPosition().getY()];
            //winner condition
            if(HasWon(c,currentCell))
                return 2;

            //change cell to the opponent Builder
            currentCell.setBuilder(c.getBuilder());
            c.getBuilder().setPosition(currentCell.getPosition());

            //change my cell
            w.setPosition(c.getPosition());
            c.setBuilder(w);

            return 1;
        }
        return 0;
    }


    /**
     * Method that override the HasLost of GodCard.
     * A builder can move in usual possible moves but also in the neighboring opponent Builder's cell
     */
    @Override
    public int HasLost(Player player,Board b) {

        if (player.getBuilder1().possibleSwitch(b).size() == 0&&player.getBuilder1().possibleMoves(b).size() == 0)
            player.getBuilder1().setDisabled(true);
        else
            player.getBuilder1().setDisabled(false);
        if (player.getBuilder2().possibleSwitch(b).size() == 0&&player.getBuilder2().possibleMoves(b).size() == 0)
            player.getBuilder2().setDisabled(true);
        else
            player.getBuilder2().setDisabled(false);

        if(player.getBuilder1().getDisabled() && player.getBuilder2().getDisabled())
            return 1;

        return 0;
    }
}
