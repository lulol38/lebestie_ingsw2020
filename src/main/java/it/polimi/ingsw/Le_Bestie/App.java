package it.polimi.ingsw.Le_Bestie;

import it.polimi.ingsw.Le_Bestie.Model.Board.Position;
import it.polimi.ingsw.Le_Bestie.Model.Game.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.io.IOException;
import java.util.ArrayList;

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

        Player p1=new Player("davide");
        ArrayList<Position>c=new ArrayList<>();
        p1.getBuilder1().setCell(0,2,3);

        c.addAll(p1.getBuilder1().possibleMoves());

        for(int i=0;i<c.size();i++) {

        }


    }
}
