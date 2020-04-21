package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.*;

public interface MessageVisitor {

    //1 metodo per ogni tipo di messaggio

    void visit(AskNumPlayers mex);

    void visit(SendNumPlayers mex);

    void visit(CloseConnection mex);

    void visit(AskUsername mex);

    void visit(SendUsername mex);

    void visit(ErrorUsername mex);

    void visit(SendGameStart mex);

    void visit(SendBeginTurn mex);

    void visit(SendEndTurn mex);

    void visit(AskPositionBuilders mex);

    void visit(SendBuilderPositions mex);

    void visit(AcceptedSetupBuilder mex);

    void visit(SendUpdatedBoard mex);
}
