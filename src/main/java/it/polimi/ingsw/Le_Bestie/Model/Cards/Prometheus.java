package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Prometheus
 * if your Builder does not move up,
 * it may build both before and after moving
 * @author VeronicaRovelli
 */

public class Prometheus extends GodCard{

    private boolean sendMessage;
    private boolean firstBuild;
    private String Message;

    public Prometheus(String name,String path,String description) {
        super(name,path,description);
        firstBuild=false;
        sendMessage=false;
        Message="Do you want to build on this cell before moving? (during the move you can't move up!!)";
    }

    @Override
    public String getMessage() {
        return Message;
    }

    @Override
    public int move(Board b,Builder w, Cell c, boolean usePower) {

        if(!sendMessage) {
            sendMessage=true;
            return 4;
        }

        //if client not use power
        if(usePower) {
            int x= super.move(b, w, c, usePower);
            if(x==1)
                sendMessage=false;
            return x;
        }
        //if client use power
        else
        {
            if(!firstBuild)
            {
                int x=build(b, w, c, usePower);
                if(x==1) {
                    firstBuild = true;
                    if(w.possibleMoves(b, true).size()==0)
                        w.setDisabled(true);
                    return 0;
                }
                return x;
            }
            else
            {
                Cell currentCell=b.getGrid()[w.getPosition().getX()][w.getPosition().getY()];
                if(c.getLevel()-currentCell.getLevel()<1) {

                    int x=super.move(b, w, c, usePower);
                    if(x==1)
                    {
                        firstBuild=false;
                        sendMessage=false;
                    }
                    return x;
                }
                else
                    return 0;
            }
        }
    }
}

