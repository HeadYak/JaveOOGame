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

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public Boolean getIsSpawner() {
        return false;
    }

    @Override
    public void interact(Character character) {
        if (character.getX() == getX() && character.getY() == getY()) {
            heal(character);
        }
    }

    @Override
    public Boolean canInteract(Character character) {
        return true;
    }
}
