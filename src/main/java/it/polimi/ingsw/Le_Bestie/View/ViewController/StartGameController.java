package it.polimi.ingsw.Le_Bestie.View.ViewController;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class StartGameController {

    @FXML
    public AnchorPane rootPane;

    public void playGameClick(ActionEvent actionEvent) throws IOException {
        AnchorPane connectionPane = FXMLLoader.load(getClass().getResource("/fxml/ConnectionStage.fxml"));
        rootPane.getChildren().setAll(connectionPane);
    }

    public void pressQuit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
