package it.polimi.ingsw.Le_Bestie.Network.Client;

import it.polimi.ingsw.Le_Bestie.Network.Messages.Message;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageParser;

import java.io.*;
import java.net.Socket;

/**
 * Class ClientSocket
 * client connection with server using socket
 * @author Luca Ferrari
 */
public class Client implements Runnable {

    public static Client instance;

    private String ip;
    private int port;
    private Socket socket;
    private String username;
    private int numPlayers;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Client(String ip, int port, String Username){
        this.ip=ip;
        this.port=port;
        this.username=username;
        try {
            this.socket = new Socket(ip, port);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        instance=this;
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

    public void receiveMessage() {
        try {
            Message mex = (Message) in.readObject();
            mex.receive(new MessageParser(this));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message){
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
