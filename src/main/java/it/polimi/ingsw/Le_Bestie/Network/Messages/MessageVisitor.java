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

    void visit(SendOpponents mex);

    void visit(SendBuilderChosen mex);

    void visit(AskCell mex);

    void visit(SendCellChosen mex);

    void visit(SendPowerMessage mex);

    void visit(SendPowerNotUsed mex);

    void visit(AskUsePower mex);

    void visit(SendCellWithPower mex);

    void visit(AskBuilderChosen mex);

    void visit(AskCellError mex);

    void visit(SendCardToPlayers mex);

    void visit(SendHasLost mex);

    void visit(SendHasWon mex);

    void visit(LostForDisconnection mex);

    void visit(OpenLobby openLobby);

    void visit(SendBoardLoaded mex);
}
