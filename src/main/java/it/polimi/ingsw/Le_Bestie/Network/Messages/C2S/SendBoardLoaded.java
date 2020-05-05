package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

/**
 * Message sent to the server from the client to notify that the board (GUI)
 * has succesfully loaded on the screen and it's ready to accept commands
 * @author Luca Ferrari
 */

public class SendBoardLoaded extends C2S{

    private int numGame;

    public SendBoardLoaded(int numGame){
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
    public void receive(MessageVisitorServer visitor) {
        visitor.visit(this);
    }
}
