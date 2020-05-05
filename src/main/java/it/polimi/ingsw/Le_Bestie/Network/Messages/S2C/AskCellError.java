package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;

/**
 * The server re-asks the client the builder to use for an action at the beginning of the turn
 * because the first cell he has selceted wasn't usable for the game rules
 * @author Luca Ferrari
 */

public class AskCellError extends S2C{

    /**
     * Visitor pattern
     * @param visitor
     */
    @Override
    public void receive(MessageVisitorClient visitor) {
        visitor.visit(this);
    }
}
