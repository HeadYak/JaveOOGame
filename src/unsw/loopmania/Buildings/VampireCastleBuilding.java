package unsw.loopmania.Buildings;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.enemies.BasicEnemy;
import unsw.loopmania.enemies.Vampire;
import unsw.loopmania.Character;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends Building implements Spawn{
    private int loopReq;
    
    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        loopReq = 5;
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
     * @param loops
     * @param world
     * spawns a mob on nearest tile to building depending on amount of loops
     */
    @Override
    public BasicEnemy spawn(LoopManiaWorld world) {
        if (world.getLoops() % loopReq == 0) {
            Vampire newVampire = new Vampire(world.findClosestPathTile(getX(), getY()), world);
            world.addEnemy(newVampire);
            return newVampire;
        }
        return null;
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
     * @return an enemy if being spawned or null
     * performs the buildings action on every new loop
     */
    @Override
    public BasicEnemy newLoop(LoopManiaWorld world) {
        return spawn(world);
    }
}
