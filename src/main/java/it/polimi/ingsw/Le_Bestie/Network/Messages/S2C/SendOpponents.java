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
    private ArrayList<String>opponentsColors;

    public SendOpponents(ArrayList<String>opponents,ArrayList<String>opponentsGods,ArrayList<String>opponentsColors){
        this.opponentsGods=opponentsGods;
        this.opponents=opponents;
        this.opponentsColors=opponentsColors;
    }

    //Getter
    public ArrayList<String> getOpponents() {
        return opponents;
    }
    public ArrayList<String> getOpponentsGods() {
        return opponentsGods;
    }
    public ArrayList<String> getOpponentsColors() {
        return opponentsColors;
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
