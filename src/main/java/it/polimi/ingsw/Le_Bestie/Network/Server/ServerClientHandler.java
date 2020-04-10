package it.polimi.ingsw.Le_Bestie.Network.Server;

import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

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
public class ServerClientHandler implements Runnable, Observer {

    private Player player;

    private String username;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    private Socket socket;

    public ServerClientHandler(Socket socket){
        this.socket=socket;
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try{

        }
        catch(IOException ex)
        {
            System.err.println(ex.getMessage());
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

    public void closeConnection(){
        try{
            socket.close();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage().toString());
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        //
    }
}
