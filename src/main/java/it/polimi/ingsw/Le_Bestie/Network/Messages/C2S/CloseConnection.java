package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

/**
 * Message sent to the server from the client, to notify the closed connection
 * @author Luca Ferrari
 */

public class CloseConnection extends C2S{

    private int idGame;

    public CloseConnection(int idGame){
        this.idGame=idGame;
    }

    //Getter
    public int getIdGame() {
        return idGame;
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
