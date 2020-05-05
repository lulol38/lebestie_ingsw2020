package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;

public class AskPositionBuilders extends S2C{
    @Override
    public void receive(MessageVisitorClient visitor) {
        visitor.visit(this);
    }
}
