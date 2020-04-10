package it.polimi.ingsw.Le_Bestie.Network.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class ClientSocket
 * client connection with server using socket
 * @author Luca Ferrari
 */
public class Client implements Serializable {

    private String ip;
    private int port;
    private String nickname;

    public Client(String ip, int port){
        this.ip=ip;
        this.port=port;
    }

    public void connectToServer() throws IOException {
        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");

        Scanner socketIn = new Scanner(socket.getInputStream());
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());

        try{
            while(true){

                //Take the nickname from the GUI
                socketOut.println("LOGIN_"+nickname); //Handshake with server
                socketOut.flush();

                String loginResponse=socketIn.nextLine(); //Response of the login
                if(loginResponse=="LOGIN_ACCEPTED"){
                    //Open GUI page of the game
                }
            }
        }
        catch(NoSuchElementException ex){
            socketOut.println("quit");
            System.out.println("Connection closed");
        }
        finally{
            socketOut.println("quit");
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }

    public static void main(String[] args){

        //Here will start the GUI

    }
}
