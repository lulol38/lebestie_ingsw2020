package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.View.GUIController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LobbyController {

    @FXML
    AnchorPane lobbyPane;

    public void initialize()
    {
        GUIController.getInstance().setLobbyController(this);
    }

    public void close(){
        Stage stage = (Stage) lobbyPane.getScene().getWindow();
        stage.close();
    }

}
