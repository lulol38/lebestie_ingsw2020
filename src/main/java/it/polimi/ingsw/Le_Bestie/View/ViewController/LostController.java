package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import it.polimi.ingsw.Le_Bestie.View.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * this class is used to manage the lost's window
 * @author Davide Carini
 */
public class LostController {

    @FXML
    AnchorPane lostPane;

    /**
     * this method is associated to the button rejoin and allows to user to join another match
     * @param actionEvent
     */
    public void pressRejoin(ActionEvent actionEvent) {
        Stage stage = (Stage) lostPane.getScene().getWindow();
        stage.close();
        Stage connectionMenu= new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/ConnectionStage.fxml"));
        }
        catch (Exception e){

        }
        Scene scene = new Scene(root);
        connectionMenu.setTitle("SANTORINI");
        connectionMenu.getIcons().add(new Image(GUI.class.getClassLoader().getResourceAsStream("images/icon.png")));
        connectionMenu.initStyle(StageStyle.DECORATED);
        connectionMenu.setScene(scene);
        connectionMenu.setResizable(false);
        connectionMenu.show();
    }

    /**
     * this method is associated at the click of the button quit and close client window
     * @param actionEvent
     * @throws IOException
     */
    public void pressQuit(ActionEvent actionEvent) throws IOException {
        Client.getInstance().sendMessage(new CloseConnection(Client.getInstance().getIdGame()));
        Client.getInstance().closeConnection();
        System.exit(0);
    }

    /**
     * this method closes the window
     */
    public void close(){
        Stage stage = (Stage) lostPane.getScene().getWindow();
        stage.close();
    }
}
