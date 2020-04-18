package it.polimi.ingsw.Le_Bestie.Controller;

import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendNumPlayers;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendUsername;
import it.polimi.ingsw.Le_Bestie.Network.Server.Server;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.*;
import java.lang.reflect.Parameter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Controller for the client, it takes place between the view and the network in the client side
public class ViewController {

    private ExecutorService executor = Executors.newCachedThreadPool();

    @FXML
    TextField txtUsername, txtServerAddress, txtServerPort;
    @FXML
    Button sendNumPlayers;
    @FXML
    TextField txtNumPlayers;
    @FXML
    TextField txtUsernameModified;
    @FXML
    RadioButton radio2P;
    @FXML
    RadioButton radio3P;
    @FXML
    ToggleGroup mygroup;


    public void pressConnectButton(javafx.event.ActionEvent actionEvent) {
        System.out.println("Trying to connect...");
        if(txtServerAddress.getText()!="" && txtServerPort.getText()!="" && txtUsername.getText()!="") {
            Client c= new Client(txtServerAddress.getText(), Integer.parseInt(txtServerPort.getText()), txtUsername.getText());
            executor.submit(c);
        }
    }


    public void pressSendNumPlayers(ActionEvent actionEvent) {

        if(radio2P.isSelected()||radio3P.isSelected()){
            Client.getInstance().sendMessage(new SendNumPlayers(Integer.parseInt(mygroup.getSelectedToggle().getUserData().toString())));
            Node  source = (Node)  actionEvent.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Number of players");
            alert.setHeaderText(null);
            alert.setContentText("The number of players must be 2 or 3");

            alert.showAndWait();
        }
    }


    public void pressSendCorrectedUsername(ActionEvent actionEvent) {
        String text= txtUsernameModified.getText();
        if(text.length()!=0){
            Client.getInstance().sendMessage(new SendUsername(text));

            Node  source = (Node)  actionEvent.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modify Username");
            alert.setHeaderText(null);
            alert.setContentText("Empty Username");

            alert.showAndWait();
        }
    }
}
