package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendEndTurn;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendNumPlayers;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendUsername;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.*;

public interface MessageVisitor {

    //1 metodo per ogni tipo di messaggio

    void visit(AskNumPlayers visitor);

    void visit(SendNumPlayers visitor);

    void visit(CloseConnection visitor);

    void visit(AskUsername visitor);

    void visit(SendUsername visitor);

    void visit(ErrorUsername visitor);

    void visit(SendGameStart visitor);

    void visit(SendBeginTurn visitor);

    void visit(SendEndTurn visitor);
}
