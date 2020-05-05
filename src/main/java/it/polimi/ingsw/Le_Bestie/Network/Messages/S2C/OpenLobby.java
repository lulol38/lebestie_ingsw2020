package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageClient;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageServer;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

/**
 * The server notifies the client that he is inserted in the lobby
 * @author Luca Ferrari
 */

public class OpenLobby extends S2C {

    /**
     * Visitor pattern
     * @param visitor
     */
    @Override
    public void receive(MessageVisitorClient visitor) {
        visitor.visit(this);
    }
}
