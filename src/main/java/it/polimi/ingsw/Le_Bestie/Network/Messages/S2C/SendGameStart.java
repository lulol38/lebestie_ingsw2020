package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendGameStart extends S2C{

    int numGame;

    public SendGameStart(int numGame){
        this.numGame=numGame;
    }

    public int getNumGame() {
        return numGame;
    }

    @Override
    public void receive(MessageVisitor visitor) {
        visitor.visit(this);
    }
}
