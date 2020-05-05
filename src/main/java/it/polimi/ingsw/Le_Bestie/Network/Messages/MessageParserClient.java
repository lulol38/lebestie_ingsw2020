package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;
import it.polimi.ingsw.Le_Bestie.View.GUIController;
import it.polimi.ingsw.Le_Bestie.View.ViewController.BoardController;
import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.*;

/**
 * Visitor pattern
 * For each received message, here are described the actions that the client has to do
 * @author Luca Ferrari
 */

public class MessageParserClient implements MessageVisitorClient {

    private Object obj;

    public MessageParserClient(Object obj){
        this.obj= obj;
    }

    /**
     * Ask the client the number of players for the match
     */
    @Override
    public void visit(AskNumPlayers mex){
        GUIController.getInstance().choiseNumber();
    }

    /**
     * Take the username of the client and send it back to the server
     * @param mex
     */
    @Override
    public void visit(AskUsername mex) {
        Client c = ((Client) obj);
        c.sendMessage(new SendUsername(c.getUsername()));
    }

    /**
     * Ask a new username
     * @param mex
     */
    @Override
    public void visit(ErrorUsername mex) {
        GUIController.getInstance().digitWrongUsername();
    }

    /**
     * Set the unique idGame of the match and open the Board GUI
     * @param mex
     */
    @Override
    public void visit(SendGameStart mex) {
        Client c = (Client) obj;
        c.setIdGame(mex.getNumGame());
        GUIController.getInstance().setBoard();
        GUIController.getInstance().closeLobbyStage();
    }

    /**
     * Show the GodCard of the player
     * @param mex
     */
    @Override
    public void visit(SendCardToPlayers mex) {
        BoardController.getInstance().addCardOnBoard(mex.getCard(),mex.getColor(),mex.getPath(),mex.getDescription());
    }

    /**
     * Show the opponents players
     * @param mex
     */
    @Override
    public void visit(SendOpponents mex) {
        BoardController.getInstance().addOpponentsOnBoard(mex.getOpponents(),mex.getOpponentsGods());
    }

    /**
     * Handle the beginning turn on the GUI
     * @param mex
     */
    @Override
    public void visit(SendBeginTurn mex) {
        BoardController.getInstance().beginTurn();
    }

    /**
     * Handle the end of the turn on the GUI
     * @param mex
     */
    @Override
    public void visit(SendEndTurn mex) {
        BoardController.getInstance().endTurn();
    }

    /**
     * Ask the setup position of the builders before the game starts
     * @param mex
     */
    public void visit(AskPositionBuilders mex){
        BoardController.getInstance().BuilderPositions();
    }

    /**
     * Builders are setted on the board
     * @param mex
     */
    @Override
    public void visit(AcceptedSetupBuilder mex) {
        Client client = (Client) obj;
        BoardController.getInstance().setBuildersSetted(true);
    }

    /**
     * Update the board with new changes
     * @param mex
     */
    @Override
    public void visit(SendUpdatedBoard mex) {
        BoardController.getInstance().setupBoard(mex.getB());
    }

    /**
     * Ask the cell for an action
     * @param mex
     */
    @Override
    public void visit(AskCell mex) {
        BoardController.getInstance().setClickBorder();
        BoardController.getInstance().AskCellChosen();
    }

    /**
     * Ask if player wants to use power
     * @param mex
     */
    @Override
    public void visit(SendPowerMessage mex) {
        BoardController.getInstance().ShowPowerMessage(mex.getMessage());
    }

    /**
     * Ask if player wants to use power
     * @param mex
     */
    @Override
    public void visit(AskUsePower mex) {
        BoardController.getInstance().ShowQuestionPower(mex.getMex());
    }

    /**
     * Ask a builder for an action
     * @param mex
     */
    @Override
    public void visit(AskBuilderChosen mex) {
        BoardController.getInstance().AskBuilderChosen();
    }

    /**
     * Handle if player loses for disconnection
     * @param mex
     */
    @Override
    public void visit(LostForDisconnection mex) {
        GUIController.getInstance().displayDisconnection();
        GUIController.getInstance().closeBoard();
    }

    /**
     * Re-ask a cell because on the previous the action can't be done
     * @param mex
     */
    @Override
    public void visit(AskCellError mex) {
        BoardController.getInstance().AskCellError("The pieces aren't available");
    }

    /**
     * Handle if a player loses
     * @param mex
     */
    @Override
    public void visit(SendHasLost mex) {
        GUIController.getInstance().displayLost();
        GUIController.getInstance().closeBoard();
    }

    /**
     * Handle if a player wins
     * @param mex
     */
    @Override
    public void visit(SendHasWon mex) {
        GUIController.getInstance().displayWin();
        GUIController.getInstance().closeBoard();
    }

    /**
     * Opens waiting lobby GUI
     * @param mex
     */
    @Override
    public void visit(OpenLobby mex) {
        GUIController.getInstance().openLobbyWaiting();
    }
}
