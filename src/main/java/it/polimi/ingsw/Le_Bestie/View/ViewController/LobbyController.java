package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.View.GUIController;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * this class is the controller of the lobby's window
 * @author Davide Carini
 */
public class LobbyController {

    @FXML
    AnchorPane lobbyPane;

    /**
     * this method is called when this controller is initialized and is helpful to set lobby controller in the GUI controller
     */
    public void initialize()
    {
        GUIController.getInstance().setLobbyController(this);
    }

    /**
     * this method is used to close the window associated to the lobby
     */
    public void close(){
        Stage stage = (Stage) lobbyPane.getScene().getWindow();
        stage.close();
    }

}
