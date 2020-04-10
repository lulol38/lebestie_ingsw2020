package it.polimi.ingsw.Le_Bestie.Controller;

import it.polimi.ingsw.Le_Bestie.Model.Game.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.Network.Server.Lobby;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {

    private Lobby lobby;

    private MatchState matchState;
    private ArrayList<Player> players;
    private ArrayList<Player> listPlayersRound;

    public GameController(Lobby lobby) throws IOException {
        this.lobby=lobby;
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

    public Player addPlayer(String username){
        Player p= new Player(username);

        players.add(p);

        return p;
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
}
