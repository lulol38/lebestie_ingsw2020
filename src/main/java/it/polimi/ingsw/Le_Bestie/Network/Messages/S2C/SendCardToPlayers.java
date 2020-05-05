package it.polimi.ingsw.Le_Bestie.Network.Messages.S2C;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorClient;

public class SendCardToPlayers extends S2C{
    private String card;
    private String color;
    private String path;
    private String description;

    public SendCardToPlayers(String card,String color,String path,String description){
        this.card=card;
        this.color=color;
        this.path=path;
        this.description=description;
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
    public String getDescription() {
        return description;
    }

    @Override
    public void receive(MessageVisitorClient visitor) {
        visitor.visit(this);
    }
}
