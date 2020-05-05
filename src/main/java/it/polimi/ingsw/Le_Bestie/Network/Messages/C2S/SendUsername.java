package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

public class SendUsername extends C2S{

    private String username;

    public SendUsername(String username){
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void receive(MessageVisitorServer visitor) {
        visitor.visit(this);
    }
}
