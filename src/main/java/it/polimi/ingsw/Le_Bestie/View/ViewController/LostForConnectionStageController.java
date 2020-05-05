package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import it.polimi.ingsw.Le_Bestie.View.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Davide Carini
 */

public class LostForConnectionStageController {

    @FXML
    Button btnRejoin;
    @FXML
    Button btnQuit;
    @FXML
    AnchorPane disconnectionPane;

    public void pressRejoin(ActionEvent actionEvent) {

        Stage stage = (Stage) disconnectionPane.getScene().getWindow();
        stage.close();
        Stage connectionMenu= new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/ConnectionStage.fxml"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        connectionMenu.setTitle("SANTORINI");
        connectionMenu.getIcons().add(new Image(GUI.class.getClassLoader().getResourceAsStream("images/icon.png")));
        connectionMenu.initStyle(StageStyle.DECORATED);
        connectionMenu.setScene(scene);
        connectionMenu.setResizable(false);
        connectionMenu.show();
    }

    public void pressQuit(javafx.event.ActionEvent actionEvent) throws IOException {
        Client.getInstance().sendMessage(new CloseConnection(Client.getInstance().getIdGame()));
        Client.getInstance().closeConnection();
        System.exit(0);
    }

    public void close(){
        Stage stage = (Stage) disconnectionPane.getScene().getWindow();
        stage.close();
    }
}
