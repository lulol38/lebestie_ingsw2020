package it.polimi.ingsw.Le_Bestie.Network.Server;

import it.polimi.ingsw.Le_Bestie.Controller.GameController;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.AskNumPlayers;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.AskUsername;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.SendGameStart;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class ServerMultiSocket
 * server listens for requests
 * @author Luca Ferrari
 */
public class Server {
    private int port;

    public static Server instance;

    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clientsConnected = new ArrayList<>();
    private ArrayList<GameController> activeGames = new ArrayList<>();
    private Lobby lobby;
    private ExecutorService executor = Executors.newCachedThreadPool();


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
            //Create the Server
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e.getMessage()); //Port not available
            return;
        }
        System.out.println("Server ready");
        //The server is listening at the port 45531
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ClientHandler client = new ClientHandler(socket);
                executor.submit(client);
                clientsConnected.add(client);
                System.out.println("Client " + client.getAddress() + " is connected to the server \n");
                client.sendMessage(new AskUsername());
            } catch (IOException e) { //Connection error
                break;
            }
        }
    }

    public void addWaitingClient(ClientHandler client, Socket soc){
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
                for (ClientHandler s: lobby.getClientsWaiting()) {
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
            for (ClientHandler s: lobby.getClientsWaiting())
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

    public synchronized void deleteConnection(ClientHandler c) {
        clientsConnected.remove(c);
        if ( lobby.getClientsWaiting().contains(c) ) lobby.getClientsWaiting().remove(c);
    }
    
    public void closeServer(){
        executor.shutdown();
        try {
            serverSocket.close();
            for (ClientHandler connection: clientsConnected) {
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
        for (ClientHandler s: lobby.getClientsWaiting()) {
            if(s.getUsername().compareTo(Username)==0)
                return false;
        }
        return true;
    }


    public static void main(String[] args){
        Server multiEchoServer = new Server(45331);
        //Start the Server
        multiEchoServer.startServer();
    }
}
