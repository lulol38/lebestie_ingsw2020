package it.polimi.ingsw.Le_Bestie.View;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.SendUpdatedBoard;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Controller for the client Board
public class BoardController extends GridPane {

    private static BoardController instance;

    private int countPositionedBuilders=0;
    private int posx, posy; //Builders positions

    private boolean buildersSetted;

    @FXML
    GridPane gridBoard;
    @FXML
    ImageView imgGodCard;

    @FXML
    Label lblMessages;

    public BoardController(){
        this.instance=this;
        this.buildersSetted=false;
        /*gridBoard.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                selectedCell.setPosition(new Position((int)e.getSceneX(), (int)e.getSceneY()));
            }
        });*/
    }

    public boolean isBuildersSetted() {
        return buildersSetted;
    }

    public void setBuildersSetted(boolean buildersSetted) {
        this.buildersSetted = buildersSetted;
    }

    public static BoardController getInstance() {
        return instance;
    }

    public void activeGUI(){
        javafx.application.Platform.runLater(() -> gridBoard.setDisable(false));
    }

    public void disableGUI(){
        javafx.application.Platform.runLater(() -> {
            gridBoard.setDisable(true);
            lblMessages.setText("");});

    }

    public void BuilderPositions(){
        javafx.application.Platform.runLater(() -> {
            activeGUI();
            lblMessages.setText("Add workers to board");
        });
    }

    public void getCell(MouseEvent event) {
        javafx.application.Platform.runLater(() -> {
            Node clickedNode = event.getPickResult().getIntersectedNode();
            if(buildersSetted==false) { //Enter here only to setup builders
                if (clickedNode != gridBoard) {
                        posx = gridBoard.getRowIndex(clickedNode);
                        posy = gridBoard.getColumnIndex(clickedNode);
                        try {
                            Client.getInstance().sendMessage(new SendBuilderPositions(posx, posy));
                        }
                        catch(Exception ex){
                            System.out.println(ex.getMessage());
                        }
                }
            }
            else { //Here the user is doing a move or a build because the builders are already setted

            }
        });


    }

    public void setupBoard(Board b) { //UPDATE BOARD
        javafx.application.Platform.runLater(()->{
            Field field = null;


            for(int x=0; x<5; x++){
                for(int y=0; y<5; y++){
                    if(b.getGrid()[x][y].getBuilder()!=null){ //There is a builder in the cell, update gui
                        Label Node = (Label) getNodeGridPane(gridBoard, x, y);

                        try {
                            field = Class.forName("javafx.scene.paint.Color").getField(b.getGrid()[x][y].getBuilder().getPlayer().getColor().toString());
                            Color color = (Color)field.get(null);
                            Node.setGraphic(new Circle(10,10 ,25, color));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }

        });
    }

    private Label getNodeGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridBoard.getChildren()) {
            if (node instanceof Label
                    && gridBoard.getColumnIndex(node) == col
                    && gridBoard.getRowIndex(node) == row) {
                return (Label) node;
            }
        }
        return null;
    }
}
