package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.Message;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class OpenLobby extends Message {
    public void receive(MessageVisitor visitor) {
        visitor.visit(this);
    }
}
