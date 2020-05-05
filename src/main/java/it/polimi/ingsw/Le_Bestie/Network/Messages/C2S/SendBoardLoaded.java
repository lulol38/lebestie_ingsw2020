package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

public class SendBoardLoaded extends C2S{

    private int numGame;

    public SendBoardLoaded(int numGame){
        this.numGame=numGame;
    }

    public int getNumGame() {
        return numGame;
    }

    @Override
    public void receive(MessageVisitorServer mex) {
        mex.visit(this);
    }
}
