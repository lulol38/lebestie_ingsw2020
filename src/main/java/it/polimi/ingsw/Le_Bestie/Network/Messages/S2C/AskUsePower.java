package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;

public class AskUsePower extends S2C{

    private String mex;

    public AskUsePower(String mex){
        this.mex=mex;
    }

    public String getMex() {
        return mex;
    }

    @Override
    public void receive(MessageVisitorClient mex) {
        mex.visit(this);
    }
}
