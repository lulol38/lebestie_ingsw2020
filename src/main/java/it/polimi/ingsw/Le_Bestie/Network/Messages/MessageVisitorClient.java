package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.*;

/**
 * Visitor pattern
 * 1 method for each message that the client can receive
 * @author Luca Ferrari
 */

public interface MessageVisitorClient {

    void visit(AskNumPlayers mex);

    void visit(AskUsername mex);

    void visit(ErrorUsername mex);

    void visit(SendGameStart mex);

    void visit(SendBeginTurn mex);

    void visit(SendEndTurn mex);

    void visit(AskPositionBuilders mex);

    void visit(AcceptedSetupBuilder mex);

    void visit(SendUpdatedBoard mex);

    void visit(SendOpponents mex);

    void visit(AskCell mex);

    void visit(SendPowerMessage mex);

    void visit(AskUsePower mex);

    void visit(AskBuilderChosen mex);

    void visit(AskCellError mex);

    void visit(SendCardToPlayers mex);

    void visit(SendHasLost mex);

    void visit(SendHasWon mex);

    void visit(LostForDisconnection mex);

    void visit(OpenLobby openLobby);
}
