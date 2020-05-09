package it.polimi.ingsw.Le_Bestie.Model;

import it.polimi.ingsw.Le_Bestie.Model.Board.Cell;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellTest {

    @Test
    public void CellTest(){
        Cell c = new Cell(2);
        assertNotNull(c);
        assertEquals(c.getLevel(), 2);
        c.setLevel(3);
        assertEquals(c.getLevel(), 3);
        c.setLevel(4);
        assertEquals(c.getLevel(), 4);
        assertEquals(c.isDisabled(), true);
    }

    @Test
    public void IncreaseLevel(){
        Cell c = new Cell(0);
        assertNotNull(c);
        c.addLevel();
        assertEquals(c.getLevel(), 1);
        c.addLevel();
        assertEquals(c.getLevel(), 2);
        c.addLevel();
        assertEquals(c.getLevel(), 3);
        c.addLevel();
        assertEquals(c.getLevel(), 4);
        assertEquals(c.isCompleteTower(), true);
        assertEquals(c.isDisabled(), true);
    }

    @Test
    public void IncreaseLevelBlocked(){
        Cell c = new Cell(0);
        assertNotNull(c);
        c.addLevel();
        assertEquals(c.getLevel(), 1);
        c.addLevel();
        assertEquals(c.getLevel(), 2);
        c.setLevel(0);
        assertEquals(c.getLevel(), 0);
        c.setLevel(3);
        assertEquals(c.getLevel(), 3);
        c.addLevel();
        assertEquals(c.getLevel(), 4);
        assertEquals(c.isCompleteTower(), true);
        assertEquals(c.isDisabled(), true);
    }
}
