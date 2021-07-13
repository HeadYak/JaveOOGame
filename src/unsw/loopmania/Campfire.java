package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Campfire extends Building{
    private int range;
    public Campfire(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        range = 1;
    }

    public int getRange() {
        return range;
    }

    public void buff(Character character) {
        character.activateBuff();
    }
}

