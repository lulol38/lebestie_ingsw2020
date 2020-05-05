package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;

/**
 * The server asks the client if he wants to use the power of his GodCard
 * @author Luca Ferrari
 */

public class SendPowerMessage extends S2C{

    private String message;

    public SendPowerMessage(String message){
        this.message=message;
    }

    //Getter
    public String getMessage() {
        return message;
    }

    /**
     * Visitor pattern
     * @param visitor
     */
    @Override
    public void receive(MessageVisitorClient visitor) {
        visitor.visit(this);
    }
}
