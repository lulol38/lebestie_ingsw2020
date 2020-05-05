package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;

/**
 * The server updates the clients of the changes on the board, after every action
 * @author Luca Ferrari
 */

public class SendUpdatedBoard extends S2C{

    Board b;

    public SendUpdatedBoard(Board b){
        this.b=b;
    }

    //Getter
    public Board getB() {
        return b;
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
