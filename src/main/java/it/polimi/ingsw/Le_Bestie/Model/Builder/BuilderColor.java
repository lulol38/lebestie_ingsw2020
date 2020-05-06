package it.polimi.ingsw.Le_Bestie.Model.Builder;

import java.io.Serializable;

/**
 * This class contains the possible colors that can identify a player
 */
public enum BuilderColor implements Serializable {
    WHITE, BLUE, BROWN;

    /**
     * Method that checks if the request color is a possible Builder's color
     *
     * @param colorBuilderChosen the color chosen
     * @return the request color if it is a possible Builder color, otherwise null
     */
    public static BuilderColor getColor(String colorBuilderChosen) {
        BuilderColor[] colors = values();
        for (BuilderColor color : colors) {
            if (color.name().equalsIgnoreCase(colorBuilderChosen)) {
                return color;
            }
        }
        return null;
    }
}