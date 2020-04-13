package it.polimi.ingsw.Le_Bestie;

import it.polimi.ingsw.Le_Bestie.Model.Cards.Deck;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    {
        launch(args);

        Deck d;
        d=GodCardsParser.parseCards(3);


        Player p1=new Player("dabide");
        Player p2=new Player("dabide");
        Player p3=new Player("dabide");
        Player p4=new Player("dabide");
        Player p5=new Player("dabide");
        Player p6=new Player("dabide");
        Player p7=new Player("dabide");
        Player p8=new Player("dabide");
        Player p9=new Player("dabide");
        Player p10=new Player("dabide");
        Player p11=new Player("dabide");
        p1.setGodCard(d.draw());
        p2.setGodCard(d.draw());
        p3.setGodCard(d.draw());
        p4.setGodCard(d.draw());
        p5.setGodCard(d.draw());
        p6.setGodCard(d.draw());
        p7.setGodCard(d.draw());
        p8.setGodCard(d.draw());
        p9.setGodCard(d.draw());
        p10.setGodCard(d.draw());
        p11.setGodCard(d.draw());

        System.out.println(p1.getGodCard().getName());
        System.out.println(p2.getGodCard().getName());
        System.out.println(p3.getGodCard().getName());
        System.out.println(p4.getGodCard().getName());
        System.out.println(p5.getGodCard().getName());
        System.out.println(p6.getGodCard().getName());
        System.out.println(p7.getGodCard().getName());
        System.out.println(p8.getGodCard().getName());
        System.out.println(p9.getGodCard().getName());
    }
}
