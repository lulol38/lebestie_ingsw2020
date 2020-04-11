package it.polimi.ingsw.Le_Bestie.Model.Cards;

/**
 * Class Apollo
 * your Builder may move into an opponent Builder's
 * space by forcing their Builder to the space
 * yours just vacated
 * @VeronicaRovelli
 */

public class Apollo extends GodCard {

    public Apollo(String name) {
        super(name);
    }

  /*  @Override
    public boolean move(Builder w, Cell c) {
        if (w.possibleMoves().contains(c))
            return super.move(w, c);

        if (!MatchState.getHasMoved() && w.possibleSwitch().contains(c)) {
            //winner condition
            if (c.getLevel() == 3)
                HasWon();

            //change cell to the opponent Builder
            w.getCell().setBuilder(c.getBuilder());
            c.getBuilder().setCell(w.getCell());

            //change my cell
            w.setCell(c);
            c.setBuilder(w);

            MatchState.setHasMoved(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean build(Builder w, Cell c) {
        return super.build(w, c);
    }

    @Override
    public boolean HasWon() {
        return super.HasWon();
    }

    @Override
    public boolean HasLost(Player player) {
        if (player.getBuilder2().possibleSwitch().size() == 0)
            player.getBuilder2().setDisabled(true);
        if (player.getBuilder1().possibleSwitch().size() == 0)
            player.getBuilder1().setDisabled(true);

        return super.HasLost(player);
    }*/
}