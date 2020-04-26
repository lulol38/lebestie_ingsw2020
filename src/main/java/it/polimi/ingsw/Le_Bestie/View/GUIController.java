package it.polimi.ingsw.Le_Bestie.View;

import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import it.polimi.ingsw.Le_Bestie.View.ViewController.BoardController;
import it.polimi.ingsw.Le_Bestie.View.ViewController.ConnectionController;
import it.polimi.ingsw.Le_Bestie.View.ViewController.LobbyController;
import it.polimi.ingsw.Le_Bestie.View.ViewController.NPlayersController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GUIController {

    private static GUIController instance;
    private ConnectionController connectionController;
    private BoardController boardController;
    private LobbyController lobbyController;

    private ExecutorService executor = Executors.newCachedThreadPool();

    public static GUIController getInstance() {
        if (instance == null)
            instance = new GUIController();
        return instance;
    }

    public void setConnectionController(ConnectionController c){
        this.connectionController=c;
    }
    public void setBoardController(BoardController b){
        this.boardController=b;
    }
    public void setLobbyController(LobbyController l){
        this.lobbyController=l;
    }

    public static void setScene(Scene s, String resource){
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(GUIController.class.getResource(resource));
            s.setRoot(root);
        }catch (Exception e){
        }

    }

    public void createConnection(String address,int port,String user){
        try {
            Client c= new Client(address, port,user);
            if(c.init()) {
                executor.submit(c);

            }
            else {
                javafx.application.Platform.runLater(()->{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setContentText("The server is not running");
                    alert.showAndWait();
                });
            }
        }
        catch (Exception e){
        }
    }


    public void digitWrongUsername(){
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
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setBoard(){

        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {

                    Stage stage= new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("/fxml/BoardStage.fxml"));
                    Scene scene = new Scene(root);
                    stage.setTitle("SANTORINI");
                    stage.getIcons().add(new Image(GUI.class.getClassLoader().getResourceAsStream("images/icon.png")));
                    stage.initStyle(StageStyle.DECORATED);
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

    public void closeLobbyStage(){
        javafx.application.Platform.runLater(()-> {
            lobbyController.close();

        });
    }

    public void choiseNumber(){
        connectionController.okConnection();
    }

    public void displayDisconnection() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Stage stage = new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("/fxml/LostForConnectionStage.fxml"));
                    Scene scene = new Scene(root);
                    stage.setTitle("SANTORINI");
                    stage.getIcons().add(new Image(GUI.class.getClassLoader().getResourceAsStream("images/icon.png")));
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void closeBoard(){
        javafx.application.Platform.runLater(()-> {
            BoardController.getInstance().close();
        });
    }

    public void openLobbyWaiting(){
        javafx.application.Platform.runLater(()-> {
           connectionController.openLobby();
        });
    }
}
