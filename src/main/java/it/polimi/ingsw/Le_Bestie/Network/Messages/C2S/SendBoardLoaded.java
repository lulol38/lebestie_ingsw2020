package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendBoardLoaded extends C2S{

    private int numGame;

    public SendBoardLoaded(int numGame){
        this.numGame=numGame;
    }

    public int getNumGame() {
        return numGame;
    }

    @Override
    public void receive(MessageVisitor mex) {
        mex.visit(this);
    }
}
