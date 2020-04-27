package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Controller.GameController;
import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;
import it.polimi.ingsw.Le_Bestie.View.GUIController;
import it.polimi.ingsw.Le_Bestie.View.ViewController.BoardController;
import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.*;
import it.polimi.ingsw.Le_Bestie.Network.Server.Server;
import it.polimi.ingsw.Le_Bestie.Network.Server.ClientHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MessageParser implements MessageVisitor {

    private Object obj;

    public MessageParser(){}

    public MessageParser(Object obj){
        this.obj= obj;
    }

    //Ask to the client the number of players for the match
    @Override
    public void visit(AskNumPlayers mex){
        GUIController.getInstance().choiseNumber();
    }

    @Override
    public void visit(SendNumPlayers mex) {
        ClientHandler clientSender= (ClientHandler) obj;
        clientSender.getServer().getLobby().setNumPlayersMatch(mex.getNumPlayers());
        System.out.println("Setted match num players to: " + Server.getInstance().getLobby().getNumPlayersMatch());
    }

    @Override
    public void visit(CloseConnection mex) {
        ClientHandler clientSender = ((ClientHandler) obj);
        clientSender.closeConnection();
    }

    @Override
    public void visit(AskUsername mex) {
        Client c = ((Client) obj);
        c.sendMessage(new SendUsername(c.getUsername()));
    }

    @Override
    public void visit(SendUsername mex) {
        ClientHandler clientSender = ((ClientHandler) obj);
        if(clientSender.getServer().checkUsername(mex.getUsername())) {
            clientSender.setUsername(mex.getUsername());
            clientSender.getServer().addWaitingClient(clientSender, clientSender.getSocket());
        }
        else
            clientSender.sendMessage(new ErrorUsername());
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
    public void visit(SendBoardLoaded mex) {
        Server.getInstance().checkLoadingBoards(mex.getNumGame());
    }

    @Override
    public void visit(SendCardToPlayers mex) {
        BoardController.getInstance().addCardOnBoard(mex.getCard(),mex.getColor(),mex.getPath());
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
    public void visit(SendBuilderPositions mex) {
        ClientHandler clientSender = ((ClientHandler) obj);
        if(Server.getInstance().getGame(mex.getIdGame()).setPlayerBuilder(mex.getPosX(), mex.getPosY())==2){
            Board b=Server.getInstance().getGame(mex.getIdGame()).getMatchState().getBoard();
            for (ClientHandler c : Server.getInstance().getGame(mex.getIdGame()).getLobby().getClientsWaiting()) {
                c.sendMessage(new SendUpdatedBoard(b));
            }
            clientSender.sendMessage(new AcceptedSetupBuilder());
            Server.getInstance().getGame(mex.getIdGame()).nextTurn();
        }
        else{
            Board b=Server.getInstance().getGame(mex.getIdGame()).getMatchState().getBoard();
            for (ClientHandler c : Server.getInstance().getGame(mex.getIdGame()).getLobby().getClientsWaiting()) {
                c.sendMessage(new SendUpdatedBoard(b));
            }
            clientSender.sendMessage(new AskPositionBuilders());
        }
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
    public void visit(SendBuilderChosen mex) {
        Server.getInstance().getGame(mex.getIdGame()).checkBuilder(mex.getBx(), mex.getBy());
    }

    @Override
    public void visit(AskCell mex) {
        BoardController.getInstance().setClickBorder();
        BoardController.getInstance().AskCellChosen();
    }

    @Override
    public void visit(SendCellChosen mex) {
        Server.getInstance().getGame(mex.getIdGame()).requestAction(mex.getCx(), mex.getCy());
    }

    @Override
    public void visit(SendPowerMessage mex) {
        BoardController.getInstance().ShowPowerMessage(mex.getMessage());
    }

    @Override
    public void visit(SendPowerNotUsed mex) {
        ClientHandler c = (ClientHandler) obj;
        if(!Server.getInstance().getGame(mex.getIdGame()).getMatchState().getHasMoved()){
            Server.getInstance().getGame(mex.getIdGame()).getMatchState().setHasMoved(true);
            //Update clients
            for (ClientHandler client : Server.getInstance().getGame(mex.getIdGame()).getLobby().getClientsWaiting()) {
                client.sendMessage(new SendUpdatedBoard(Server.getInstance().getGame(mex.getIdGame()).getMatchState().getBoard()));
            }
            c.sendMessage(new AskCell());
        }
        else{
            Server.getInstance().getGame(mex.getIdGame()).getMatchState().setUsePower(true);
            Server.getInstance().getGame(mex.getIdGame()).requestAction(mex.getCx(), mex.getCy());
        }
    }

    @Override
    public void visit(AskUsePower mex) {
        BoardController.getInstance().ShowQuestionPower(mex.getMex());
    }

    @Override
    public void visit(SendCellWithPower mex) {
        Server.getInstance().getGame(mex.getIdGame()).getMatchState().setUsePower(mex.isPower());
        Server.getInstance().getGame(mex.getIdGame()).requestAction(mex.getCx(), mex.getCy());
        //Update clients
        for (ClientHandler client : Server.getInstance().getGame(mex.getIdGame()).getLobby().getClientsWaiting()) {
            client.sendMessage(new SendUpdatedBoard(Server.getInstance().getGame(mex.getIdGame()).getMatchState().getBoard()));
        }
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
