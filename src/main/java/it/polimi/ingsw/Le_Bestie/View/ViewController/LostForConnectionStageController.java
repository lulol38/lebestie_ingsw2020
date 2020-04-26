package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.View.GUIController;
import javafx.fxml.FXML;

import java.awt.*;
import java.awt.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class LostForConnectionStageController {
    @FXML
    Button btnRejoin;
    @FXML
    Button btnQuit;
    @FXML
    AnchorPane disconnectionPane;

    public void pressQuit(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }
}
