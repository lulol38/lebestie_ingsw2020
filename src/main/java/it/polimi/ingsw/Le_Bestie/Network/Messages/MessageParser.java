package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Controller.GameController;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;
import it.polimi.ingsw.Le_Bestie.View.BoardController;
import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.*;
import it.polimi.ingsw.Le_Bestie.Network.Server.Server;
import it.polimi.ingsw.Le_Bestie.Network.Server.ClientHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Stage stage= new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("/fxml/NPlayersWindow.fxml"));
                    Scene scene = new Scene(root);

                    stage.setTitle("START GAME");
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void visit(SendNumPlayers mex) {
        Server.getInstance().getLobby().setNumPlayersMatch(mex.getNumPlayers());
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
        if(Server.getInstance().checkUsername(mex.getUsername())) {
            clientSender.setUsername(mex.getUsername());
            Server.getInstance().addWaitingClient(clientSender, clientSender.getSocket());
        }
        else
            clientSender.sendMessage(new ErrorUsername());
    }

    @Override
    public void visit(ErrorUsername mex) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Stage stage= new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("/fxml/ModifyUsername.fxml"));
                    Scene scene = new Scene(root);

                    stage.setTitle("Modify Username");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void visit(SendGameStart visitor) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    BoardController b = new BoardController();

                    Stage stage= new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("/fxml/Board.fxml"));

                    Scene scene = new Scene(root);

                    stage.setTitle("Board");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();

                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent we) {
                            if(Client.getInstance()!=null)
                                Client.getInstance().sendMessage(new CloseConnection());
                            System.exit(0);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void visit(SendBeginTurn visitor) {
        BoardController.getInstance().activeGUI();
        //DO MOVES
    }

    @Override
    public void visit(SendEndTurn visitor) {
        GameController.getInstance().nextTurn();
        //
        //Server goes to next player and sends begin turn to him
        //

    }

    public void visit(AskPositionBuilders visitor){
        BoardController.getInstance().BuilderPositions();
    }

    @Override
    public void visit(SendBuilderPositions visitor) {
        ClientHandler clientSender = ((ClientHandler) obj);
        String user = clientSender.getUsername();
        GameController.getInstance().setPlayerBuilders(visitor.getPos1x(), visitor.getPos1y(), visitor.getPos2x(), visitor.getPos2y());
    }
}
