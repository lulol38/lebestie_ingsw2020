package it.polimi.ingsw.Le_Bestie.Network.Messages;

public class MessageParser implements MessageVisitor {

    private Object obj;

    public MessageParser(Object obj){
        this.obj= obj;
    }

    @Override
    public void visit(Message mex) {
        //fai cose in base ai messaggi
    }
}
