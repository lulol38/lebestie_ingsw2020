package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

/**
 * The client sends to the server the cell chosen from the user on the GUI for a move or a build action
 * @author Luca Ferrari
 */

public class SendCellChosen extends C2S{

    private int idGame;
    private int cx, cy;

    public SendCellChosen(int cx, int cy, int idGame){
        this.cx=cx;
        this.cy=cy;
        this.idGame=idGame;
    }

    //Getters
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
