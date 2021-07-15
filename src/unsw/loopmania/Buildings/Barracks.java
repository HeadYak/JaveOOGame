package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.Character;

public class Barracks extends Building{
    public Building(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    public void addAlly(Character character) {
        Ally newAlly = new Ally();
        character.recruitAlly(newAlly);
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
            addAlly(character);
        }
    }

    @Override
    public Boolean canInteract() {
        return true;
    }
}
