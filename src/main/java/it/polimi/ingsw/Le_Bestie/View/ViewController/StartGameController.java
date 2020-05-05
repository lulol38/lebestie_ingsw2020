package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.View.GUIController;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Davide Carini
 */
public class StartGameController {

    @FXML
    public AnchorPane rootPane;

    public void playGameClick(ActionEvent actionEvent) throws Exception { GUIController.getInstance().setScene(rootPane.getScene(),"/fxml/ConnectionStage.fxml"); }

    public void pressQuit(ActionEvent actionEvent) {
        System.exit(0);
    }


}
