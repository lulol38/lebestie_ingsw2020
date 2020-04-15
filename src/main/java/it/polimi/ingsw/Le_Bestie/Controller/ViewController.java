package it.polimi.ingsw.Le_Bestie.Controller;


import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendNumPlayers;
import it.polimi.ingsw.Le_Bestie.Network.Server.Server;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Parameter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Controller for the client, it takes place between the view and the network in the client side
public class ViewController {

    private ExecutorService executor = Executors.newCachedThreadPool();
    private Client c;

    @FXML
    TextField txtUsername, txtServerAddress, txtServerPort;

    public void pressConnectButton(javafx.event.ActionEvent actionEvent) {
        System.out.println("Trying to connect...");
        if(txtServerAddress.getText()!="" && txtServerPort.getText()!="" && txtUsername.getText()!="") {
            c = new Client(txtServerAddress.getText(), Integer.parseInt(txtServerPort.getText()), txtUsername.getText());
            executor.submit(c);
        }
    }

    /*
    @FXML
    public void exitApplication(javafx.event.ActionEvent event) {
        c.sendMessage(new CloseConnection());
    }*/
}
