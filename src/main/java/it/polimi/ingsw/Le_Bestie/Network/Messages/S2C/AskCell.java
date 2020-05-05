package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;

/**
 * The server asks the client the cell to use for an action after he has selected a builder
 * @author Luca Ferrari
 */

public class AskCell extends S2C{

    /**
     * Visitor pattern
     * @param visitor
     */
    @Override
    public void receive(MessageVisitorClient visitor) {
        visitor.visit(this);
    }
}
