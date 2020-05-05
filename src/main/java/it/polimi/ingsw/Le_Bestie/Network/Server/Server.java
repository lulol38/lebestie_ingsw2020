package it.polimi.ingsw.Le_Bestie.Network.Server;

import it.polimi.ingsw.Le_Bestie.Controller.GameController;
import it.polimi.ingsw.Le_Bestie.Network.Client.Client;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.SendUsername;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class Server
 * server listens for requests
 * @author Luca Ferrari
 */
public class Server {

    private int port;
    public static Server instance;
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clientsConnected = new ArrayList<>();
    private ArrayList<ClientHandler>clientsWaiting=new ArrayList<>();
    private static ArrayList<GameController> activeGames = new ArrayList<>();
    private Lobby lobby;
    private ExecutorService executor = Executors.newCachedThreadPool();
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    final java.util.Random rand = new java.util.Random();

    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    final Set<String> identifiers = new HashSet<String>();

    public Server(int port){
        this.port=port;
        this.lobby= new Lobby();
        instance=this;
    }

    //Getters
    public static Server getInstance(){
        return instance;
    }
    public Lobby getLobby() {
        return lobby;
    }
    public ArrayList<ClientHandler>getClientsWaiting(){
        return clientsWaiting;
    }

    //Setter
    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    /**
     * Setup and start listening and accepting connections on the port
     * When a client connects, launch a new thread, add him to the clients connected and ask his username
     */
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
                ClientHandler client = new ClientHandler(socket, this);
                executor.submit(client);
                clientsConnected.add(client);
                System.out.println("Client " + client.getAddress() + " is connected to the server \n");
                client.sendMessage(new AskUsername());
            } catch (IOException e) { //Connection error
                break;
            }
        }
    }

    /**
     * When a new client is connected handle if he is the first and in this case, ask gìhim the number of
     * players for the match, or add him to the lobby
     * When the lobby is full (size of the lobby == number of players of the match), create the game
     * @param client
     */
    public synchronized void manageWaiting(ClientHandler client){
        if(lobby.getClientsWaiting().size()==0){
            try {
                client.sendMessage(new AskNumPlayers());
                System.out.println("Asking num players");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            if(lobby.getNumPlayersMatch()!=0){
                for(int x=0; x<clientsWaiting.size();x++) {
                    if(clientsWaiting.get(x).getUsername().compareTo(lobby.getClientsWaiting().get(0).getUsername())==0)
                        clientsWaiting.get(x).setUsername(randomIdentifier());


                    lobby.addClientToLobby(clientsWaiting.get(x));
                    clientsWaiting.get(x).sendMessage(new OpenLobby());
                    clientsWaiting.remove(clientsWaiting.get(x));
                    if(lobby.getClientsWaiting().size()==lobby.getNumPlayersMatch())
                    {
                        System.out.println("Starting game");
                        Random rand = new Random();
                        int numGame=rand.nextInt(99999);
                        for (ClientHandler s: lobby.getClientsWaiting()) {
                            s.sendMessage(new SendGameStart(numGame));
                        }
                        Lobby lHelp= new Lobby();
                        for (ClientHandler c: lobby.getClientsWaiting()) {
                            lHelp.addClientToLobby(c);
                        }
                        lHelp.setNumPlayersMatch(lobby.getNumPlayersMatch());
                        GameController game = new GameController(lHelp, numGame);
                        activeGames.add(game);
                        return;
                    }
                }
            }
        }
    }

    /**
     * When all the clients boards are uploaded, send to the client the unique idGame of the match and start it
     * @param numGame
     */
    public void checkLoadingBoards(int numGame){
        GameController g = getGame(numGame);
        g.getLobby().setLoadedBoards(g.getLobby().getLoadedBoards()+1);
        if(g.getLobby().getLoadedBoards()==g.getLobby().getNumPlayersMatch()) {
            startMatch(getGame(numGame));
            lobby.cleanLobby();
            lobby.setNumPlayersMatch(0);
        }
    }

    /**
     * Start the match
     * @param game GameController of the match
     */
    public synchronized void startMatch(GameController game){
        try{
            game.initGame();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Delete a client connection
     * If he was playing in a match, end it and notificate it to the other clients
     * @param c
     */
    public synchronized void deleteConnection(ClientHandler c) {
        clientsConnected.remove(c);
        clientsWaiting.remove(c);
        if ( lobby.getClientsWaiting().contains(c) ) lobby.getClientsWaiting().remove(c);
        for(ClientHandler client:lobby.getClientsWaiting())
            client.sendMessage(new LostForDisconnection());
        for (GameController g: activeGames) {
            if(g.getLobby().getClientsWaiting().contains(c)){
                g.getLobby().getClientsWaiting().remove(c);
                for (ClientHandler client: g.getLobby().getClientsWaiting()) {
                    client.sendMessage(new LostForDisconnection());
                }
                activeGames.remove(g);
            }
        }
    }

    /**
     * This method controls if a username is already taken in the lobby
     */
    public boolean checkUsername(String Username){
        for (ClientHandler s: lobby.getClientsWaiting()) {
            if(s.getUsername().compareTo(Username)==0)
                return false;
        }
        return true;
    }

    /**
     * Get game controller from the unique idGame
     * @param numGame
     * @return
     */
    public GameController getGame(int numGame){
        for (GameController g: activeGames) {
            if(g.getIdGame()==numGame){
                return g;
            }
        }
        return null;
    }

    /**
     * Remove a game from the games list
     * @param game
     */
    public static void removeGame(GameController game){
        activeGames.remove(game);
        System.out.println("Closing game");
    }

    /**
     * Random username generated
     * @return string username
     */
    public String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

    /**
     * Start the server
     * @param args
     */
    public static void main(String[] args){
        Server multiEchoServer = new Server(45331);
        multiEchoServer.startServer();
    }
}
