package it.polimi.ingsw.Le_Bestie.Network.Server;

import com.sun.security.ntlm.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocket implements Runnable{

    private Socket socket;
    private String ip;
    private int port;

    public ServerSocket(String ip, int port){
        try {
            this.socket = new Socket(ip, port);
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
            return;
        }
    }

    public void run(){
        try{
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while(true){

                //Here the server listens the messages sent from the client or sends messages

                String line=in.nextLine();
                if(line.equals("quit")) break;
                else{
                    System.out.println("Received: " + line);
                    out.println("Received: " + line);
                    out.flush();
                }
            }

            in.close();
            out.close();
            socket.close();
            System.out.println("Server Closed");
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }

    public static void main(String[] args){

        //Here will start the server

        ServerSocket s = new ServerSocket("xxxxxx", 1345);
    }
}
