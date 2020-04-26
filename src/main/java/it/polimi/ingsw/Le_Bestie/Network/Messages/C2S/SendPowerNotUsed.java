package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendPowerNotUsed extends C2S{

    private int idGame;

    private int cx, cy;

    public SendPowerNotUsed(int cx, int cy, int idGame){
        this.cx=cx;
        this.cy=cy;
        this.idGame=idGame;
    }

    public int getIdGame() {
        return idGame;
    }

    public int getCx() {
        return cx;
    }

    public int getCy() {
        return cy;
    }

    @Override
    public void receive(MessageVisitor mex) {
        mex.visit(this);
    }
}
