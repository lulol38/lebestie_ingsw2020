package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitor;

public class SendCardToPlayers extends S2C{
    private String card;
    private String color;
    private String path;

    public SendCardToPlayers(String card,String color,String path){
        this.card=card;
        this.color=color;
        this.path=path;
    }


    public String getCard() {
        return this.card;
    }
    public String getColor(){
        return this.color;
    }
    public String getPath(){
        return this.path;
    }


    @Override
    public void receive(MessageVisitor visitor) {
        visitor.visit(this);
    }
}
