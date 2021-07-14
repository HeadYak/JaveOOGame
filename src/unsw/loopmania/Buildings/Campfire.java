package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.Character;

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

