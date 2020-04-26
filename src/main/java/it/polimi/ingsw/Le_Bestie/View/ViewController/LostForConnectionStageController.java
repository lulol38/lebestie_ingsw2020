package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.View.GUI;
import it.polimi.ingsw.Le_Bestie.View.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LostForConnectionStageController {
    @FXML
    Button btnRejoin;
    @FXML
    Button btnQuit;
    @FXML
    AnchorPane disconnectionPane;

    public void pressRejoin(ActionEvent actionEvent) {

        Stage stage = (Stage) disconnectionPane.getScene().getWindow();
        stage.close();
        Stage connectionMenu= new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/ConnectionStage.fxml"));
        }
        catch (Exception e){

        }
        Scene scene = new Scene(root);
        connectionMenu.setTitle("SANTORINI");
        connectionMenu.getIcons().add(new Image(GUI.class.getClassLoader().getResourceAsStream("images/icon.png")));
        connectionMenu.initStyle(StageStyle.DECORATED);
        connectionMenu.setScene(scene);
        connectionMenu.setResizable(false);
        connectionMenu.show();

    }

    public void pressQuit(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }

    public void close(){
        Stage stage = (Stage) disconnectionPane.getScene().getWindow();
        stage.close();
    }
}
