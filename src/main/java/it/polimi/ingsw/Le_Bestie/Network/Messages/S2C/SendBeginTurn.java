package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;

/**
 * The server notifies the client that it's his turn
 * @author Luca Ferrari
 */

public class SendBeginTurn extends S2C{

    /**
     * Visitor pattern
     * @param visitor
     */
    @Override
    public void receive(MessageVisitorClient visitor) {
        visitor.visit(this);
    }
}
