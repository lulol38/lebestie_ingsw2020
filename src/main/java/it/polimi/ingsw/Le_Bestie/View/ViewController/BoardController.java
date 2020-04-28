package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;
import it.polimi.ingsw.Le_Bestie.View.GUIController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

//Controller for the client Board
public class BoardController{

    private static BoardController instance;

    private int countPositionedBuilders=0;
    private boolean s=false;
    private int posx, posy; //Builders positions

    private boolean buildersSetted;

    private int selectedBuilderX=10;
    private int selectedBuilderY=10;
    private int selectedCellX=10;
    private int selectedCellY=10;

    private Node n;
    int i = 0;

    @FXML
    GridPane gridBoard;
    @FXML
    GridPane gridLevels;
    @FXML
    ImageView imgGodCard;
    @FXML
    ImageView imgPower;
    @FXML
    Label lblMessages;
    @FXML
    Label lblOpponents1;
    @FXML
    Label lblOpponents2;
    @FXML
    Label lblTurn;
    @FXML
    Label lblCard;
    @FXML
    Rectangle rect;
    @FXML
    AnchorPane boardPane;
    @FXML
    Label lblDescription;
    @FXML
    ImageView imgPowerInactive;

    public BoardController(){
        this.instance=this;
        this.buildersSetted=false;
    }

    public static BoardController getInstance(){
        if (instance == null)
            instance = new BoardController();
        return instance;
    }

    public void initialize()
    {
        GUIController.getInstance().setBoardController(this);
    }

    public void setSelectedCellX(int selectedCellX) {
        this.selectedCellX = selectedCellX;
    }

    public void setSelectedCellY(int selectedCellY) {
        this.selectedCellY = selectedCellY;
    }

    public void setSelectedBuilderX(int selectedBuilderX) {
        this.selectedBuilderX = selectedBuilderX;
    }

    public void setSelectedBuilderY(int selectedBuilderY) {
        this.selectedBuilderY = selectedBuilderY;
    }

    public boolean isBuildersSetted() {
        return buildersSetted;
    }

