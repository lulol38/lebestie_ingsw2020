package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.View.GUIController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * This class represents the controller for the connection window
 * @author Davide Carini
 */

public class ConnectionController {

    private int n;

    @FXML
    TextField txtUsername, txtServerAddress, txtServerPort;
    @FXML
    Button btnConnect;
    @FXML
    Button btnBack;
    @FXML
    AnchorPane connectionPane;

    /**
     * initialize the connection controller in the instance of the GUI controller
     */
    public void initialize()
    {
        n=0;
        GUIController.getInstance().setConnectionController(this);
    }

    /**
     * This class sets red the border of textfield that contains errors
     * @param tf is the textfield to set red
     */
    private void setRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        if(!styleClass.contains("error")) {
            styleClass.add("error");
        }
    }

    /**
     * Remove the color red from textfields when are respected conditions allow that
     * @param tf is the textfield that is pressed
     */
    private void removeRed(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        styleClass.removeAll(Collections.singleton("error"));
    }

    public void pressTextFieldUsername(){ removeRed(txtUsername); }
    public void pressTextFieldPort(){ removeRed(txtServerPort); }
    public void pressTextFieldAddress(){ removeRed(txtServerAddress); }

    /**
     * This method is connected to the click of the button Connect and it controls textfields are corrected
     * @param actionEvent
     * @throws Exception
     */
    public void pressConnectButton(ActionEvent actionEvent) throws Exception {
        if(txtUsername.getLength()==0||txtServerAddress.getLength()==0||txtServerPort.getLength()==0||!isInteger(txtServerPort.getText()) ){
            if(txtUsername.getLength()==0)
                 setRed(txtUsername);
            if(txtServerPort.getLength()==0 || !isInteger(txtServerPort.getText()))
                setRed(txtServerPort);
            if(txtServerAddress.getLength()==0)
                setRed(txtServerAddress);
            return;
        }
        System.out.println("Trying to connect...");
        if(txtServerAddress.getText()!="" && txtServerPort.getText()!="" && txtUsername.getText()!="")
            GUIController.getInstance().createConnection(txtServerAddress.getText(),Integer.parseInt(txtServerPort.getText()),txtUsername.getText());
    }

    /**
     * Connect the back button to the precedent stage
     * @param actionEvent
     * @throws Exception
     */
    public void pressBack(ActionEvent actionEvent) throws Exception { GUIController.getInstance().setScene(connectionPane.getScene(),"/fxml/StartStage.fxml"); }

    /**
     * This method is called if the user is the player that creates the game
     */
    public void okConnection(){ GUIController.getInstance().setScene(connectionPane.getScene(), "/fxml/NPlayersStage.fxml"); }

    /**
     * This method is called to open stage associated to the lobby
     */
    public void openLobby(){ GUIController.getInstance().setScene(connectionPane.getScene(),"/fxml/LobbyStage.fxml"); }

    public void close(){
        Stage stage = (Stage) connectionPane.getScene().getWindow();
        stage.close();
    }

    public void disableButton(){
        btnBack.setDisable(true);
        btnConnect.setDisable(true); }

    /**
     * This method controls if the string in input is a queue of integer( is used for the port)
     * @param s is the string associated to the port textfield
     * @return true if s is a valid integer, false else
     */
    public static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try {
            Integer.parseInt(s);
            isValidInteger = true;
        } catch (NumberFormatException ex) {
            System.out.println("The server port name is not a not a string, but is the number:45331");
        }
        return isValidInteger;
    }
}
