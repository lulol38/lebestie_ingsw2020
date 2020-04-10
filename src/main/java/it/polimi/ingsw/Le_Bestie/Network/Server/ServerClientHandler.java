package it.polimi.ingsw.Le_Bestie.Network.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class ServerClientHandler
 * server launches thread foreach client
 * @author Luca Ferrari
 */
public class ServerClientHandler implements Runnable{

    private int numPlayers=0;
    private int maxNum;
    private ArrayList<String> nicknames=new ArrayList<String>();

    private Socket socket;

    public ServerClientHandler(Socket socket){
        this.socket=socket;
    }

    public void run(){
        try{
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (true) {
                String line = in.nextLine();
                if (line.equals("quit"))
                    break;
                else {

                    //CASE of the message received
                    switch(line) {
                        case "abc":
                    }





                }
            }
            out.println("quit");
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

}
