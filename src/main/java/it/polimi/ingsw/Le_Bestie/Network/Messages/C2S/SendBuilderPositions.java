package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendBuilderPositions extends C2S{

    private int pos1x, pos1y, pos2x, pos2y;

    public SendBuilderPositions(int pos1x, int pos1y, int pos2x, int pos2y){
        this.pos1x=pos1x;
        this.pos1y=pos1y;
        this.pos2x=pos2x;
        this.pos2y=pos2y;
    }

    public int getPos1x() {
        return pos1x;
    }

    public int getPos1y() {
        return pos1y;
    }

    public int getPos2x() {
        return pos2x;
    }

    public int getPos2y() {
        return pos2y;
    }

    @Override
    public void receive(MessageVisitor visitor) {
        visitor.visit(this);
    }
}
