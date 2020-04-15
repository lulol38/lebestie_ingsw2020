package it.polimi.ingsw.Le_Bestie;

import it.polimi.ingsw.Le_Bestie.Model.Builder.BuilderColor;
import it.polimi.ingsw.Le_Bestie.Model.Cards.Deck;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.jsonParser.GodCardsParser;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * ******SANTORINI GAME******
 */
public class App extends Application {
    public void start(Stage stage) throws IOException {
        javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/GUI/Menu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = new Scene(root);

                stage.setTitle("Menu");
                stage.setScene(scene);
                stage.show();

                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent we) {
                        System.exit(0);
                    }
                });
            }
        });

    }

    public static void main( String[] args ) throws IOException
    {
        launch(args);

        Deck d;
        d= GodCardsParser.parseCards(3);


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
        //System.out.println(p8.getGodCard().getName());
        //System.out.println(p9.getGodCard().getName());



        ArrayList<BuilderColor> unusedColorsList = new ArrayList<>();
        for (BuilderColor curColor : BuilderColor.values()) {

                unusedColorsList.add(curColor);

        }
        BuilderColor b1;
        p1.getBuilder1().setColor(BuilderColor.getColor("BLUE"));
        unusedColorsList.remove(p1.getBuilder1().getColor());
        for(BuilderColor bc : unusedColorsList)
            System.out.println(bc.toString());

        BuilderColor b;
        b= BuilderColor.getColor("WHITE");
        if(b!=null)
            System.out.println("Colore scelto OK");




    }
}
