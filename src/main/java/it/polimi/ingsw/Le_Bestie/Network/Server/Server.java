package it.polimi.ingsw.Le_Bestie.Network.Server;

import it.polimi.ingsw.Le_Bestie.Controller.GameController;
import it.polimi.ingsw.Le_Bestie.Network.Messages.Message;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.AskNumPlayers;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.AskUsername;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.SendGameStart;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class ServerMultiSocket
 * server listens for requests
 * @author Luca Ferrari
 */
public class Server {

    public static Server instance;
    private int port;

    private ArrayList<ServerClientHandler> clientsConnected = new ArrayList<>();
    private ArrayList<GameController> activeGames = new ArrayList<>();
    private Lobby lobby;

    private ExecutorService executor = Executors.newCachedThreadPool();
    private ServerSocket serverSocket;

    public Server(int port){
        this.port=port;
        this.lobby= new Lobby();
        instance=this;
    }

    public static Server getInstance(){
        return instance;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public void startServer() {
        
        System.out.println("Server is starting...");
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e.getMessage()); //Port not available
            return;
        }
        System.out.println("Server ready");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ServerClientHandler client = new ServerClientHandler(socket);
                executor.submit(client);
                clientsConnected.add(client);
                System.out.println("Client " + client.getAddress() + " is connected to the server \n");
                client.sendMessage(new AskUsername());
            } catch (IOException e) { //Connection error
                break;
            }
        }
    }

    public void addWaitingClient(ServerClientHandler client, Socket soc){
        lobby.addClientToLobby(client);
        if(lobby.getClientsWaiting().size()==1){ //First player decides if 2 or 3 players
            try {
                client.sendMessage(new AskNumPlayers());
                System.out.println("Asking num players");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            if(lobby.getClientsWaiting().size()==lobby.getNumPlayersMatch()) {
                System.out.println("Starting game");
                for (ServerClientHandler s: lobby.getClientsWaiting()) {
                    s.sendMessage(new SendGameStart());
                }
                startMatch(lobby);
                lobby.cleanLobby();
            }
        }
    }

    public void startMatch(Lobby lobby){
        try{
            GameController game = new GameController(lobby);
            for (ServerClientHandler s: lobby.getClientsWaiting())
            {
//                s.setPlayer(game.getMatchState().addPlayer(s.getUsername()));
            }
//            activeGames.add(game);
            game.initGame();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public synchronized void deleteConnection(ServerClientHandler c) {
        clientsConnected.remove(c);

        if ( lobby.getClientsWaiting().contains(c) ) lobby.getClientsWaiting().remove(c);
    }
    
    public void closeServer(){
        executor.shutdown();
        try {
            serverSocket.close();
            for (ServerClientHandler connection: clientsConnected) {
                connection.closeConnection();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Server closed");
    }

    //This method controls if a username is already taken in the lobby
    public boolean checkUsername(String Username){
        for (ServerClientHandler s: lobby.getClientsWaiting()) {
            if(s.getUsername().compareTo(Username)==0)
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        Server s = new Server(45331);
        s.startServer();
    }
}
