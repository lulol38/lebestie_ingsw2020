package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.event.ActionEvent;

public class ConnectionController {
    @FXML
    TextField txtUsername, txtServerAddress, txtServerPort;
    @FXML
    Button btnConnect;
    @FXML
    AnchorPane connectionPane;

    private ExecutorService executor = Executors.newCachedThreadPool();

    public void pressConnectButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Trying to connect...");
        if(txtServerAddress.getText()!="" && txtServerPort.getText()!="" && txtUsername.getText()!="") {
            Client c= new Client(txtServerAddress.getText(), Integer.parseInt(txtServerPort.getText()), txtUsername.getText());
            c.init();
            executor.submit(c);
        }
        AnchorPane lobbyPane = FXMLLoader.load(getClass().getResource("/fxml/LobbyStage.fxml"));
        connectionPane.getChildren().setAll(lobbyPane);

    }
    public  void pressBack(ActionEvent actionEvent) throws IOException {
        AnchorPane rootPane = FXMLLoader.load(getClass().getResource("/fxml/StartStage.fxml"));
        connectionPane.getChildren().setAll(rootPane);
    }


}
