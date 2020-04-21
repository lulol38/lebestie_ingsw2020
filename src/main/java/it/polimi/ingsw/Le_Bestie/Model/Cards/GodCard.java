package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Board.Board;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Player.Player;

import java.io.Serializable;

/**
 * Class GodCard
 * describes a generic turn without power
 * @author VeronicaRovelli
 */

public abstract class GodCard implements Serializable {

    private String name;
    protected boolean notMoveUp;

    public GodCard(String name){
        this.name=name;
    }
    public String getName() { return this.name; }

    public void setNotMoveUp(boolean notMoveUp) {
        this.notMoveUp=notMoveUp;
    }

    public boolean getNotMoveUp() {
        return notMoveUp;
    }

   /* return
    0 -> il builder non può muoversi nella cella richiesta
         **** scrivo all'utente "scegli un'altra cella"
         **** NON che non può muoversi in quella selezionata (vedi prometeo)
    1 -> il builder si è spostato nella cella desiderata(c)
    2 -> il player associato al builder ha vinto!!
    3 -> richiama la move SSE l'utente vuole usare potere (nuovi parametri da chiedere a utente)
    4 -> richiama la move passando stessi paramentri di prima ma usePower = !(risposta utente)
    ***
    ***ATTENZIONE: quando richiamo metodi move o build perchè la prima volta
    ***hanno avuto dei problemi(return 0 or 2) devo passare anche hasPower corretto
    */

    public int move(Board b, Builder w, Cell c,boolean usePower){
        Cell currentCell=b.getGrid()[w.getPosition().getX()][w.getPosition().getX()];
        if(w.possibleMoves(b,notMoveUp).contains(c))
        {
            //winner condition
            if(HasWon(c,currentCell))
                return 2;
            currentCell.setBuilder(null);
            c.setBuilder(w);
            w.setPosition(c.getPosition());
            return 1;
        }
        return 0;
    }

    /* return
    0 -> il builder non può costruire in quella cella (c)
    1 -> il builder ha costruito con successo
    2 -> il builder non ha costruito per mancanza di pezzi
    3 -> chiedo all'utente se vuole usare potere:
         se si: richiedo nuova cella e richiamo la build (usePower=FALSE)
         se no: richiamo la build senza richiedere cella (usePower=TRUE)
    4 -> richiama la build passando stessi paramentri di prima ma hasPower=!(risposta utente)
     */
    public int build(Board b,Builder w, Cell c, boolean usePower){
        if(w.possibleBuilds(b).contains(c))
        {
            if(b.getRemainingPieces(c.getLevel()+1)==0)
                return 2;
            c.addLevel();
            b.removePiece(c.getLevel());
            return 1;
        }
        return 0;
    }

    public boolean HasWon(Cell c,Cell currentCell) {

        return currentCell.getLevel()<c.getLevel()&&c.getLevel()==3;

    }

    //HasLost viene chiamato da matchstate prima la move e dopo la build
    public boolean HasLost(Player player,Board b){
        if(player.getBuilder1().possibleMoves(b,notMoveUp).size()==0)
            player.getBuilder1().setDisabled(true);
        else
            player.getBuilder1().setDisabled(false);

        if(player.getBuilder2().possibleMoves(b,notMoveUp).size()==0)
            player.getBuilder2().setDisabled(true);
        else
            player.getBuilder2().setDisabled(false);
        return player.getBuilder1().getDisabled() && player.getBuilder2().getDisabled();
    }

}

