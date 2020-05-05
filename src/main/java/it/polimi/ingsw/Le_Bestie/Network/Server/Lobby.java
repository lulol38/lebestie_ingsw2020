package it.polimi.ingsw.Le_Bestie.Network.Server;

import java.util.ArrayList;

/**
 * Lobby includes the clients waiting for the beginning of the match
 * @author Luca Ferrari
 */

public class Lobby {

    private ArrayList<ClientHandler> clientsWaiting = new ArrayList<ClientHandler>();
    private int numPlayersMatch;
    private int loadedBoards;

    //Getters
    public int getNumPlayersMatch() {
        return numPlayersMatch;
    }
    public int getLoadedBoards() {
        return loadedBoards;
    }
    public ArrayList<ClientHandler> getClientsWaiting() {
        return clientsWaiting;
    }

    //Setters
    public void setNumPlayersMatch(int numPlayersMatch) {
        this.numPlayersMatch = numPlayersMatch;
    }
    public void setLoadedBoards(int loadedBoards) {
        this.loadedBoards = loadedBoards;
    }

    /**
     *This methods adds a client to the lobby
     * @param s is the client to be added
     */
    public void addClientToLobby(ClientHandler s){
        clientsWaiting.add(s);
    }

    /**
     * This method cleans the lobby
     */
    public void cleanLobby(){
        clientsWaiting.clear();
    }

    /**
     * This method returns a client in the lobby by looking for his username
     * @param username is the username used for the research
     * @return
     */
    public ClientHandler getClientHandlerFromUsername(String username){
        for (ClientHandler c: clientsWaiting) {
            if(c.getUsername()==username) return c;
        }
        return null;
    }
}
