package it.polimi.ingsw.Le_Bestie.View;

import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendBoardLoaded;
import it.polimi.ingsw.Le_Bestie.View.ViewController.BoardController;
import it.polimi.ingsw.Le_Bestie.View.ViewController.ConnectionController;
import it.polimi.ingsw.Le_Bestie.View.ViewController.LobbyController;
import it.polimi.ingsw.Le_Bestie.View.ViewController.NPlayersController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *This class is used for the interaction between GUI and the message parser of the client.
 *@author Davide Carini
 */

public class GUIController {

    private static GUIController instance;
    private ConnectionController connectionController;
    private BoardController boardController;
    private LobbyController lobbyController;
    private NPlayersController nPlayersController;
    private ExecutorService executor = Executors.newCachedThreadPool();

    //GETTERS

    //SETTERS
    public void setConnectionController(ConnectionController c){
        this.connectionController=c;
    }
    public void setBoardController(BoardController b){
        this.boardController=b;
    }
    public void setLobbyController(LobbyController l){
        this.lobbyController=l;
    }
    public void setNPlayersController(NPlayersController n){this.nPlayersController=n;}

    /**
     * The singleton instance of the GUi controller returns, if it has not been created it allocates it as well
     * @return the singleton instance
     */
    public static GUIController getInstance() {
        if (instance == null)
            instance = new GUIController();
        return instance;
    }

    /**
     * This method is used to set scene of the current stage
     * @param s is the scene to load
     * @param resource is the path of the scene to load (.fxml file)
     */
    public static void setScene(Scene s, String resource){
        try {

            AnchorPane root = FXMLLoader.load(GUIController.class.getResource(resource));
            if (s != null) {
                s.setRoot(root);
                s.getWindow().setOnCloseRequest(we -> {
                    if (Client.getInstance() != null) {
                        Client.getInstance().sendMessage(new CloseConnection(Client.getInstance().getIdGame()));
                        try {
                            Client.getInstance().closeConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    System.exit(0);
                });}
            }catch(Exception e){
                e.printStackTrace();

        }
    }

    /**
     * This method creates new client connection if the server is running(in this case is open an alert that display the error).
     * @param address of the server that the client want to connect
     * @param port of the server that the client want to connect
     * @param user is the nickname that represents the user in the game
     */
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
                    System.out.println("The server is not running");
                });
                Client.setInstance(null);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method is called to display an alert that allows the user to modify the nickname chosen.
     */
    public void digitWrongUsername(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    connectionController.disableButton();
                    Stage stage= new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("/fxml/ModifyUsername.fxml"));
                    Scene scene = new Scene(root);
                    stage.setTitle("Modify Username");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.DECORATED);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This method is called to set a new stage that represents the board
     */
    public void setBoard(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    closeNumber();
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
                    stage.setOnCloseRequest(we -> {
                        if(Client.getInstance()!=null) {
                            Client.getInstance().sendMessage(new CloseConnection(Client.getInstance().getIdGame()));
                            try {
                                Client.getInstance().closeConnection();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        System.exit(0);
                    });
                    Client.getInstance().sendMessage(new SendBoardLoaded(Client.getInstance().getIdGame()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This method is used to display to the first user the window for the selection of player's number in the game
     */
    public void choiseNumber(){
        connectionController.okConnection();
    }

    /**
     * This method is called when a user disconnects himself from the game. All the user in the game lose.
     */
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

    /**
     * This method is used to called lobby Controller and close the stage lobby when the game starts.
     */
    public void closeLobbyStage(){
        javafx.application.Platform.runLater(()-> {
            if (lobbyController!=null)
                 lobbyController.close();
        });
    }

    /**
     * This method is used to close board when the game terminates.
     */
    public void closeBoard(){
        javafx.application.Platform.runLater(()-> {
            boardController.close();
        });
    }

    /**
     * This method is used to open lobby and to wait all users.
     */
    public void openLobbyWaiting(){
        javafx.application.Platform.runLater(()-> {
           connectionController.openLobby();
        });
    }

    /**
     * This method is used to close the window "Nplayers".
     */
    public void closeNumber(){
        javafx.application.Platform.runLater(()-> {
        if(nPlayersController !=null)
            nPlayersController.close();
        });
    }

    /**
     * This method is used to display the win's window when a user wins.
     */
    public void displayWin(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Stage stage= new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("/fxml/winStage.fxml"));
                    Scene scene = new Scene(root);

                    stage.setTitle("WIN");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.getIcons().add(new Image(GUI.class.getClassLoader().getResourceAsStream("images/icon.png")));
                    stage.initStyle(StageStyle.DECORATED);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This method is used to display the lost's window when a user wins.
     */
    public void displayLost(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Stage stage= new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("/fxml/lostStage.fxml"));
                    Scene scene = new Scene(root);

                    stage.setTitle("LOST");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.getIcons().add(new Image(GUI.class.getClassLoader().getResourceAsStream("images/icon.png")));
                    stage.initStyle(StageStyle.DECORATED);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * This method is used to display the window for clients when the server falls
     */
    public void openServerClosedWindow(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Stage stage = new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("/fxml/ServerClosedStage.fxml"));
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

}
