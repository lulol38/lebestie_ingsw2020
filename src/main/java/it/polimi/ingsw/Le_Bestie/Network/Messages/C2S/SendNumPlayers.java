package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

/**
 * The first client connected to the match in the lobby sends to the server the numbers of the players
 * for the match
 * @author Luca Ferrari
 */

public class SendNumPlayers extends C2S{

    private int numPlayers;

    public SendNumPlayers(int numPlayers){
        this.numPlayers=numPlayers;
    }

    //Getter
    public int getNumPlayers() {
        return numPlayers;
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
