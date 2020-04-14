package it.polimi.ingsw.Le_Bestie.Model.Builder;
/**
 * 
 */
public enum BuilderColor {
    WHITE, LIGHT, BLUE, BROWN;

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