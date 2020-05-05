package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

public class SendCellChosen extends C2S{

    private int idGame;

    private int cx, cy;

    public SendCellChosen(int cx, int cy, int idGame){
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
    public void receive(MessageVisitorServer mex) {
        mex.visit(this);
    }
}
