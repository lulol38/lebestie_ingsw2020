package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendBuilderPositions extends C2S{

    private int posx, posy;

    public SendBuilderPositions(int posx, int posy){
        this.posx=posx;
        this.posy=posy;
    }

    public int getPosX() {
        return this.posx;
    }

    public int getPosY() {
        return this.posy;
    }

    @Override
    public void receive(MessageVisitor visitor) {
        visitor.visit(this);
    }
}
