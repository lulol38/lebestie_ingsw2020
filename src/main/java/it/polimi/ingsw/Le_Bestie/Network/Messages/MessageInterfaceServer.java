package it.polimi.ingsw.Le_Bestie.Network.Messages;

/**
 * Visitor pattern
 * @author Luca Ferrari
 */

public interface MessageInterfaceServer {

    /**
     * Visitor pattern
     * @param mex
     */
    void receive(MessageVisitorServer mex);
}
