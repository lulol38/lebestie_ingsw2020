package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;
import it.polimi.ingsw.Le_Bestie.View.GUIController;
import it.polimi.ingsw.Le_Bestie.View.ViewController.BoardController;
import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.*;
import it.polimi.ingsw.Le_Bestie.Network.Server.Server;
import it.polimi.ingsw.Le_Bestie.Network.Server.ClientHandler;

public class MessageParserClient implements MessageVisitorClient {

    private Object obj;

    public MessageParserClient(Object obj){
        this.obj= obj;
    }

    //Ask to the client the number of players for the match
    @Override
    public void visit(AskNumPlayers mex){
        GUIController.getInstance().choiseNumber();
    }

    @Override
    public void visit(AskUsername mex) {
        Client c = ((Client) obj);
        c.sendMessage(new SendUsername(c.getUsername()));
    }

    @Override
    public void visit(ErrorUsername mex) {
        GUIController.getInstance().digitWrongUsername();
    }

    @Override
    public void visit(SendGameStart mex) {
        Client c = (Client) obj;
        c.setIdGame(mex.getNumGame());
        GUIController.getInstance().setBoard();
        GUIController.getInstance().closeLobbyStage();
    }

    @Override
    public void visit(SendCardToPlayers mex) {
        BoardController.getInstance().addCardOnBoard(mex.getCard(),mex.getColor(),mex.getPath(),mex.getDescription());
    }

    @Override
    public void visit(SendOpponents mex) {
        BoardController.getInstance().addOpponentsOnBoard(mex.getOpponents(),mex.getOpponentsGods());
    }

    @Override
    public void visit(SendBeginTurn mex) {
        BoardController.getInstance().beginTurn();
    }

    @Override
    public void visit(SendEndTurn mex) {
        BoardController.getInstance().endTurn();
    }

    public void visit(AskPositionBuilders mex){
        BoardController.getInstance().BuilderPositions();
    }

    @Override
    public void visit(AcceptedSetupBuilder mex) {
        Client client = (Client) obj;
        BoardController.getInstance().setBuildersSetted(true);
    }

    @Override
    public void visit(SendUpdatedBoard mex) {
        BoardController.getInstance().setupBoard(mex.getB());
    }

    @Override
    public void visit(AskCell mex) {
        BoardController.getInstance().setClickBorder();
        BoardController.getInstance().AskCellChosen();
    }

    @Override
    public void visit(SendPowerMessage mex) {
        BoardController.getInstance().ShowPowerMessage(mex.getMessage());
    }

    @Override
    public void visit(AskUsePower mex) {
        BoardController.getInstance().ShowQuestionPower(mex.getMex());
    }

    @Override
    public void visit(AskBuilderChosen mex) {
        BoardController.getInstance().AskBuilderChosen();
    }

    @Override
    public void visit(LostForDisconnection mex) {
        GUIController.getInstance().displayDisconnection();
        GUIController.getInstance().closeBoard();
    }

    @Override
    public void visit(AskCellError mex) {
        BoardController.getInstance().AskCellError("The pieces aren't available");
    }

    @Override
    public void visit(SendHasLost mex) {
        GUIController.getInstance().displayLost();
        GUIController.getInstance().closeBoard();
    }

    @Override
    public void visit(SendHasWon mex) {
        GUIController.getInstance().displayWin();
        GUIController.getInstance().closeBoard();
    }

    @Override
    public void visit(OpenLobby mex) {
        GUIController.getInstance().openLobbyWaiting();
    }
}
