package it.polimi.ingsw.Le_Bestie.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StartStage.fxml"));
        stage.getIcons().add(new Image(GUI.class.getClassLoader().getResourceAsStream("images/icon.png")));
        Scene scene = new Scene(root);
        stage.setTitle("SANTORINI");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
