package it.polimi.ingsw.Le_Bestie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * ******SANTORINI GAME******
 */
public class App extends Application {
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Menu.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main( String[] args ) throws IOException
    {/*
        System.out.println("___________________________________________________________________________________________________________");
        System.out.println("*****************   *****************");
        System.out.println("*                   *               *");
        System.out.println("*                   *               *");
        System.out.println("*                   *               *");
        System.out.println("*                   *               *");
        System.out.println("*                   *               *");
        System.out.println("*****************   *****************");
        System.out.println("                *   *               *");
        System.out.println("                *   *               *");
        System.out.println("                *   *               *");
        System.out.println("                *   *               *");
        System.out.println("                *   *               *");
        System.out.println("*****************   *               *");
        System.out.println("___________________________________________________________________________________________________________");*/
        launch(args);
    }
}
