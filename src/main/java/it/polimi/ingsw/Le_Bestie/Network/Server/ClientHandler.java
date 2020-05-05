package it.polimi.ingsw.Le_Bestie.Network.Server;

import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageParserServer;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageClient;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class ClientHandler
 * server launches a thread for each client to listen and to send messages
 * @author Luca Ferrari
 */
public class ClientHandler implements Runnable {

    private String username;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    private boolean connected;
    private Server server;

    public ClientHandler(Socket socket, Server server){
        this.socket=socket;
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.connected=true;
        this.server=server;
    }

    //Getters
    public Server getServer() {
        return server;
    }
    public String getUsername() {
        return username;
    }
    public String getAddress(){ return socket.getInetAddress().toString(); }
    public Socket getSocket() {
        return socket;
    }

    //Setter
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Method that listens for messages while the server is connected to the client
     */
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

    /**
     * Method that handles when a message is received, and sends it to the parser
     */
    public void receiveMessage(){
        try {
            MessageClient mex= (MessageClient) in.readObject();
            mex.receive(new MessageParserServer(this));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            closeConnection();
        }
    }

    /**
     * Method that sends the message to the client
     * @param message It's the message that has to be sent to the client
     */
    public void sendMessage(MessageServer message){
        try {
            out.reset();
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that closes the client connection to the client
     */
    public synchronized void closeConnection(){
        try{
            Server.getInstance().deleteConnection(this);
            System.out.println("Closing: "+ socket.toString());
            socket.close();
            this.connected=false;
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
