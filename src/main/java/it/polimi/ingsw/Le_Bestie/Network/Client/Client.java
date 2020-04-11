package it.polimi.ingsw.Le_Bestie.Network.Client;

import it.polimi.ingsw.Le_Bestie.Network.Messages.Message;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageParser;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Class ClientSocket
 * client connection with server using socket
 * @author Luca Ferrari
 */
public class Client implements Runnable, Observer {

    private String ip;
    private int port;
    private Socket socket;
    private String nickname;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Client(String ip, int port){
        this.ip=ip;
        this.port=port;
        try {
            this.socket = new Socket(ip, port);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(true){
            try{
                receiveMessage();
            }
            catch(Exception ex){
                System.out.println(ex.getMessage().toString());
            }
        }
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

    @Override
    public void update(Observable observable, Object o) {

    }

    public static void main(String[] args){

        //Here will start the GUI

    }
}
