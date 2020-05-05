package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

/**
 * The client sends to the server his username
 * @author Luca Ferrari
 */

public class SendUsername extends C2S{

    private String username;

    public SendUsername(String username){
        this.username=username;
    }

    //Getter
    public String getUsername() {
        return username;
    }

    /**
     * Visitor pattern
     * @param visitor
     */
    @Override
    public void receive(MessageVisitorServer visitor) {
        visitor.visit(this);
    }
}
