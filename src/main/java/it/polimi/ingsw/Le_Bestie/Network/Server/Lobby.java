package it.polimi.ingsw.Le_Bestie.Network.Server;

import java.util.ArrayList;

public class Lobby {

    private ArrayList<ClientHandler> clientsWaiting = new ArrayList<ClientHandler>();
    private int numPlayersMatch;
    private int loadedBoards;

    public int getNumPlayersMatch() {
        return numPlayersMatch;
    }
    public int getLoadedBoards() {
        return loadedBoards;
    }
    public ArrayList<ClientHandler> getClientsWaiting() {
        return clientsWaiting;
    }

    public void setNumPlayersMatch(int numPlayersMatch) {
        this.numPlayersMatch = numPlayersMatch;
    }
    public void setLoadedBoards(int loadedBoards) {
        this.loadedBoards = loadedBoards;
    }

    public void addClientToLobby(ClientHandler s){
        clientsWaiting.add(s);
    }
    public void cleanLobby(){
        clientsWaiting.clear();
    }

    public ClientHandler getClientHandlerFromUsername(String username){
        for (ClientHandler c: clientsWaiting) {
            if(c.getUsername()==username) return c;
        }
        return null;
    }
}
