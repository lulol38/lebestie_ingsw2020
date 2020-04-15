package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendNumPlayers extends C2S{

    private int numPlayers;

    public SendNumPlayers(int numPlayers){
        this.numPlayers=numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    @Override
    public void receive(MessageVisitor visitor) {
        visitor.visit(this);
    }
}
