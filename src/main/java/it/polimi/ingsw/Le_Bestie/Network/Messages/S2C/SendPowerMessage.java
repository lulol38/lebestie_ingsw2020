package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendPowerMessage extends S2C{
    private String message;

    public String getMessage() {
        return message;
    }

    public SendPowerMessage(String message){
        this.message=message;
    }

    @Override
    public void receive(MessageVisitor mex) {
        mex.visit(this);
    }
}
