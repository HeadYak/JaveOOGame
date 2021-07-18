package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.*;

public class Tower extends Building implements Support{
    private int range;
    public Tower(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        range = 1;
    }

    /**
     * @return the range of the building
     */
    @Override
    public int getRange() {
        return range;
    }

    /**
     * @return true if the building is a spawner and false if not
     */
    @Override
    public Boolean getIsSpawner() {
        return false;
    }

    /**
     * @param character
     * activates support buff for character
     */
    @Override
    public void support(Character character) {
        character.activateSupport();
    }

    /**
     * @param character
     * performs the buildings interaction with the building
     */
    @Override
    public void interact(Character character) {
        support(character);
        character.addInRange(this);
    }

    /**
     * @param character
     * @return true if the character can interact with the building else false
     */
    @Override
    public Boolean canInteract(Character character) {
        if (Math.pow(range, 2) >= Math.pow(character.getX() - getX(), 2) + Math.pow(character.getY() - getY(), 2)) {
            return true;
        }
        if (character.removeInRange(this)) {
            character.deactivateSupport();
        }
        return false;
    }

    /**
     * @param enemy
     * performs the buildings interaction with the enemy
     */
    @Override
    public void interactMob(BasicEnemy enemy) {
    }

    /**
     * @param enemy
     * @return true if the enemy can interact with the building else false
     */
    @Override
    public Boolean canInteractMob(BasicEnemy enemy) {
        return false;
    }

    /**
     * @param world
     * performs the buildings action on every new loop
     */
    @Override
    public void newLoop(LoopManiaWorld world) {
    }
}
