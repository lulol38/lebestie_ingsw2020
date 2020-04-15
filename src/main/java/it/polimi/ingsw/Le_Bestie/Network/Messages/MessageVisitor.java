package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendNumPlayers;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.AskNumPlayers;

public interface MessageVisitor {

    //1 metodo per ogni tipo di messaggio

    void visit(AskNumPlayers mex);

    void visit(SendNumPlayers mex);

    void visit(CloseConnection mex);
}
