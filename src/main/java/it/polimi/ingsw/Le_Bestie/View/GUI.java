package it.polimi.ingsw.Le_Bestie.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("SANTORINI");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(GUI.class.getClassLoader().getResourceAsStream("images/icon.png")));
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene=new Scene(new AnchorPane());
        primaryStage.setScene(scene);
        GUIController.setScene(scene,"/fxml/StartStage.fxml");
        primaryStage.show();

    }
}
