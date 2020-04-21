package it.polimi.ingsw.Le_Bestie.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class StartGameController {

    private ExecutorService executor = Executors.newCachedThreadPool();
    @FXML
    ImageView btnPlay;
    @FXML
    Label l1;

    public void pressButtonPlay(javafx.event.ActionEvent actionEvent){

    }



}
