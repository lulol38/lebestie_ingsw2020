package it.polimi.ingsw.Le_Bestie.View;

import it.polimi.ingsw.Le_Bestie.View.ViewController.ConnectionController;
import it.polimi.ingsw.Le_Bestie.View.ViewController.NPlayersController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GUIController {

    public static GUIController instance;
    private ConnectionController connectionController;

    public static GUIController getInstance() {
        if (instance == null)
            instance = new GUIController();
        return instance;
    }

    public static void setScene(Scene scene,String path) {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(GUIController.class.getResource(path));
            scene.setRoot(root);
        }catch (Exception e){

        }
    }

    public void numberOfPlayers()  {
         Platform.runLater(new Runnable() {
           @Override
            public void run() {
                try {
                    Stage stage= new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("/fxml/NPlayersStage.fxml"));
                    Scene scene = new Scene(root);
                    stage.setTitle("START GAME");
                    stage.initStyle(StageStyle.UNDECORATED);
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
