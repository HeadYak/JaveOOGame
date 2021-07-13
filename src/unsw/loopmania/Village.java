package unsw.loopmania;

import javafx.beans.property.SimpleIntegerProperty;

public class Village extends Building{
    public Village(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void heal(Character character) {
        character.regen(20);
    }
}
