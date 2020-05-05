package it.polimi.ingsw.Le_Bestie.Network;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Network.Messages.C2S.*;
import it.polimi.ingsw.Le_Bestie.Network.Messages.MessageVisitorServer;
import it.polimi.ingsw.Le_Bestie.Network.Messages.S2C.*;
import it.polimi.ingsw.Le_Bestie.Network.Server.ClientHandler;
import it.polimi.ingsw.Le_Bestie.Network.Server.Server;

public class MessageParserServer implements MessageVisitorServer {

    private Object obj;

    public MessageParserServer(Object obj){
        this.obj= obj;
    }

    @Override
    public void visit(SendNumPlayers mex) {
        ClientHandler clientSender= (ClientHandler) obj;
        clientSender.getServer().getLobby().setNumPlayersMatch(mex.getNumPlayers());
        clientSender.getServer().getLobby().addClientToLobby(clientSender);
        clientSender.getServer().getClientsWaiting().remove(clientSender);
        System.out.println("Setted match num players to: " + Server.getInstance().getLobby().getNumPlayersMatch());
        clientSender.getServer().manageWaiting(clientSender);
    }

    @Override
    public void visit(CloseConnection mex) {
        ClientHandler clientSender = ((ClientHandler) obj);
        clientSender.closeConnection();
    }

    @Override
    public void visit(SendUsername mex) {
        ClientHandler clientSender = ((ClientHandler) obj);
        if(clientSender.getServer().checkUsername(mex.getUsername())) {
            clientSender.setUsername(mex.getUsername());
            clientSender.getServer().getClientsWaiting().add(clientSender);
            clientSender.getServer().manageWaiting(clientSender);
            //clientSender.getServer().addWaitingClient(clientSender, clientSender.getSocket());
        }
        else
            clientSender.sendMessage(new ErrorUsername());
    }

    @Override
    public void visit(SendBoardLoaded mex) {
        Server.getInstance().checkLoadingBoards(mex.getNumGame());
    }

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

    @Override
    public void visit(SendBuilderChosen mex) {
        Server.getInstance().getGame(mex.getIdGame()).checkBuilder(mex.getBx(), mex.getBy());
    }

    @Override
    public void visit(SendCellChosen mex) {
        Server.getInstance().getGame(mex.getIdGame()).requestAction(mex.getCx(), mex.getCy());
    }

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
