package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

public class CloseConnection extends C2S{

    private int idGame;

    public CloseConnection(int idGame){
        this.idGame=idGame;
    }

    public int getIdGame() {
        return idGame;
    }

    @Override
    public void receive(MessageVisitorServer visitor) {
        visitor.visit(this);
    }
}
