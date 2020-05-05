package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;

public interface MessageVisitorServer {

    void visit(SendNumPlayers mex);

    void visit(CloseConnection mex);

    void visit(SendUsername mex);

    void visit(SendBuilderPositions mex);

    void visit(SendBuilderChosen mex);

    void visit(SendCellChosen mex);

    void visit(SendPowerNotUsed mex);

    void visit(SendCellWithPower mex);

    void visit(SendBoardLoaded mex);
}
