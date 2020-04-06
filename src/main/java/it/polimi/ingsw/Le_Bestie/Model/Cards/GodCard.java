package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;

import java.util.*;

public interface GodCard {

    boolean move(Builder w, Cell c);

    boolean build(Builder w, Cell c);

    boolean HasWon();

    boolean HasLost();

}