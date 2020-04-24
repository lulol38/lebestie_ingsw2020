package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendCardToPlayers extends S2C{
    private String card;

    public SendCardToPlayers(String card){
        this.card=card;
    }

    public String getCard(){
        return  this.card;
    }

    @Override
    public void receive(MessageVisitor visitor) {
        visitor.visit(this);
    }
}
