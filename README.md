# lebestie_ingsw2020
Repository Prova Finale di Ingegneria del Software - Anno Accademico 2019/2020, Gruppo Le Bestie

PREPARAZIONE PARTITA
1)	Primo giocatore avvia la partita -> sceglie numero giocatori (numPlayer), viene istanziato il MatchState, inserisce nickname, viene inserito player in array 
2)	Attesa prox giocatore
3)	Secondo giocatore entra in partita e inserisce nickname, sceglie due caselle non occupate (necessaria verifica + istanziare due builder + metti isOccupied true) e viene inserito nell’array ->  addPlayer
4)	Attesa prox giocatore
5)	Terzo giocatore (uguale)
6)	Viene assegnata una carta ad ogni giocatore PlayerModel (con assignCard pesca casualmente in deck -> da modificare se numPlayer=3 con removeCard3Players) e viene istanziata la corrispondente GodCard da getGod. La carta pescata si elimina dal deck con RemoveCard

INIZIO PARTITA
1)	nextPlayer fa partire primo giocatore, lo chiama e si attiva la sua carta
2)	TURNO*
3)	Quando ha finito la carta chiama nextPlayer

CONDIZIONI FINE PARTITA 
Ad ogni turno devo controllare che: 
•	Un giocatore vince (hasWon -> se true chiama endMatch)
•	Se l’array di giocatori rimane con un elemento 

TURNO*
1)	Controllo se ho perso (se per entrambi i builder sono disabled OR PossibleMoves=0)  
1-1)	se ho perso chiamo cleanAndRemovePlayer, mi elimino dall’array di giocatori e chiamo nextPlayer
1-2)	se un solo builder è disabled, obbligo il giocatore a muovere l’altro
1-3)	altrimenti faccio scegliere al giocatore quale vuole muovere
2)	Il giocatore sceglie la casella in cui spostarsi
3)	Verifico che sia in possibleMoves del builder
4)	Cambio cella al builder
5)	hasMoved true
6)	Il giocatore sceglie la casella in cui costruire
7)	Verifico che sia in possibleBuilds del builder
8)	Cambio il levelBuilded della cella
9)	hasBuild true
