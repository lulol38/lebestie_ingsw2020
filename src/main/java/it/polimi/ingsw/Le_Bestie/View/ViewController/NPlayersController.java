package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendNumPlayers;
import it.polimi.ingsw.Le_Bestie.View.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NPlayersController {

    @FXML
    public AnchorPane playersPane;
    @FXML
    public RadioButton rdb2P;
    @FXML
    public ToggleGroup mygroup;
    @FXML
    public RadioButton rdb3P;

    public void initialize() { GUIController.getInstance().setNPlayersController(this); }

    public void pressSendNumPlayers(ActionEvent actionEvent) {
        if(rdb2P.isSelected()||rdb3P.isSelected()){
            Client.getInstance().sendMessage(new SendNumPlayers(Integer.parseInt(mygroup.getSelectedToggle().getUserData().toString())));
            GUIController.getInstance().setScene(playersPane.getScene(),"/fxml/LobbyStage.fxml");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Number of players");
            alert.setHeaderText(null);
            alert.setContentText("The number of players must be 2 or 3");
            alert.showAndWait();
        }

    }

    public void close(){
        Stage stage = (Stage) playersPane.getScene().getWindow();
        stage.close();
    }
}
