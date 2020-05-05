package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageClient;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageServer;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

public class OpenLobby extends S2C {
    public void receive(MessageVisitorClient visitor) {
        visitor.visit(this);
    }
}
