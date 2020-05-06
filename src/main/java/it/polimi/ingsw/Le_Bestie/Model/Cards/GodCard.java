package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.io.Serializable;

/**
 * Class GodCard
 * describes a generic turn without power
 * @author VeronicaRovelli
 */

public abstract class GodCard implements Serializable {

    private String name;
    private String message;
    private String path;
    private String description;

    public GodCard(String name,String path,String description){
        this.name=name;
        this.path=path;
        this.description=description;

    }

    /**
     * Getters
     */
    public String getName() { return this.name; }
    public String getPath() { return this.path; }
    public String getDescription(){return this.description;}

    public String getMessage() {
        return message;
    }


    /**
     * Method that makes a request move on the board, only if it is a possible move.
     * A player can move a builder into one of the eight neighboring cells, if it's unoccupied (not containing a builder or dome) and maximum one level higher.
     *
     * @param b the current board
     * @param w the builder to move
     * @param c the selected cell in which move the builder
     * @param usePower if the client chooses to use power or not
     * @return an integer that correspond with the follow cases:
     * 0 if the client must select another cell
     * 1 if the builder correctly moves in the selected cell
     * 2 if the builder's player won
     * 3 if the client must choose to use his GodCard's power (if the client chooses "yes" recall the move method with a new selected cell, otherwise go on with the build)
     * 4 if the client must choose to use his GodCard's power (then recall the move method with the same cell, but if the client chooses "yes" usePower=false, otherwise usePower=true)
     */
    public int move(Board b, Builder w, Cell c,boolean usePower){
        Cell currentCell=b.getGrid()[w.getPosition().getX()][w.getPosition().getY()];
        if(w.possibleMoves(b).contains(c))
        {
            //winner condition
            if(HasWon(c,currentCell))
                return 2;
            currentCell.setBuilder(null);
            c.setBuilder(w);
            w.setPosition(c.getPosition());
            return 1;
        }
        return 0;
    }


    /**
     * Method that makes a request build on the board, only if it is a possible build.
     * A player can build a block or dome on an unoccupied space neighboring the moved builder.
     *
     * @param b the current board
     * @param w the chosen builder
     * @param c the selected cell in which build
     * @param usePower if the client chooses to use power or not
     * @return an integer that correspond with the follow cases:
     * 0 if the client can't build in the selected cell
     * 1 if the builder correctly builds in the selected cell
     * 2 if the builder can't build because there aren't the correct shape of block or dome for the level being build
     * 3 if the client must choose to use his GodCard's power (if the client chooses "yes" recall the build method with a new selected cell and usePower=false, otherwise recall the build method with the same cell and usePower=true)
     * 4 if the client must choose to use his GodCard's power (then recall the build method with the same cell, but if the client chooses "yes" usePower=false, otherwise usePower=true)
     * 5 if the builder's player won
     */
    public int build(Board b,Builder w, Cell c, boolean usePower){
        if(w.possibleBuilds(b).contains(c))
        {
            if(b.getRemainingPieces(c.getLevel()+1)==0)
                return 2;
            c.addLevel();
            b.removePiece(c.getLevel());
            return 1;
        }
        return 0;
    }


    /**
     * Method that checks if the player won.
     * Win condition: when one of your builders moves up on top of level 3 during your turn.
     *
     * @param c the cell in which move the builder
     * @param currentCell the cell in which is the builder before the move
     * @return 1 if the player won, otherwise 0
     */
    public boolean HasWon(Cell c,Cell currentCell) {
        return currentCell.getLevel()<c.getLevel()&&c.getLevel()==3;
    }


    /**
     * Method that checks if the player has lost.
     * If you are unable to perform a move then build on your turn, you lose.
     *
     * @param player the current player
     * @param b the current board
     * @return an integer that correspond with the follow cases:
     * 0 if the player hasn't lost
     * 1 if the player has lost
     * 2 if the player won
     */
    public int HasLost(Player player,Board b){
        if(player.getBuilder1().possibleMoves(b).size()==0)
            player.getBuilder1().setDisabled(true);
        else
            player.getBuilder1().setDisabled(false);

        if(player.getBuilder2().possibleMoves(b).size()==0)
            player.getBuilder2().setDisabled(true);
        else
            player.getBuilder2().setDisabled(false);

        if(player.getBuilder1().getDisabled() && player.getBuilder2().getDisabled())
            return 1;
        return 0;
    }

}

