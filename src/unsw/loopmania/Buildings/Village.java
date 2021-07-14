package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.Character;

public class Village extends Building{
    public Village(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void heal(Character character) {
        character.regen(20);
    }
}
