package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendCardToPlayers extends S2C{
    private String card;
    private String color;


    public SendCardToPlayers(String card,String color){
        this.card=card;
        this.color=color;
    }

    public String getCard() {
        return this.card;
    }
    public String getColor(){
        return this.color;
    }

    @Override
    public void receive(MessageVisitor visitor) {
        visitor.visit(this);
    }
}
