package it.polimi.ingsw.Le_Bestie.Network.Server;

import it.polimi.ingsw.Le_Bestie.Controller.GameController;

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

    private int port;

    private ArrayList<ServerClientHandler> clientsConnected = new ArrayList<ServerClientHandler>();
    private ArrayList<GameController> activeGames = new ArrayList<GameController>();
    private Lobby lobby;

    private ExecutorService executor = Executors.newCachedThreadPool();
    private ServerSocket serverSocket;

    public Server(int port){
        this.port=port;
        this.lobby= new Lobby();
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
                clientsConnected.add(client);
                System.out.println("Client " + client.getAddress() + " is connected to the server \n");
                addWaitingClient(client, socket);
                executor.submit(client);
            } catch (IOException e) { //Connection error
                break;
            }
        }
    }


    //
    //
    //MODIFY WITH CORRECT MESSAGE SENT, NOT OUTPUT STREAM
    //
    //
    public void addWaitingClient(ServerClientHandler client, Socket soc){
        lobby.addClientToLobby(client);
        if(lobby.getClientsWaiting().size()==1){ //First player decides if 3 or 4 players
            try {
                Scanner in = new Scanner(soc.getInputStream());
                PrintWriter out = new PrintWriter(soc.getOutputStream());
                out.println("DEC23PLAYERS");
                lobby.setNumPlayersMatch(in.nextInt()); //Read from the client the number of players and sets to the lobby
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            if(lobby.getClientsWaiting().size()==lobby.getNumPlayersMatch()) {
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
                s.setPlayer(game.addPlayer(s.getUsername()));
            }
            activeGames.add(game);
            game.startGame();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
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

    public static void main(String[] args){
        Server s = new Server(1345);
        s.startServer();
    }
}
