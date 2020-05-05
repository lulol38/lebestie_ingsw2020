package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.View.GUIController;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

/**
 *This class is used to control the start game window
 * @author Davide Carini
 */
public class StartGameController {

    @FXML
    public AnchorPane rootPane;

    /**
     * This method connect the start game window with the connection window
     * @param actionEvent
     * @throws Exception
     */
    public void playGameClick(ActionEvent actionEvent) throws Exception { GUIController.getInstance().setScene(rootPane.getScene(),"/fxml/ConnectionStage.fxml"); }

    /**
     * This method is used to close the window and it's called when the button "quit" is pressed
     * @param actionEvent
     */
    public void pressQuit(ActionEvent actionEvent) {
        System.exit(0);
    }


}
