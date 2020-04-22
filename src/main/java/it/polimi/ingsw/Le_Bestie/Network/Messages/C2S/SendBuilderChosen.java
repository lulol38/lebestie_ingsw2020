package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendBuilderChosen extends C2S{

    private int bx, by, cx, cy;

    public SendBuilderChosen(int bx, int by){
        this.bx=bx;
        this.by=by;
    }

    public int getBx() {
        return bx;
    }

    public int getBy() {
        return by;
    }

    @Override
    public void receive(MessageVisitor mex) {
        mex.visit(this);
    }
}
