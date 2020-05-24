package it.polimi.ingsw.Le_Bestie.View.ViewController;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;
import it.polimi.ingsw.Le_Bestie.View.GUIController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.lang.reflect.Field;
import java.util.*;

/**
 * This class represents the controller for the client's board
 * @author Davide Carini
 */

public class BoardController{

    private static BoardController instance;
    private int countPositionedBuilders=0;
    private boolean s;
    private int posx, posy; //Builder's positions
    private boolean buildersSetted;
    private int selectedBuilderX=10;
    private int selectedBuilderY=10;
    private int selectedCellX=10;
    private int selectedCellY=10;
    private Node n;

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
    Rectangle rect1;
    @FXML
    Rectangle rect2;
    @FXML
    AnchorPane boardPane;
    @FXML
    Label lblDescription;
    @FXML
    ImageView imgPowerInactive;

    //CONSTRUCTOR
    public BoardController(){
        this.instance=this;
        this.buildersSetted=false;
        s=false;
    }

    //GETTERS
    public boolean isBuildersSetted() { return buildersSetted; }

    //SETTERS
    public void setSelectedCellX(int selectedCellX) { this.selectedCellX = selectedCellX; }
    public void setSelectedCellY(int selectedCellY) { this.selectedCellY = selectedCellY; }
    public void setSelectedBuilderX(int selectedBuilderX) { this.selectedBuilderX = selectedBuilderX; }
    public void setSelectedBuilderY(int selectedBuilderY) { this.selectedBuilderY = selectedBuilderY; }
    public void setBuildersSetted(boolean buildersSetted) { this.buildersSetted = buildersSetted; }

    /**
     * The singleton instance of the Board Controller returns, if it has not been created it allocates it as well
     * @return the singleton instance
     */
    public static BoardController getInstance(){
        if (instance == null)
            instance = new BoardController();
        return instance;
    }

    /**
     * This method is used for initialize boardController in the instance of the GUI controller.
     */
    public void initialize()
    {
        GUIController.getInstance().setBoardController(this);
    }

    /**
     * Active the GUI
     */
    public void activeGUI(){ javafx.application.Platform.runLater(() -> gridBoard.setDisable(false)); }

    /**
     * Disable the GUI
     */
    public void disableGUI(){
        javafx.application.Platform.runLater(() -> {
            gridBoard.setDisable(true);
            lblMessages.setText("");
        });
    }

    /**
     *Request to the user the initial positions of builders
     */
    public void BuilderPositions(){
        javafx.application.Platform.runLater(() -> {
            lblMessages.setText("ADD WORKERS TO THE BOARD");
        });
    }

    /**
     *Ask to the user the builder that he want to move.
     */
    public void AskBuilderChosen(){
        javafx.application.Platform.runLater(()->{
            s=false;
            setSelectedBuilderX(10);
            setSelectedBuilderY(10);
            lblMessages.setText("SELECT A WORKER");
        });
    }
    /**
     *Ask to the user the cell.
     */
    public void AskCellChosen(){
        javafx.application.Platform.runLater(()->{
            setSelectedCellX(10);
            setSelectedCellY(10);
            lblMessages.setText("SELECT A CELL");
        });
    }

    /**
     * This method shows Power request.
     * @param mex is an answer for the client for use or not the power of the god card
     */
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

    /**
     * This method shows the power used
     * @param mex represents the power
     */
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

    /**
     *
     * @param event get the cell that the user clicks
     */
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

    /**
     * This method update the board (visible to the user)
     * @param b is the board that is serialized from the model and represents the situation of the board game
     */
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
                            Node.setGraphic(new Circle(15,15 ,15, color));

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

    /**
     *
     * @param gridPane grid with the 25 cell
     * @param row
     * @param col
     * @return label associated to the node at coordinates (row,col)
     */
    private Label getNodeGridPane(GridPane gridPane, int row, int col) {
        for (Node node : gridBoard.getChildren()) {
            if (node instanceof Label && gridBoard.getColumnIndex(node) == col && gridBoard.getRowIndex(node) == row) {
                return (Label) node;
            }
        }
        return null;
    }

    /**
     * Active GUI and display label for the start turn of the player
     */
    public void beginTurn() {
        javafx.application.Platform.runLater(()->{
            BoardController.getInstance().activeGUI();
            lblTurn.setText("IS YOUR TURN");
        });
    }

    /**
     * Disable GUI, hides color of border of cell clicked and display label that indicates the turn is finished
     */
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

    /**
     * this methos is used whe there are no more pieces available
     * @param s
     */
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

    /**
     * Display god card, color associated to the player and description god card drawn
     * @param card is the god card drawn from the deck
     * @param color is the color univocal associated to the user
     * @param path is the relative path of the card image
     * @param description is the power of the god card
     */
    public void addCardOnBoard(String card, String color, String path,String description){
        javafx.application.Platform.runLater(()-> {
            lblCard.setText(card);
            rect.setFill(Color.valueOf(color));
            imgGodCard.setImage(new Image(path));
            lblDescription.setText(description);
        });
    }

    /**
     * This method is used to set the Color of the border of the cell to remember the builder and the first cell selected
     */
    public void setClickBorder() { n.setStyle("-fx-border-color: #ff0000;"); }

    /**
     * This method is used to close the window associated to the board
     */
    public void close(){
        Stage stage = (Stage) boardPane.getScene().getWindow();
        stage.close();
    }

    /**
     * This method writes on the board the opponents players in the game
     * @param opponents are nicknames of other players
     * @param opponentsGods are names of cards drawn by other players
     */
    public void addOpponentsOnBoard(ArrayList<String> opponents, ArrayList<String> opponentsGods, ArrayList<String> opponentsColors) {
        javafx.application.Platform.runLater(()-> {
            for(int j=0;j<opponents.size();j++){
                if(j==1){
                    lblOpponents2.setText(opponents.get(j)+" with "+opponentsGods.get(j));
                    rect2.setFill(Color.valueOf(opponentsColors.get(j)));
                    break;
                }
                rect1.setFill(Color.valueOf(opponentsColors.get(j)));
                lblOpponents1.setText(opponents.get(j)+" with "+opponentsGods.get(j));
            }

        });
    }


    /*public void createBoard(){
        GUIController.getInstance().setScene(gridBoard.getScene(),"/fxml/BoardStage.fxml");
    }*/

}
