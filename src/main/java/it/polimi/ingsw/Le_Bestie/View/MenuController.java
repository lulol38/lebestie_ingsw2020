package it.polimi.ingsw.Le_Bestie.View;

import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MenuController {

    private ExecutorService executor = Executors.newCachedThreadPool();

    @FXML
    TextField txtUsername, txtServerAddress, txtServerPort;

    @FXML
    public Button connectButton;

    public void pressConnectButton(javafx.event.ActionEvent actionEvent) {
        System.out.println("Trying to connect...");
        if(txtServerAddress.getText()!="" && txtServerPort.getText()!="" && txtUsername.getText()!="") {
            Client c= new Client(txtServerAddress.getText(), Integer.parseInt(txtServerPort.getText()), txtUsername.getText());
            executor.submit(c);
        }
    }
}
