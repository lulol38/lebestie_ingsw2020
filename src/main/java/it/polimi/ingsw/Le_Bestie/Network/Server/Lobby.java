package it.polimi.ingsw.Le_Bestie.Network.Server;

import java.util.ArrayList;

public class Lobby {

    private ArrayList<ServerClientHandler> clientsWaiting = new ArrayList<ServerClientHandler>();
    private int numPlayersMatch;

    public Lobby(){

    }

    public int getNumPlayersMatch() {
        return numPlayersMatch;
    }

    public void setNumPlayersMatch(int numPlayersMatch) {
        this.numPlayersMatch = numPlayersMatch;
    }

    public ArrayList<ServerClientHandler> getClientsWaiting() {
        return clientsWaiting;
    }

    public void addClientToLobby(ServerClientHandler s){
        clientsWaiting.add(s);
    }

    public void cleanLobby(){
        clientsWaiting.clear();
    }
}
