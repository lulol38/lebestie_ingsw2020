package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

/**
 * Class Hephaestus
 * your Builder may build one additional block(not dome)
 * on top of your first block
 * @author VeronicaRovelli
 */

public class Hephaestus extends GodCard{

    /**
     * If the player has already built once
     */
    boolean secondBuild;
    /**
     * A message to send to the client to decide if use Hephaestus' power
     */
    private String Message;

    public Hephaestus(String name,String path,String description) {
        super(name,path,description);
        secondBuild=false;
        Message="Do you want to build one additional block on top of your first block?";
    }

    @Override
    public String getMessage() {
        return Message;
    }

    /**
     * Method that overrides the build of GodCard.
     * The builder can build one additional block (not dome)
     * on top of his first block.
     * The build method is always called twice.
     */
    @Override
    public int build(Board b, Builder w, Cell c, boolean usePower) {

        if(!secondBuild)
        {
            int x=super.build(b, w, c, usePower);
            if(x==1)
            {
                if(c.getLevel()<3) {
                    secondBuild = true;
                    return 4;
                }
                return 1;
            }
            return x;
        }

        if(!usePower) {
            int x=super.build(b, w, c, usePower);
            if(x==1) {
                secondBuild=false;
            }
            return x;
        }
        else {
            secondBuild=false;
            return 1;
        }
    }
}

