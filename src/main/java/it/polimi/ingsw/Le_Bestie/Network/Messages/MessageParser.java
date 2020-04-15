package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendNumPlayers;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.AskNumPlayers;
import it.polimi.ingsw.Le_Bestie.Network.Server.Server;
import it.polimi.ingsw.Le_Bestie.Network.Server.ServerClientHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class MessageParser implements MessageVisitor {

    private Object obj;

    public MessageParser(){}

    public MessageParser(Object obj){
        this.obj= obj;
    }

    //Ask to the client the number of players for the match
    @Override
    public void visit(AskNumPlayers mex){
        try {
            Stage stage= new Stage();
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/GUI/NumPlayers.fxml"));
            Scene scene = new Scene(root);

            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(SendNumPlayers mex) {
        Server.instance.getLobby().setNumPlayersMatch(mex.getNumPlayers());
    }

    @Override
    public void visit(CloseConnection mex) {
        ServerClientHandler client = ((ServerClientHandler) obj);
        client.closeConnection();
    }
}
