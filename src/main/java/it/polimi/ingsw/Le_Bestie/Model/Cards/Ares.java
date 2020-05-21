package it.polimi.ingsw.Le_Bestie.Model.Cards;


import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;

/**
 * Class Ares
 * you may remove an unoccupied block (not dome)
 * neighboring your unmoved Worker
 * @author VeronicaRovelli
 */

public class Ares extends GodCard{

    /**
     * A message to send to the client to decide if use Ares' power
     */
    private String Message;
    /**
     * If the player has already built
     */
    private boolean firstBuilt;
    /**
     * The player's unmoved builder
     */
    private Builder notMovedBuilder;


    public Ares (String name,String path,String description) {
        super(name,path,description);
        firstBuilt=false;
        notMovedBuilder=null;
        Message="Do you want to remove an unoccupied block\n(not dome) neighboring your unmoved Worker?";
    }

    @Override
    public String getMessage() {
        return Message;
    }


    /**
     * Method that overrides the build of GodCard.
     * At the end of his turn (after the usual build),
     * the player can remove an unoccupied block (not dome)
     * in neighboring his unmoved builder.
     * The build method is always called twice.
     */
    @Override
    public int build(Board b, Builder w, Cell c, boolean usePower){

        //first built
        if(!firstBuilt)
        {
            int x=super.build(b,w,c,usePower);
            if(x==1)
            {
                if(w.getPlayer().getBuilder1()==w)
                    notMovedBuilder=w.getPlayer().getBuilder2();
                else
                    notMovedBuilder=w.getPlayer().getBuilder1();

                if(notMovedBuilder.possibleBuilds(b).size()==0)
                {
                    notMovedBuilder=null;
                    return 1;
                }
                for(int i=0;i<notMovedBuilder.possibleBuilds(b).size();i++) {
                    if (notMovedBuilder.possibleBuilds(b).get(i).getLevel() != 0) {
                        firstBuilt = true;
                        return 3;
                    }
                }
                notMovedBuilder=null;
                return 1;
            }
            return x;
        }
        //second built
        else {
            if (!usePower) {
                if(c.getLevel()==0||!notMovedBuilder.possibleBuilds(b).contains(c))
                    return 0;

                c.setLevel(c.getLevel()-1);
                firstBuilt=false;
                notMovedBuilder=null;
                return 1;
            }

            else
            {
                notMovedBuilder=null;
                firstBuilt = false;
                return 1;
            }

        }
    }
}
