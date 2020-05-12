package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Game.MatchState;

public class Poseidon extends GodCard {

    /**
     * A message to send to the client to decide if use Prometheus' power
     */
    private String Message;
    /**
     * The player's unmoved builder
     */
    private Builder notMovedBuilder;
    private int cont;

    public Poseidon(String name,String path,String description) {
        super(name,path,description);
        Message="Do you want to build another time with the not moved builder?";
        cont = 0;
        notMovedBuilder=null;
    }

    @Override
    public String getMessage() {
        return Message;
    }

    /**
     * Method that overrides the build of GodCard.
     * At the end of his turn (after the usual build),
     * the player can build up to 3 times with the not moved builder
     */
    @Override
    public  int build(Board b, Builder w, Cell c, boolean usePower) {

        if(cont==0) {
            if (w.getPlayer().getBuilder1() == w)
                notMovedBuilder = w.getPlayer().getBuilder2();
            else
                notMovedBuilder = w.getPlayer().getBuilder1();
        }

        if(cont==0) {
            int res = super.build(b, w, c, usePower);
            if(res==1){
                cont++;
                if(b.getGrid()[notMovedBuilder.getPosition().getX()][notMovedBuilder.getPosition().getY()].getLevel()==0)
                    return 3;
            }
            return res;
        }
        else {
            if (b.getGrid()[notMovedBuilder.getPosition().getX()][notMovedBuilder.getPosition().getY()].getLevel() == 0 && notMovedBuilder.possibleBuilds(b).contains(c) && !usePower) {
                int res = super.build(b, notMovedBuilder, c, usePower);
                if (res == 1) {
                    cont++;
                    if (cont == 4 || notMovedBuilder.possibleBuilds(b).size() == 0) {
                        cont = 0;
                        notMovedBuilder = null;
                        return 1;
                    }

                    return 3;
                } else
                    return res;
            } else {
                if (usePower) {
                    cont = 0;
                    notMovedBuilder = null;
                    return 1;
                }
                return 0;
            }
        }

    }

}
