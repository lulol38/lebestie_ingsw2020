package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;

/**
 * The server notifies the client that the game is starting
 * @author Luca Ferrari
 */

public class SendGameStart extends S2C{

    int numGame;

    public SendGameStart(int numGame){
        this.numGame=numGame;
    }

    //Getter
    public int getNumGame() {
        return numGame;
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
