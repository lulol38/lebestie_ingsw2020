package it.polimi.ingsw.Le_Bestie.Network.Server;

import it.polimi.ingsw.Le_Bestie.Model.Player.Player;
import it.polimi.ingsw.Le_Bestie.Network.Messages.Message;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageParser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Class ServerClientHandler
 * server launches thread foreach client
 * @author Luca Ferrari
 */
public class ClientHandler implements Runnable {

    private Player player;

    private String username;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Socket socket;

    private boolean connected;

    public ClientHandler(Socket socket){
        this.socket=socket;
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.connected=true;
    }

    public void run(){
        try{
            while(connected) {
                receiveMessage();
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getAddress(){
        return socket.getInetAddress().toString();
    }

    public Socket getSocket() {
        return socket;
    }

    public void receiveMessage(){
        try {
            Message mex= (Message) in.readObject();
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

    public synchronized void closeConnection(){
        try{
            Server.getInstance().deleteConnection(this);
            System.out.println("Closing: "+ socket.toString());
            socket.close();
            this.connected=false;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage().toString());
        }
    }
}
