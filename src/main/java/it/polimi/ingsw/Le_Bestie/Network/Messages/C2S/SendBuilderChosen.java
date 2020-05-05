package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

/**
 * The client sends to the server the builder that the user has selected on the GUI
 * for the moves
 * @author Luca Ferrari
 */

public class SendBuilderChosen extends C2S{

    private int idGame;
    private int bx, by, cx, cy;

    public SendBuilderChosen(int bx, int by, int idGame){
        this.bx=bx;
        this.by=by;
        this.idGame=idGame;
    }

    //Getters
    public int getIdGame() {
        return idGame;
    }
    public int getBx() {
        return bx;
    }
    public int getBy() {
        return by;
    }

    /**
     * Visitor pattern
     * @param visitor
     */
    @Override
    public void receive(MessageVisitorServer visitor) {
        visitor.visit(this);
    }
}
