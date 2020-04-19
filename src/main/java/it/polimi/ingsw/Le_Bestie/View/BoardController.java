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
    private int pos1x, pos1y, pos2x, pos2y;

    private Board board;

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
        this.board=new Board();

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
        gridBoard.setDisable(true);
    }

    public void activeGUI(){
        gridBoard.setDisable(false);
    }

    public void BuilderPositions(){
        gridBoard.setDisable(false);
        lblMessages.setText("Add workers to board");
    }

    public void getCell(MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != gridBoard) {
            // click on descendant node
            countPositionedBuilders++;
            if(countPositionedBuilders==1) {
                pos1x = GridPane.getColumnIndex(clickedNode);
                pos1y = GridPane.getRowIndex(clickedNode);
            }
            if(countPositionedBuilders==2){
                pos2x=GridPane.getColumnIndex(clickedNode);
                pos2y=GridPane.getRowIndex(clickedNode);
                Client.getInstance().sendMessage(new SendBuilderPositions(pos1x, pos1y, pos2x, pos2y));
                gridBoard.setDisable(true);
                Client.getInstance().sendMessage(new SendEndTurn());
            }
        }
    }
}
