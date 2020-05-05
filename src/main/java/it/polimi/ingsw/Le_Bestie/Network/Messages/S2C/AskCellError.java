package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;

public class AskCellError extends S2C{
    @Override
    public void receive(MessageVisitorClient mex) {
        mex.visit(this);
    }
}
