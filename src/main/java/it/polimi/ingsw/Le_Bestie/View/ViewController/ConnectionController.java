package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.View.GUIController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

public class ConnectionController {

    @FXML
    TextField txtUsername, txtServerAddress, txtServerPort;
    @FXML
    Button btnConnect;
    @FXML
    AnchorPane connectionPane;

    private ExecutorService executor = Executors.newCachedThreadPool();

    private void setRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();

        if(!styleClass.contains("tferror")) {
            styleClass.add("tferror");
        }
    }
    private void removeRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("tferror"));
    }

    public void pressTextFieldUsername(){
        removeRed(txtUsername);
    }
    public void pressTextFieldPort(){
        removeRed(txtServerPort);
    }
    public void pressTextFieldAddress(){
        removeRed(txtServerAddress);
    }

    public void pressConnectButton(ActionEvent actionEvent) throws Exception {
        if(txtUsername.getLength()==0||txtServerAddress.getLength()==0||txtServerPort.getLength()==0){
            if(txtUsername.getLength()==0)
                 setRed(txtUsername);
            if(txtServerPort.getLength()==0)
                setRed(txtServerPort);
            if(txtServerAddress.getLength()==0)
                setRed(txtServerAddress);
            return;
        }

        System.out.println("Trying to connect...");
        if(txtServerAddress.getText()!="" && txtServerPort.getText()!="" && txtUsername.getText()!="") {

            Client c= new Client(txtServerAddress.getText(), Integer.parseInt(txtServerPort.getText()), txtUsername.getText());
            c.init();
            executor.submit(c);
        }

        GUIController.getInstance().setScene(connectionPane.getScene(),"/fxml/LobbyStage.fxml");
       /* AnchorPane lobbyPane = FXMLLoader.load(getClass().getResource("/fxml/LobbyStage.fxml"));
        connectionPane.getChildren().setAll(lobbyPane);*/

    }
    public  void pressBack(ActionEvent actionEvent) throws Exception {
        GUIController.getInstance().setScene(connectionPane.getScene(),"/fxml/StartStage.fxml");
        /*AnchorPane rootPane = FXMLLoader.load(getClass().getResource("/fxml/StartStage.fxml"));
        connectionPane.getChildren().setAll(rootPane);*/
    }

}
