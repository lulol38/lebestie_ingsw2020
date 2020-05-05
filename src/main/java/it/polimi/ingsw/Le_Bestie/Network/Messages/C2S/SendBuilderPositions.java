package it.polimi.ingsw.Le_Bestie.Network.Messages.C2S;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;

/**
 * The client sends to the server the setup position of the builders before the game starts
 * (Two of this messages will be sent, once for each builder)
 * @author Luca Ferrari
 */

public class SendBuilderPositions extends C2S{

    private int idGame;

    private int posx, posy;

    public SendBuilderPositions(int posx, int posy, int idGame){
        this.posx=posx;
        this.posy=posy;
        this.idGame=idGame;
    }

    //Getters
    public int getIdGame() {
        return idGame;
    }
    public int getPosX() {
        return this.posx;
    }
    public int getPosY() {
        return this.posy;
    }

    /**
     * Visitor pattern
     * @param visitor
     */
    @Override
    public void receive(MessageVisitorServer visitor) {
        visitor.visit(this);
    }
}
