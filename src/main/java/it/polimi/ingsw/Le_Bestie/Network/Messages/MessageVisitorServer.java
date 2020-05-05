package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;

/**
 * Visitor pattern
 * 1 method for each message that the server can receive
 * @author Luca Ferrari
 */

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
