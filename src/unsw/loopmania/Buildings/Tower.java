package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.Character;

public class Tower extends Building{
    private int range;
    public Tower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        range = 1;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public Boolean getIsSpawner() {
        return false;
    }

    @Override
    public void interact(Character character) {
    }

    @Override
    public Boolean canInteract() {
        return false;
    }
}
