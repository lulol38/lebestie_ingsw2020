package it.polimi.ingsw.Le_Bestie.Network.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMultiSocket{

    private int port;

    public ServerMultiSocket(int port){
        this.port=port;
    }

    public void startServer() {
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(port);
        }
        catch (IOException e) {
            System.err.println(e.getMessage()); //Port not available
            return;
        }
        System.out.println("Server ready");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new ServerClientHandler(socket));
            } catch (IOException e) {
                break; //serverSocket closed
            }
        }
        executor.shutdown();
        try {
            serverSocket.close();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Server closed");
    }

    public static void main(String[] args){

        //Here will start the server

        ServerMultiSocket s = new ServerMultiSocket(1345);
        s.startServer();
    }
}
