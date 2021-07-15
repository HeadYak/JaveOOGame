package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.Character;
import unsw.loopmania.BasicEnemy;

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
        character.activateSupport();
    }

    @Override
    public Boolean canInteract(Character character) {
        if (range > Math.sqrt((character.getX() - getX())^2 + (character.getY() - getY())^2)) {
            return true;
        }
        character.deactivateSupport();
        return false;
    }

    @Override
    public void interactMob(BasicEnemy enemy) {
    }

    @Override
    public Boolean canInteractMob(BasicEnemy enemy) {
        return false;
    }
}
