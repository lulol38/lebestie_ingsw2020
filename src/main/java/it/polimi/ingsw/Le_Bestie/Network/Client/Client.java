package it.polimi.ingsw.Le_Bestie.Network.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
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
    private String nickname;

    public Client(String ip, int port){
        this.ip=ip;
        this.port=port;
    }

    public void run() {

    }

    public static void main(String[] args){

        //Here will start the GUI

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
