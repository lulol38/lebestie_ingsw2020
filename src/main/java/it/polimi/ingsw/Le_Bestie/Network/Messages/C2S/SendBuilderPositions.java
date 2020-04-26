package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendBuilderPositions extends C2S{

    private int idGame;

    private int posx, posy;

    public SendBuilderPositions(int posx, int posy, int idGame){
        this.posx=posx;
        this.posy=posy;
        this.idGame=idGame;
    }

    public int getIdGame() {
        return idGame;
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
