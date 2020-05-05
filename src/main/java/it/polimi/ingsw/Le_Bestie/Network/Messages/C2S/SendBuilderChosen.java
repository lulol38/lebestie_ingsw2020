package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

public class SendBuilderChosen extends C2S{

    private int idGame;

    private int bx, by, cx, cy;

    public SendBuilderChosen(int bx, int by, int idGame){
        this.bx=bx;
        this.by=by;
        this.idGame=idGame;
    }

    public int getIdGame() {
        return idGame;
    }

    public int getBx() {
        return bx;
    }

    public int getBy() {
        return by;
    }

    @Override
    public void receive(MessageVisitorServer mex) {
        mex.visit(this);
    }
}
