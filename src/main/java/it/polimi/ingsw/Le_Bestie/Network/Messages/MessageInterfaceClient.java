package it.polimi.ingsw.Le_Bestie.Network.Messages;

/**
 * Visitor Pattern
 * @author Luca Ferrari
 */

public interface MessageInterfaceClient {

    /**
     * Visitor pattern
     * @param mex
     */
    void receive(MessageVisitorClient mex);
}
