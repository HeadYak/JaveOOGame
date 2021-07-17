package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.Character;
import unsw.loopmania.BasicEnemy;
import unsw.loopmania.*;

public class Village extends Building{
    public Village(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * @param character
     * restores 20hp to character
     */
    public void heal(Character character) {
        character.regen(20);
    }

    /**
     * @return the range of the building
     */
    @Override
    public int getRange() {
        return 0;
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
     * performs the buildings interaction with the building
     */
    @Override
    public void interact(Character character) {
        if (character.getX() == getX() && character.getY() == getY()) {
            heal(character);
        }
    }

    /**
     * @param character
     * @return true if the character can interact with the building else false
     */
    @Override
    public Boolean canInteract(Character character) {
        return true;
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
     * @param character
     * performs the buildings action on every new loop
     */
    @Override
    public void newLoop(LoopManiaWorld world, Character character) {
    }
}
