package it.polimi.ingsw.Le_Bestie.Network.Client;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageClient;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageParserClient;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageServer;

import java.io.*;
import java.net.Socket;
/**
 * Class Client
 * client connection with server using socket
 * @author Luca Ferrari
 */
public class Client implements Runnable {

    private static Client instance;

    private String ip;
    private int port;

    private Socket socket;
    private String username;
    private int numPlayers;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    private int idGame;

    private boolean connected;

    public Client(String ip, int port, String username) {
        this.ip=ip;
        this.port=port;
        this.username=username;
        instance=this;
    }

    //Getters
    public static Client getInstance() {
        return instance;
    }
    public String getUsername() {
        return username;
    }
    public int getNumPlayers() {
        return numPlayers;
    }
    public int getIdGame() {
        return idGame;
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    /**
     * This method initializes the socket connection to the server and the in/out streams
     * @return if all has gone well returns true, instead false
     */
    public boolean init() {
        try {
            //Create the connection with the server
            this.socket = new Socket(ip, port);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            this.connected=true;
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    /**
     * Method that listens for messages while the client is connected
     */
    public void run() {
        while (connected) {
            try {
                receiveMessage();
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Method that handles when a message is received, and sends it to the parser
     */
    public void receiveMessage() {
        try {
            MessageServer mex = (MessageServer) in.readObject();
            mex.receive(new MessageParserClient(this));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that sends the message to the server
     * @param message It's the message that has to be sent to the server
     */
    public void sendMessage(MessageClient message){
        try {
            out.reset();
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that closes the client connection to the server
     * @throws IOException
     */
    public void closeConnection() throws IOException {
        this.connected=false;
        this.in.close();
        this.out.close();
        this.socket.close();
    }
}
