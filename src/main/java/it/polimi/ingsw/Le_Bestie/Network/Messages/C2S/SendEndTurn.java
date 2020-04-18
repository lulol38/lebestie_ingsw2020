package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendEndTurn extends C2S{
    @Override
    public void receive(MessageVisitor visitor) {
        visitor.visit(this);
    }
}
