package it.polimi.ingsw.Le_Bestie.Network.Client;

import it.polimi.ingsw.Le_Bestie.View.GUI;
import javafx.application.Application;

/**
 * Main Client
 * @author Davide Carini
 */
public class MainClient {

    /**
     * Starting the Game GUI
     * @param args
     */
    public static void main(String[] args )
    {
        System.out.println("Starting GUI...");
        Application.launch(GUI.class);
    }
}
