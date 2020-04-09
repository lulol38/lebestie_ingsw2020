package it.polimi.ingsw.Le_Bestie;

import it.polimi.ingsw.Le_Bestie.Controller.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import java.io.IOException;

/**
 *
 * ******SANTORINI GAME******
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println("SANTORINI");

        MatchState m =new MatchState();
        m.setNumPlayers(3);
        Player p1 =new Player("davide");
        Player p2 =new Player("shish");
        Player p3 =new Player("zeb");
        Player p4 =new Player("davide");
        Player p5 =new Player("shish");
        Player p6 =new Player("zeb");
        Player p7 =new Player("davide");
        Player p8 =new Player("shish");
        Player p9 =new Player("zeb");


        m.getDeck().assignCard(p1);
        System.out.println(p1.toString());
        m.getDeck().assignCard(p2);
        System.out.println(p2.toString());
        m.getDeck().assignCard(p3);
        System.out.println(p3.toString());
        m.getDeck().assignCard(p4);
        System.out.println(p4.toString());
        m.getDeck().assignCard(p5);
        System.out.println(p5.toString());
        m.getDeck().assignCard(p6);
        System.out.println(p6.toString());
        m.getDeck().assignCard(p7);
        System.out.println(p7.toString());



    }
}
