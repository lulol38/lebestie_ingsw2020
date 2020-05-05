package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;

/**
 * The server asks the client if he wants to use the power of his GodCard
 * @author Luca Ferrari
 */

public class AskUsePower extends S2C{

    private String mex;

    public AskUsePower(String mex){
        this.mex=mex;
    }

    //Getter
    public String getMex() {
        return mex;
    }

    /**
     * Visitor pattern
     * @param visitor
     */
    @Override
    public void receive(MessageVisitorClient visitor) {
        visitor.visit(this);
    }
}
