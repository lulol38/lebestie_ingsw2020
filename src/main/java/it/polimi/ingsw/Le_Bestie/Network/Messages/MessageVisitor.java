package it.polimi.ingsw.Le_Bestie.Network.Messages;

public interface MessageVisitor {

    //1 metodo per ogni tipo di messaggio

    void visit(Message mex);
}
