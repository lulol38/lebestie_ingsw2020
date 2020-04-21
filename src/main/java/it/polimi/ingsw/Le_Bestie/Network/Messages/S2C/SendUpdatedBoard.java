package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendUpdatedBoard extends S2C{
    Board b;

    public SendUpdatedBoard(Board b){
        this.b=b;
    }

    public Board getB() {
        return b;
    }

    @Override
    public void receive(MessageVisitor mex) {
        mex.visit(this);
    }
}
