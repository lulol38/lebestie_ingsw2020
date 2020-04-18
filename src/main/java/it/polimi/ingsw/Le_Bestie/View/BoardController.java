package it.polimi.ingsw.Le_Bestie.View;

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

//Controller for the client Board
public class BoardController {

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
