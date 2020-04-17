package it.polimi.ingsw.Le_Bestie;

import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.CloseConnection;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * *SANTORINI GAME*
 */
public class App extends Application {
    public void start(Stage stage) {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/fxml/Menu2.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //stage.getIcons().add(new Image(App.class.getClassLoader().getResourceAsStream("icon.png")));
                Scene scene = new Scene(root);
                stage.setTitle("Santorini Menu");
                stage.setScene(scene);
                stage.show();

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent we) {
                        if(Client.getInstance()!=null)
                            Client.getInstance().sendMessage(new CloseConnection());
                        System.exit(0);
                    }
                });
            }
        });

    }

    public static void main( String[] args )
    {
        System.out.println("SANTORINI");
        launch(args);
    }
}
