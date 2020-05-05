package it.polimi.ingsw.Le_Bestie.Network.Messages;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.*;
import it.polimi.ingsw.Le_Bestie.Network.Server.ClientHandler;
import it.polimi.ingsw.Le_Bestie.Network.Server.Server;

/**
 * Visitor pattern
 * For each received message, here are described the actions that the server has to do
 * @author Luca Ferrari
 */

public class MessageParserServer implements MessageVisitorServer {

    private Object obj;

    public MessageParserServer(Object obj){
        this.obj= obj;
    }

    /**
     * Sevrer sets the number of players fot the match sent from the client and inserts him in the lobby as first player
     */
    @Override
    public void visit(SendNumPlayers mex) {
        ClientHandler clientSender= (ClientHandler) obj;
        clientSender.getServer().getLobby().setNumPlayersMatch(mex.getNumPlayers());
        clientSender.getServer().getLobby().addClientToLobby(clientSender);
        clientSender.getServer().getClientsWaiting().remove(clientSender);
        System.out.println("Setted match num players to: " + Server.getInstance().getLobby().getNumPlayersMatch());
        clientSender.getServer().manageWaiting(clientSender);
    }

    /**
     * Server closes the connection with the client that has sent the message
     */
    @Override
    public void visit(CloseConnection mex) {
        ClientHandler clientSender = ((ClientHandler) obj);
        clientSender.closeConnection();
    }

    /**
     * Server sets the username of the client before checking if it's already taken or not and inserts him in the lobby
     */
    @Override
    public void visit(SendUsername mex) {
        ClientHandler clientSender = ((ClientHandler) obj);
        if(clientSender.getServer().checkUsername(mex.getUsername())) {
            clientSender.setUsername(mex.getUsername());
            clientSender.getServer().getClientsWaiting().add(clientSender);
            clientSender.getServer().manageWaiting(clientSender);
        }
        else
            clientSender.sendMessage(new ErrorUsername());
    }

    /**
     * Server increases the number of boards that have been loaded
     */
    @Override
    public void visit(SendBoardLoaded mex) {
        Server.getInstance().checkLoadingBoards(mex.getNumGame());
    }

    /**
     * Server sets the initial position of the builders on the board, before the game starts, and updates the clients
     */
    @Override
    public void visit(SendBuilderPositions mex) {
        ClientHandler clientSender = ((ClientHandler) obj);
        if(Server.getInstance().getGame(mex.getIdGame()).setPlayerBuilder(mex.getPosX(), mex.getPosY())==2){
            Board b=Server.getInstance().getGame(mex.getIdGame()).getMatchState().getBoard();
            for (ClientHandler c : Server.getInstance().getGame(mex.getIdGame()).getLobby().getClientsWaiting()) {
                c.sendMessage(new SendUpdatedBoard(b));
            }
            clientSender.sendMessage(new AcceptedSetupBuilder());
            Server.getInstance().getGame(mex.getIdGame()).nextTurn();
        }
        else{
            Board b=Server.getInstance().getGame(mex.getIdGame()).getMatchState().getBoard();
            for (ClientHandler c : Server.getInstance().getGame(mex.getIdGame()).getLobby().getClientsWaiting()) {
                c.sendMessage(new SendUpdatedBoard(b));
            }
            clientSender.sendMessage(new AskPositionBuilders());
        }
    }

    /**
     * Server checks if the builder chosen for an action is usable
     */
    @Override
    public void visit(SendBuilderChosen mex) {
        Server.getInstance().getGame(mex.getIdGame()).checkBuilder(mex.getBx(), mex.getBy());
    }

    /**
     * Server checks if the cell chosen for an action is usable
     */
    @Override
    public void visit(SendCellChosen mex) {
        Server.getInstance().getGame(mex.getIdGame()).requestAction(mex.getCx(), mex.getCy());
    }

    /**
     * Server continues with an action (move or build) without using the power and updates the clients
     */
    @Override
    public void visit(SendPowerNotUsed mex) {
        ClientHandler c = (ClientHandler) obj;
        if(!Server.getInstance().getGame(mex.getIdGame()).getMatchState().getHasMoved()){
            Server.getInstance().getGame(mex.getIdGame()).getMatchState().setHasMoved(true);
            //Update clients
            for (ClientHandler client : Server.getInstance().getGame(mex.getIdGame()).getLobby().getClientsWaiting()) {
                client.sendMessage(new SendUpdatedBoard(Server.getInstance().getGame(mex.getIdGame()).getMatchState().getBoard()));
            }
            c.sendMessage(new AskCell());
        }
        else{
            Server.getInstance().getGame(mex.getIdGame()).getMatchState().setUsePower(true);
            Server.getInstance().getGame(mex.getIdGame()).requestAction(mex.getCx(), mex.getCy());
        }
    }

    /**
     * Server continues with an action (move or build) using the power and updates the clients
     */
    @Override
    public void visit(SendCellWithPower mex) {
        Server.getInstance().getGame(mex.getIdGame()).getMatchState().setUsePower(mex.isPower());
        Server.getInstance().getGame(mex.getIdGame()).requestAction(mex.getCx(), mex.getCy());
        //Update clients
        for (ClientHandler client : Server.getInstance().getGame(mex.getIdGame()).getLobby().getClientsWaiting()) {
            client.sendMessage(new SendUpdatedBoard(Server.getInstance().getGame(mex.getIdGame()).getMatchState().getBoard()));
        }
    }
}
