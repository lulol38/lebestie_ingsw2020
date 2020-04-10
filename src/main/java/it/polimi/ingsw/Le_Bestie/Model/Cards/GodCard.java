package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Controller.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;


public abstract class GodCard {

    private String name;

    public GodCard(String name){
        this.name=name;
    }

    public boolean move(Builder w, Cell c){
        if(!MatchState.getHasMoved()&&w.possibleMoves().contains(c))
        {
            //winner condition
            if(c.getLevel()==3)
                HasWon();

            //free the previous builder's cell
            w.getCell().setBuilder(null);
            w.getCell().setOccupied(false);

            //new cell
            w.setCell(c);
            c.setBuilder(w);
            c.setOccupied(true);

            MatchState.setHasMoved(true);
            return true;
        }
        return false;
    }

    public boolean build(Builder w, Cell c){
        if(MatchState.getHasMoved()&&w.possibleBuilds().contains(c))
        {
            //buinding piece is available?
            if(MatchState.getRemainingPieces(c.getLevel()+1)>0)
            {
                c.addLevel();



                 //checkPieces(c.getLevel());
                //is the builder locked, after his build?
                if(w.possibleMoves().size()==0)
                    w.setDisabled(true);
            }
            HasLost();
            return false;
        }
        HasLost();
        return false;
    }

    public boolean HasWon(){return false;}

    public boolean HasLost(){return false;}

}