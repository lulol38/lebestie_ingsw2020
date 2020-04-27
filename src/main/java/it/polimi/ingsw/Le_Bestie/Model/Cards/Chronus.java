package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Chronus
 * you also win when there are at least five
 * Complete Towers on the board
 * @author VeronicaRovelli
 */

public class Chronus extends GodCard{

    public Chronus(String name,String path)
    {
        super(name,path);
    }

    @Override
    public int build(Board b,Builder w, Cell c, boolean usePower){
        if(w.possibleBuilds(b).contains(c))
        {
            if(b.getRemainingPieces(c.getLevel()+1)==0)
                return 2;
            c.addLevel();
            if(c.getLevel()==4)
            {
                int cont=0;
                for (int x = 0; x < b.getROW(); x++) {
                    for (int y = 0; y < b.getCOL(); y++) {
                        if(b.getGrid()[x][y].isCompleteTower())
                            cont++;
                    }
                }
                if(cont>4)
                    return 5;
            }

            b.removePiece(c.getLevel());
            return 1;
        }
        return 0;
    }

    @Override
    public int HasLost(Player player, Board b) {

        int cont=0;
        for (int x = 0; x < b.getROW(); x++) {
            for (int y = 0; y < b.getCOL(); y++) {
                if(b.getGrid()[x][y].isCompleteTower())
                    cont++;
            }
        }
        if(cont>4)
            return 2;

        return super.HasLost(player,b);
    }

}
