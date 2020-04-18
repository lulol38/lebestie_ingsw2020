package it.polimi.ingsw.Le_Bestie.Controller;

import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendEndTurn;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendNumPlayers;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendUsername;
import it.polimi.ingsw.Le_Bestie.Network.Server.Server;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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

    @FXML
    Pane paneGrid;
    @FXML
    Button btn00, btn01, btn02, btn03, btn04, btn10, btn11, btn12, btn13, btn14, btn20, btn21, btn22, btn23, btn24, btn30, btn31, btn32, btn33, btn34, btn40, btn41, btn42, btn43, btn44;
    @FXML
    Label lbl00, lbl01, lbl02, lbl03, lbl04, lbl10, lbl11, lbl12, lbl13, lbl14, lbl20, lbl21, lbl22, lbl23, lbl24, lbl30, lbl31, lbl32, lbl33, lbl34, lbl40, lbl41, lbl42, lbl43, lbl44;
    @FXML
    ImageView imgGodCard;
    @FXML
    Button btnEndTurn;

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

    public void pressEndTurn(ActionEvent actionEvent){
        Client.getInstance().sendMessage(new SendEndTurn());
        btnEndTurn.setDisable(true);
        paneGrid.setDisable(true);
    }

    public static void activeGUI(){
        //btnEndTurn.setDisable(true);
        //paneGrid.setDisable(true);
    }
}
