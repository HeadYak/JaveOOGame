package unsw.loopmania.Buildings;

import javax.swing.text.Position;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.LoopManiaWorld;
import unsw.loopmania.Character;
import unsw.loopmania.BasicEnemy;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends Building implements Spawn, Support{
    private int loopReq;
    private int range;
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    
    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        range = 1;
        loopReq = 5;
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
        return true;
    }

    /**
     * @param loops
     * @param world
     * spawns a mob on nearest tile to building depending on amount of loops
     */
    @Override
    public void spawn(int loops, LoopManiaWorld world) {
        if (loops % loopReq == 0) {
            Vampire newVampire = new Vampire(world.findClosestPathTile(getX(), getY()));
            world.addEnemy(newVampire);
        }
    }

    /**
     * @param character
     * activates mobsupport buff for character
     */
    @Override
    public void support(Character character) {
        character.activateMobSupport();
    }

    /**
     * @param character
     * performs the buildings interaction with the building
     */
    @Override
    public void interact(Character character) {
        support(character);
    }

    /**
     * @param character
     * @return true if the character can interact with the building else false
     */
    @Override
    public Boolean canInteract(Character character) {
        if (range > Math.sqrt((character.getX() - getX())^2 + (character.getY() - getY())^2)) {
            character.addInRange(this);
            return true;
        }
        if (character.removeInRange(this)) {
            character.deactivateMobSupport();
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
     * @param character
     * performs the buildings action on every new loop
     */
    @Override
    public void newLoop(LoopManiaWorld world, Character character) {
        spawn(character.getLoop(), world);
    }
}
