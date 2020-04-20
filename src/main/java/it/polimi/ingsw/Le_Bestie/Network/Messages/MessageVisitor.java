package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;
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

    void visit(AskPositionBuilders visitor);

    void visit(SendBuilderPositions visitor);

    void visit(AcceptedSetupBuilder visitor);
}
