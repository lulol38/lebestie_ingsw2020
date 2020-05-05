package it.polimi.ingsw.Le_Bestie.Network.Client;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageClient;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageParserClient;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageServer;

import java.io.*;
import java.net.Socket;
/**
 * Class ClientSocket
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

    public Client(String ip, int port, String username) {

        this.ip=ip;
        this.port=port;
        this.username=username;

        instance=this;
    }

    public boolean init() {
        try {
            //Create the connection with the server
            this.socket = new Socket(ip, port);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    public void run() {
        while (true) {
            try {
                receiveMessage();
            }
            catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public static Client getInstance() {
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

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

    public void sendMessage(MessageClient message){
        try {
            out.reset();
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws IOException {
        this.in.close();
        this.out.close();
        this.socket.close();
    }
}
