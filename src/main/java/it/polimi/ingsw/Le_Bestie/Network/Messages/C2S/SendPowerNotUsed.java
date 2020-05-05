package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

/**
 * The client sends to the server the cell chosen from the user on the GUI for a move or a build action
 * adding the confirm that the user doesn't want to use the power of the GodCard assigned to him
 * @author Luca Ferrari
 */

public class SendPowerNotUsed extends C2S{

    private int idGame;

    private int cx, cy;

    public SendPowerNotUsed(int cx, int cy, int idGame){
        this.cx=cx;
        this.cy=cy;
        this.idGame=idGame;
    }

    //Getter
    public int getIdGame() {
        return idGame;
    }
    public int getCx() {
        return cx;
    }
    public int getCy() {
        return cy;
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
