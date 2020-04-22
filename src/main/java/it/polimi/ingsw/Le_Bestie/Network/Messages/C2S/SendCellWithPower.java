package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendCellWithPower extends C2S{

    private int cx, cy;
    private boolean power;

    public SendCellWithPower(int cx, int cy, boolean power){
        this.cx=cx;
        this.cy=cy;
        this.power=power;
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
