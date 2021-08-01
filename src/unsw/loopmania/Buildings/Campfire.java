package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Character;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.*;

public class Campfire extends Building{
    private int range;
    public Campfire(SimpleIntegerProperty x, SimpleIntegerProperty y) {
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
     * sets character's buff state to true
     */
    public void buff(Character character) {
        character.activateBuff();
    }

    /**
     * @param character
     * performs the buildings interaction with the building
     */
    @Override
    public void interact(Character character) {
        character.activateBuff();
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
            character.deactivateBuff();
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
     * @return null
     * performs the buildings action on every new loop
     */
    @Override
    public BasicEnemy newLoop(LoopManiaWorld world) {
        return null;
    }
}

