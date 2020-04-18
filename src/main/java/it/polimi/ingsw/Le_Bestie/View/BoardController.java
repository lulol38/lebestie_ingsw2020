package it.polimi.ingsw.Le_Bestie.View;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendEndTurn;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendNumPlayers;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendUsername;
import it.polimi.ingsw.Le_Bestie.Network.Server.Server;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.*;
import java.lang.reflect.Parameter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Controller for the client Board
public class BoardController extends GridPane {

    private static BoardController instance;

    private Board board;
    private Cell selectedCell;

    @FXML
    GridPane gridBoard;
    @FXML
    ImageView imgGodCard;
    @FXML
    Button btnEndTurn;

    public BoardController(){
        this.instance=this;
        this.board=new Board();

        gridBoard.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                selectedCell.setPosition(new Position((int)e.getSceneX(), (int)e.getSceneY()));
            }
        });
    }

    public static BoardController getInstance() {
        return instance;
    }

    public void pressEndTurn(ActionEvent actionEvent){
        Client.getInstance().sendMessage(new SendEndTurn());
        btnEndTurn.setDisable(true);
        gridBoard.setDisable(true);
    }

    public void activeGUI(){
        btnEndTurn.setDisable(false);
        gridBoard.setDisable(false);
    }
}
