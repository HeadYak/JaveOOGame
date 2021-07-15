package unsw.loopmania.Buildings;

import javax.swing.text.Position;

import javafx.beans.property.SimpleIntegerProperty;
import unsw.loopmania.Building;
import unsw.loopmania.LoopManiaWorld;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends Building implements Spawn{
    private int loopReq;
    private int range;
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    
    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        range = 1;
        loopReq = 5;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public Boolean getIsSpawner() {
        return true;
    }

    @Override
    public void spawn(int loops, LoopManiaWorld world) {
        if (loops % loopReq == 0) {
            Vampire newVampire = new Vampire(world.findClosestPathTile(getX(), getY()));
            world.addEnemy(newVampire);
        }
    }
}
