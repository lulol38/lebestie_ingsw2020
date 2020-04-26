package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendCellWithPower extends C2S{

    private int idGame;

    private int cx, cy;
    private boolean power;

    public SendCellWithPower(int cx, int cy, boolean power, int idGame){
        this.cx=cx;
        this.cy=cy;
        this.power=power;
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

    public boolean isPower() {
        return power;
    }

    @Override
    public void receive(MessageVisitor mex) {
        mex.visit(this);
    }
}
