package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.Character;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.*;

public class Trap extends Building{

    public Trap(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
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
     * @param enemy
     * damages the enemy that steps on trap
     */
    public void trap(BasicEnemy enemy) {
        enemy.damage(60);
    }

    /**
     * @param character
     * performs the buildings interaction with the building
     */    
    @Override
    public void interact(Character character) {
    }

    /**
     * @param character
     * @return true if the character can interact with the building else false
     */
    @Override
    public Boolean canInteract(Character character) {
        return false;
    }

    /**
     * @param enemy
     * performs the buildings interaction with the enemy
     */
    @Override
    public void interactMob(BasicEnemy enemy) {
        trap(enemy);
    }

    /**
     * @param enemy
     * @return true if the enemy can interact with the building else false
     */
    @Override
    public Boolean canInteractMob(BasicEnemy enemy) {
        return true;
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
