package it.polimi.ingsw.Le_Bestie.Controller;

<<<<<<< HEAD
import it.polimi.ingsw.Le_Bestie.Model.Game.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import java.io.IOException;
import java.util.ArrayList;

public class GameController {

    private MatchState matchState;
    private ArrayList<Player> players;
    private ArrayList<Player> listPlayersRound;

    public GameController() throws IOException {

        this.matchState=new MatchState();
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    private boolean searchUser(String user){
        for(int i=0;i<this.players.size();i++)
        {
            if(players.get(i).getNickname().equalsIgnoreCase(user))
                return true;
        }
        return false;
    }

    public void startGame(){
        matchState();
    }

    public MatchState getMatchState() {
        return matchState;
    }

    public void matchState(){
        updatePlayersInRound(listPlayersRound);
    }

    private void setPlayersInRound() {
        listPlayersRound.addAll(players);
    }

    private void updatePlayersInRound(ArrayList<Player> players) {
        try {
            Player exPlayerInRound = players.get(0);
            players.remove(0);
            players.add( players.size(), exPlayerInRound);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException");
        }
    }

=======
import it.polimi.ingsw.Le_Bestie.Network.Server.Lobby;

public class GameController {

    private Lobby lobby;

    public GameController(Lobby lobby){
        this.lobby=lobby;
    }
>>>>>>> origin/master
}
