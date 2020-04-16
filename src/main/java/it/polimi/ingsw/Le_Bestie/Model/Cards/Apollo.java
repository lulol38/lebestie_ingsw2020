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

    public Apollo(String name) {
        super(name);
    }

    @Override
    public int move(Board b, Builder w, Cell c,boolean usePower) {
        if (w.possibleMoves(b, notMoveUp).contains(c))
            return super.move(b, w, c,usePower);

        if (w.possibleSwitch(b, notMoveUp).contains(c)) {
            Cell currentCell=b.getGrid()[w.getPosition().getX()][w.getPosition().getX()];
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



    @Override
    public int build(Board b, Builder w, Cell c, boolean usePower) {
        return super.build(b,w, c,usePower);
    }

    @Override
    public boolean HasWon(Cell c,Cell currentCell) {
        return super.HasWon(c,currentCell);
    }

    @Override
    public boolean HasLost(Player player,Board b) {

        if (player.getBuilder1().possibleSwitch(b, notMoveUp).size() == 0&&player.getBuilder1().possibleMoves(b, notMoveUp).size() == 0)
            player.getBuilder1().setDisabled(true);
        else
            player.getBuilder1().setDisabled(true);
        if (player.getBuilder2().possibleSwitch(b, notMoveUp).size() == 0&&player.getBuilder2().possibleMoves(b, notMoveUp).size() == 0)
            player.getBuilder2().setDisabled(true);
        else
            player.getBuilder2().setDisabled(true);

        return player.getBuilder1().getDisabled() && player.getBuilder2().getDisabled();
    }
}