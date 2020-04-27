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

    private String Message;
    private boolean firstBuilt;
    private Builder notMovedBuilder;

    public Ares (String name,String path) {
        super(name,path);
        firstBuilt=false;
        notMovedBuilder=null;
        Message="Do tou want to remove an unoccupied block (not dome) neighboring your unmoved Worker?";
    }

    @Override
    public String getMessage() {
        return Message;
    }

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