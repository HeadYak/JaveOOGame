package unsw.loopmania;

import javax.swing.text.Position;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * a basic form of building in the world
 */
public class VampireCastleBuilding extends Building implements Spawn{
    private Vampire vampire;
    private int range;
    private int loopReq;
    // TODO = add more types of building, and make sure buildings have effects on entities as required by the spec
    public VampireCastleBuilding(SimpleIntegerProperty x, SimpleIntegerProperty y) {
        super(x, y);
        vampire = new Vampire();
        range = 1;
        loopReq = 5;
        isSpawner = true;
    }

    public int getRange() {
        return range;
    }

    @Override
    public void spawn(int loops, LoopManiaWorld world) {
        if (loops % loopReq == 0) {
            Vampire newVampire = new Vampire(world.findClosestPathTile(getX(), getY()));
        }
    }
}
