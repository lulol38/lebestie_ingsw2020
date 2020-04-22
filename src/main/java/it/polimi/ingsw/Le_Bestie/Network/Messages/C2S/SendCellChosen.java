package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendCellChosen extends C2S{
    private int cx, cy;

    public SendCellChosen(int cx, int cy){
        this.cx=cx;
        this.cy=cy;
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
