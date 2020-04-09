package it.polimi.ingsw.Le_Bestie.Model.Cards;

import it.polimi.ingsw.Le_Bestie.Controller.MatchState;
import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import it.polimi.ingsw.Le_Bestie.Model.Builder.Builder;

import java.util.*;

public class Minotaur extends GodCard{

    public Minotaur(String name) {
        super(name);
    }

    @Override
    public boolean move(Builder w, Cell c) {
        return false;
    }

    @Override
    public boolean build(Builder w, Cell c) {
        return false;
    }

    @Override
    public boolean HasWon() {
        return false;
    }

    @Override
    public boolean HasLost() {
        return false;
    }
}