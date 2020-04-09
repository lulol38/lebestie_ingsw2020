package it.polimi.ingsw.Le_Bestie.Network.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClientSocket {

    private String ip;
    private int port;

    public ClientSocket(String ip, int port){
        this.ip=ip;
        this.port=port;
    }

    public void startClient() throws IOException {
        Socket socket = new Socket(ip, port);
        System.out.println("Connection established");

        Scanner socketIn = new Scanner(socket.getInputStream());
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        Scanner stdin = new Scanner(System. in);

        try{
            while(true){
                //Here the client listens the messages sent from the server or sends messages

                /*String inputLine=stdin.nextLine();
                socketOut.println(inputLine);
                socketOut.flush();
                String socketLine=socketIn.nextLine();
                System.out.println(socketLine);*/
            }
        }
        catch(NoSuchElementException ex){
            System.out.println("Connection closed");
        }
        finally{
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }

    public static void main(String[] args){

        //Here will start the GUI

    }
}
