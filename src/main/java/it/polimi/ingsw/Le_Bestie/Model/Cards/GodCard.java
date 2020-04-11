package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class GodCard
 * describes a generic turn without power
 * @VeronicaRovelli
 */

public abstract class GodCard {

    private String name;

    public GodCard(String name){
        this.name=name;
    }
    public String getName() { return this.name; }

   /* return
    0->il builder non può muoversi nella cella richiesta
    1->il builder si è spostato nella cella desiderata(c)
    2->il player associato al builder ha vinto!!
    */

    public int move(Board b, Builder w, Cell c,boolean notmoveup){
         Cell currentCell=b.getGrid()[w.getPosition().getX()][w.getPosition().getX()];
         if(w.possibleMoves(b,notmoveup).contains(c))
         {
             //winner condition
             if(HasWon(c))
                  return 2;
             currentCell.setBuilder(null);
             c.setBuilder(w);
             w.setPosition(c.getPosition());
             return 1;
         }
         return 0;
    }

    public boolean build(Board b,Builder w, Cell c){
        if(w.possibleBuilds(b).contains(c))
        {
            c.addLevel();
            return true;
        }
        return false;
    }

    public boolean HasWon(Cell c) {
        if (c.getLevel() == 3)
            return true;
        return false;
    }

    //HasLost viene chiamato da matchstate prima la move e dopo la build
    public boolean HasLost(Player player,Board b,boolean notmoveup){
        if(player.getBuilder1().possibleMoves(b,notmoveup).size()==0)
            player.getBuilder1().setDisabled(true);
        if(player.getBuilder2().possibleMoves(b,notmoveup).size()==0)
            player.getBuilder2().setDisabled(true);
        if(player.getBuilder1().getDisabled()&&player.getBuilder2().getDisabled())
                return true;
        return false;
    }

}