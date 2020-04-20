package it.polimi.ingsw.Le_Bestie.View;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;
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

    private int countPositionedBuilders=0;
    private int pos1x, pos1y, pos2x, pos2y; //Builders positions

    @FXML
    GridPane gridBoard;
    @FXML
    ImageView imgGodCard;
    @FXML
    Button btnEndTurn;
    @FXML
    Label lblMessages;

    public BoardController(){
        this.instance=this;

        /*gridBoard.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                selectedCell.setPosition(new Position((int)e.getSceneX(), (int)e.getSceneY()));
            }
        });*/
    }

    public static BoardController getInstance() {
        return instance;
    }

    public void pressEndTurn(ActionEvent actionEvent){
        Client.getInstance().sendMessage(new SendEndTurn());
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                disableGUI();
            }
        });
    }

    public void activeGUI(){
        gridBoard.setDisable(false);
    }

    public void disableGUI(){
        gridBoard.setDisable(true);
        lblMessages.setText("");
    }

    public void BuilderPositions(){
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                activeGUI();
                lblMessages.setText("Add workers to board");
            }
        });
    }

    public void getCell(MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if(countPositionedBuilders==0||countPositionedBuilders==1) { //Enter here only to setup builders
            if (clickedNode != gridBoard) {

                // click on descendant node
                countPositionedBuilders++;
                if (countPositionedBuilders == 1) {
                    pos1x = gridBoard.getColumnIndex(clickedNode);
                    pos1y = gridBoard.getRowIndex(clickedNode);
                }
                if (countPositionedBuilders == 2) {
                    pos2x = gridBoard.getColumnIndex(clickedNode);
                    pos2y = gridBoard.getRowIndex(clickedNode);
                    Client.getInstance().sendMessage(new SendBuilderPositions(pos1x, pos1y, pos2x, pos2y));
                    disableGUI();
                }
            }
        }
        else { //Here the user is doing a move or a build because the builders are already setted

        }

    }
}
