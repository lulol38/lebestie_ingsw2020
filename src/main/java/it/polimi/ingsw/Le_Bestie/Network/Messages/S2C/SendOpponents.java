package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;

import java.util.ArrayList;

/**
 * The server sends to the client his opponents in the match, and their GodCards
 * @author Luca Ferrari
 */

public class SendOpponents extends S2C {

    private ArrayList<String>opponents;
    private ArrayList<String> opponentsGods;

    public SendOpponents(ArrayList<String>opponents,ArrayList<String>opponentsGods){
        this.opponentsGods=opponentsGods;
        this.opponents=opponents;
    }

    //Getter
    public ArrayList<String> getOpponents() {
        return opponents;
    }
    public ArrayList<String> getOpponentsGods() {
        return opponentsGods;
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
