package it.polimi.ingsw.Le_Bestie.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;


public class StartGameController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView btnStart;
    @FXML
    private ImageView btnQuit;
    @FXML
    public void initialize() {
        button();
    }

    private void button(){
        btnStart.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> playGameClick());
        btnQuit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.exit(0));
    }
    private void playGameClick() {

        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
            rootPane.getChildren().setAll(pane);
        }
        catch (IOException e){

        }
    }
}
