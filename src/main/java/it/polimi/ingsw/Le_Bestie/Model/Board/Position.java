package it.polimi.ingsw.Le_Bestie.Model.Board;

import java.io.Serializable;

public class Position implements Serializable {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Setters

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Checking if this location is on a 5 x 5 Grid or out of bounds
    public boolean onGrid() {
        return (this.x >= 0 && this.x <= 4 && this.y >= 0 && this.y <= 4);
    }

    public String toString() {
        return "Y  " + this.y + ",X  " + this.x;
    }
}
