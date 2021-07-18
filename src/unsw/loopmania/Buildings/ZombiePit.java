package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.enemies.Zombie;
import unsw.loopmania.Character;

public class ZombiePit extends Building implements Spawn{

    public ZombiePit(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
    }

    /**
     * @param loops
     * @param world
     * spawns a mob on nearest tile to building depending on amount of loops
     */
    @Override
    public void spawn(LoopManiaWorld world) {
        Zombie newZombie = new Zombie(world.findClosestPathTile(getX(), getY()), world.getCharacter());
        world.addEnemy(newZombie);
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
        return true;
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
        spawn(world);
    }
}
