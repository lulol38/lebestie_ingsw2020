package it.polimi.ingsw.Le_Bestie.View;

import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Gui extends Application {
    @Override
    public void start(Stage stage) {
        javafx.application.Platform.runLater(new Runnable() {
            public void run() {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.getIcons().add(new Image(Gui.class.getClassLoader().getResourceAsStream("images/icon.png")));
                Scene scene = new Scene(root);
                stage.setTitle("SANTORINI MENU");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent we) {
                        if (Client.getInstance() != null)
                            Client.getInstance().sendMessage(new CloseConnection());
                        System.exit(0);
                    }
                });
            }
        });
    }
}
