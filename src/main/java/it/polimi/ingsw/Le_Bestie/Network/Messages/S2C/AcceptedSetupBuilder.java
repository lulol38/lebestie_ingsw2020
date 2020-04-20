package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class AcceptedSetupBuilder extends S2C{
    @Override
    public void receive(MessageVisitor visitor) {
        visitor.visit(this);
    }
}