    public void setBuildersSetted(boolean buildersSetted) {
        this.buildersSetted = buildersSetted;
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
            lblMessages.setText("Add workers to board");
        });
    }

    public void AskBuilderChosen(){
        javafx.application.Platform.runLater(()->{
            s=false;
            setSelectedBuilderX(10);
            setSelectedBuilderY(10);
            lblMessages.setText("Select a worker");
        });
    }

    public void AskCellChosen(){
        javafx.application.Platform.runLater(()->{
            setSelectedCellX(10);
            setSelectedCellY(10);
            lblMessages.setText("Select a cell");
        });
    }

    public void ShowQuestionPower(String mex){
        javafx.application.Platform.runLater(()->{
            lblMessages.setText(mex);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("POWER");
            alert.setContentText(mex);

            ButtonType buttonTypeOne = new ButtonType("Yes");
            ButtonType buttonTypeTwo = new ButtonType("No");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){

                Client.getInstance().sendMessage(new SendCellWithPower(selectedCellX, selectedCellY, false, Client.getInstance().getIdGame()));
            } else if (result.get() == buttonTypeTwo) {
                Client.getInstance().sendMessage(new SendCellWithPower(selectedCellX, selectedCellY, true, Client.getInstance().getIdGame()));
            }
        });
    }

    public void ShowPowerMessage(String mex) {
        javafx.application.Platform.runLater(()->{
            lblMessages.setText(mex);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("POWER");
            alert.setContentText(mex);

            ButtonType buttonTypeOne = new ButtonType("Yes");
            ButtonType buttonTypeTwo = new ButtonType("No");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                imgPower.setOpacity(1.0);
                imgPowerInactive.setOpacity(0.0);
                AskCellChosen();
            } else if (result.get() == buttonTypeTwo) {
                Client.getInstance().sendMessage(new SendPowerNotUsed(selectedCellX, selectedCellY, Client.getInstance().getIdGame()));
            }
        });
    }

    public void getCell(MouseEvent event) {
        javafx.application.Platform.runLater(()->{

            Node clickedNode = event.getPickResult().getIntersectedNode();
            if (buildersSetted == false) { //Enter here only to setup builders
                if (clickedNode != gridBoard) {
                    if(!(clickedNode instanceof Label))
                        clickedNode=clickedNode.getParent();
                    posx = gridBoard.getRowIndex(clickedNode);
                    posy = gridBoard.getColumnIndex(clickedNode);


                    try {
                        Client.getInstance().sendMessage(new SendBuilderPositions(posx, posy, Client.getInstance().getIdGame()));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } else { //Here the user is doing a move or a build because the builders are already setted
                if(!(clickedNode instanceof Label))
                    clickedNode=clickedNode.getParent();

                if (selectedBuilderX == 10 || selectedBuilderY == 10) {
                    if (clickedNode != gridBoard) {
                        selectedBuilderX = gridBoard.getRowIndex(clickedNode);
                        selectedBuilderY = gridBoard.getColumnIndex(clickedNode);

                        Client.getInstance().sendMessage(new SendBuilderChosen(selectedBuilderX, selectedBuilderY, Client.getInstance().getIdGame()));
                        n=clickedNode;

                    }
                } else {
                    if (selectedCellX == 10 || selectedCellY == 10) {
                        if (clickedNode != gridBoard) {

                            selectedCellX = gridBoard.getRowIndex(clickedNode);
                            selectedCellY = gridBoard.getColumnIndex(clickedNode);

                            Client.getInstance().sendMessage(new SendCellChosen(selectedCellX, selectedCellY, Client.getInstance().getIdGame()));

                        }
                    }
                }
            }
        });
    }

    public void setupBoard(Board b) { //UPDATE BOARD
        javafx.application.Platform.runLater(()->{
            Field field = null;

            for(int x=0; x<5; x++){
                for(int y=0; y<5; y++){
                    //Label lbl = (Label) getNodeGridPane(gridLevels, x, y);
                    //lbl.setText(String.valueOf(b.getGrid()[x][y].getLevel()));

                    Label Node = (Label) getNodeGridPane(gridBoard, x, y);
                    Node.setText(String.valueOf(b.getGrid()[x][y].getLevel()));
                    Node.setAlignment(Pos.CENTER);
                    if(b.getGrid()[x][y].getBuilder()!=null){ //There is a builder in the cell, update gui

                        try {
                            field = Class.forName("javafx.scene.paint.Color").getField(b.getGrid()[x][y].getBuilder().getPlayer().getColor().toString());
                            Color color = (Color)field.get(null);
                            Node.setGraphic(new Circle(10,10 ,10, color));

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else{
                        Node.setGraphic(null);
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

    public void beginTurn() {
        javafx.application.Platform.runLater(()->{
            BoardController.getInstance().activeGUI();
            lblTurn.setText("IS YOUR TURN");
        });
    }

    public void endTurn() {
        javafx.application.Platform.runLater(()->{
            BoardController.getInstance().disableGUI();
            lblTurn.setText("YOUR TURN IS ENDED");
            imgPower.setOpacity(0.0);
            imgPowerInactive.setOpacity(1.0);
            if(n!=null)
                n.setStyle("-fx-border-color: Transparent;");
        });
    }

    public void AskCellError(String s) {
        javafx.application.Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText(s);

            alert.showAndWait();

            AskCellChosen();
        });
    }

    public void addCardOnBoard(String card, String color, String path,String description){
        javafx.application.Platform.runLater(()-> {
            lblCard.setText(card);
            rect.setFill(Color.valueOf(color));
            imgGodCard.setImage(new Image(path));
            lblDescription.setText(description);

        });
    }

    public void setClickBorder()
    {
        n.setStyle("-fx-border-color: #ff0000;");
    }

    public void close(){
        Stage stage = (Stage) boardPane.getScene().getWindow();
        stage.close();
    }

    public void createBoard(){
        GUIController.getInstance().setScene(gridBoard.getScene(),"/fxml/BoardStage.fxml");
    }

    public void addOpponentsOnBoard(ArrayList<String> opponents, ArrayList<String> opponentsGods) {
        javafx.application.Platform.runLater(()-> {
            for(int j=0;j<opponents.size();j++){
                if(j==1){
                    lblOpponents2.setText(opponents.get(j)+" with "+opponentsGods.get(j));
                    break;
                }
                lblOpponents1.setText(opponents.get(j)+" with "+opponentsGods.get(j));
            }

        });
    }
}
