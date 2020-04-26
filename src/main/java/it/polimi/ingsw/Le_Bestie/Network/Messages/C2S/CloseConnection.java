package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class CloseConnection extends C2S{

    private int idGame;

    public CloseConnection(int idGame){
        this.idGame=idGame;
    }

    public int getIdGame() {
        return idGame;
    }

    @Override
    public void receive(MessageVisitor visitor) {
        visitor.visit(this);
    }
}
